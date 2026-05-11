package scoremanager.main;

import java.util.List;

import bean.Subject;
import bean.Teacher;
import dao.ClassNumDao;
import dao.SubjectDao;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import tool.Action;

public class TestSearchAction extends Action {

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {

        HttpSession session = req.getSession();
        Teacher teacher = (Teacher) session.getAttribute("user");

        if (teacher == null) {
            req.getRequestDispatcher("/login.jsp").forward(req, res);
            return;
        }

        SubjectDao subjectDao = new SubjectDao();
        ClassNumDao classNumDao = new ClassNumDao();

        List<Subject> subjectList = subjectDao.filter(teacher.getSchool());
        List<String> classList = classNumDao.filter(teacher.getSchool());


        req.setAttribute("subjectList", subjectList);
        req.setAttribute("classList", classList);

        req.getRequestDispatcher("/scoremanager/main/test_search.jsp")
           .forward(req, res);
    }
}
