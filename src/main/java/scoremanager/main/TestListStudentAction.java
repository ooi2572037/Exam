package scoremanager.main;

import java.util.List;

import bean.ClassNum;
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

public class TestListStudentAction extends Action {

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {

        HttpSession session = req.getSession();
        Teacher teacher = (Teacher) session.getAttribute("user");

        // ログインチェック
        if (teacher == null) {
            req.getRequestDispatcher("/login.jsp").forward(req, res);
            return;
        }

        // test_list.jsp から送られる検索条件
        String subjectCd = req.getParameter("subject_cd");
        String classNumCd = req.getParameter("class_num");

        // DAO
        SubjectDao subjectDao = new SubjectDao();
        ClassNumDao classNumDao = new ClassNumDao();
        TestDao testDao = new TestDao();

        // 科目・クラス情報を取得
        Subject subject = subjectDao.get(subjectCd, teacher.getSchool());
        ClassNum classNum = classNumDao.get(classNumCd, teacher.getSchool());

        // 成績一覧を取得（TestDao）
        List<Test> testList = testDao.findByCondition(
            teacher.getSchool(),
            subjectCd,
            classNumCd
        );

        // JSP に渡す
        req.setAttribute("subject", subject);
        req.setAttribute("classNum", classNum);
        req.setAttribute("testList", testList);

        // 成績一覧 JSP へ
        req.getRequestDispatcher("/scoremanager/main/test_list_student.jsp")
           .forward(req, res);
    }
}
