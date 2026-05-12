package scoremanager.main;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import bean.School;
import bean.Subject;
import bean.Teacher;
import bean.Test;
import dao.ClassNumDao;
import dao.SubjectDao;
import dao.TestDao;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import tool.Action;

public class TestListAction extends Action {

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {

        HttpSession session = req.getSession();
        Teacher teacher = (Teacher) session.getAttribute("user");
        School school = teacher.getSchool();

        // --- 1. 検索フォームの選択肢を準備 ---
        
        // 入学年度のリスト（現在の年から10年前までを動的に生成）
        List<Integer> entYearList = new ArrayList<>();
        int currentYear = LocalDate.now().getYear();
        for (int i = currentYear; i >= currentYear - 10; i--) {
            entYearList.add(i);
        }

        // クラス一覧、科目一覧をDAOから取得
        ClassNumDao cNumDao = new ClassNumDao();
        List<String> classList = cNumDao.filter(school); // 学校に紐づくクラス一覧

        SubjectDao subDao = new SubjectDao();
        List<Subject> subjectList = subDao.filter(school); // 学校に紐づく科目一覧

        // --- 2. リクエストパラメータを取得 ---
        String entYearStr = req.getParameter("f1");     // 入学年度
        String classNum = req.getParameter("f2");       // クラス番号
        String subjectCd = req.getParameter("f3");      // 科目コード
        String numStr = req.getParameter("f4");         // 回数

        TestDao dao = new TestDao();
        List<Test> list = null;

        // --- 3. 検索実行判定 ---
        if (entYearStr != null && classNum != null && subjectCd != null && numStr != null &&
            !entYearStr.equals("") && !classNum.equals("") && !subjectCd.equals("") && !numStr.equals("")) {
            
            int entYear = Integer.parseInt(entYearStr);
            int no = Integer.parseInt(numStr);

            // 条件に一致する成績一覧を取得
            // ※TestDaoに「filter(School, 入学年度, クラス, 科目, 回数)」メソッドがある前提
            list = dao.filter(school, entYear, classNum, subjectCd, no);
        } else {
            list = dao.findAll(school); 
        }

        // --- 4. JSPに必要なデータをセット ---
        req.setAttribute("entYearList", entYearList);
        req.setAttribute("classList", classList);
        req.setAttribute("subjectList", subjectList);
        req.setAttribute("testList", list);

        // JSPへフォワード
        req.getRequestDispatcher("test_list.jsp").forward(req, res);
    }
}