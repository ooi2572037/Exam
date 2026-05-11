package scoremanager.main;

import java.util.ArrayList;
import java.util.List;

import bean.Student;
import bean.Subject;
import bean.Teacher;
import bean.Test;
import dao.StudentDao;
import dao.SubjectDao;
import dao.TestDao;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import tool.Action;

public class TestListSubjectAction extends Action {

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {

        HttpSession session = req.getSession();
        Teacher teacher = (Teacher) session.getAttribute("user");

        // ログインチェック
        if (teacher == null) {
            req.getRequestDispatcher("/login.jsp").forward(req, res);
            return;
        }

        // test_list.jsp から送られる科目コード
        String subjectCd = req.getParameter("subject_cd");

        // DAO
        SubjectDao subjectDao = new SubjectDao();
        TestDao testDao = new TestDao();
        StudentDao studentDao = new StudentDao();

        // 科目情報を取得（SubjectDao の仕様に完全準拠）
        Subject subject = subjectDao.get(subjectCd, teacher.getSchool());

        // 科目に紐づく成績一覧を取得
        List<Test> testList = testDao.findBySubject(
            teacher.getSchool(),
            subjectCd
        );

        // 学生名を付与する
        List<Test> enrichedList = new ArrayList<>();

        for (Test t : testList) {
            Student stu = studentDao.get(t.getStudentNo(), teacher.getSchool());
            if (stu != null) {
                t.setStudentName(stu.getStudentName());
            }
            enrichedList.add(t);
        }

        // JSP に渡す
        req.setAttribute("subject", subject);
        req.setAttribute("testList", enrichedList);

        // 科目別成績一覧 JSP へ
        req.getRequestDispatcher("/scoremanager/main/test_list_subject.jsp")
           .forward(req, res);
    }
}
