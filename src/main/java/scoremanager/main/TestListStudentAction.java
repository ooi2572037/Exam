package scoremanager.main;

import java.util.List;

import bean.Teacher;
import bean.Test;
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

        if (teacher == null) {
            req.getRequestDispatcher("/login.jsp").forward(req, res);
            return;
        }

        // ★ 学生番号を取得
        String studentNo = req.getParameter("student_no");

        if (studentNo == null || studentNo.isEmpty()) {
            req.setAttribute("error", "学生番号が指定されていません");
            req.getRequestDispatcher("/error.jsp").forward(req, res);
            return;
        }

        // DAO
        TestDao testDao = new TestDao();

        // ★ 学生の全成績を取得
        List<Test> testList = testDao.findByStudent(
            teacher.getSchool(),
            studentNo
        );

        req.setAttribute("studentNo", studentNo);
        req.setAttribute("testList", testList);

        req.getRequestDispatcher("/scoremanager/main/test_list_student.jsp")
           .forward(req, res);
    }
}

