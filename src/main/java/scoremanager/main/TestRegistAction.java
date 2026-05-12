package scoremanager.main;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import bean.School;
import bean.Student;
import bean.Subject;
import bean.Teacher;
import dao.ClassNumDao;
import dao.StudentDao;
import dao.SubjectDao;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import tool.Action;

public class TestRegistAction extends Action {

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {

        HttpSession session = req.getSession();
        Teacher teacher = (Teacher) session.getAttribute("user");
        
        // ログインチェック（テスト用）
        if (teacher == null) {
            teacher = new Teacher();
            School school = new School();
            school.setSchoolCd("oos"); // H2コンソールのデータに合わせる
            teacher.setSchool(school);
        }

        // 変数初期化
        String entYearStr = req.getParameter("f1");
        String classNum = req.getParameter("f2");
        int entYear = 0;
        List<Student> students = null;
        LocalDate todaysDate = LocalDate.now();
        int year = todaysDate.getYear();
        
        StudentDao studentDao = new StudentDao();
        ClassNumDao classNumDao = new ClassNumDao();
        SubjectDao subjectDao = new SubjectDao(); // 科目用Dao
        Map<String, String> errors = new HashMap<>();

        if (entYearStr != null && !entYearStr.isEmpty()) {
            entYear = Integer.parseInt(entYearStr);
        }

        // --- 選択肢の準備 ---
        // 1. 入学年度リスト
        List<Integer> entYearSet = new ArrayList<>();
        for (int i = year - 10; i <= year + 1; i++) {
            entYearSet.add(i);
        }

        // 2. クラス一覧
        List<String> classList = classNumDao.filter(teacher.getSchool());

        // 3. 科目一覧 (SubjectDaoを使用してDBから取得)
        List<Subject> subjects = subjectDao.filter(teacher.getSchool());

        // --- 検索ロジック ---
        if (entYear != 0 && classNum != null && !classNum.equals("0")) {
            students = studentDao.filter(teacher.getSchool(), entYear, classNum, true);
        } else if (entYear == 0 && classNum != null && !classNum.equals("0")) {
            errors.put("f1", "クラスを指定する場合は入学年度も指定してください");
        }

        // --- レスポンスセット ---
        req.setAttribute("f1", entYear);
        req.setAttribute("f2", classNum);
        req.setAttribute("students", students);
        req.setAttribute("class_num_set", classList);
        req.setAttribute("ent_year_set", entYearSet);
        req.setAttribute("subjects", subjects); // 科目リストをセット
        req.setAttribute("errors", errors);

        session.setAttribute("students", students);

        req.getRequestDispatcher("test_regist.jsp").forward(req, res);
    }
}