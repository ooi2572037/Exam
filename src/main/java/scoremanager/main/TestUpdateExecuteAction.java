package scoremanager.main;

import bean.School;
import bean.Teacher;
import bean.Test;
import dao.TestDao;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import tool.Action;

public class TestUpdateExecuteAction extends Action {
    @Override
    public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {

        String studentNo = req.getParameter("studentNo");
        String subjectCd = req.getParameter("subjectCd");
        int no = Integer.parseInt(req.getParameter("no"));
        int point = Integer.parseInt(req.getParameter("point"));

        HttpSession session = req.getSession();

        Teacher teacher = (Teacher) session.getAttribute("user");
        School school = teacher.getSchool();

        Test test = new Test();
        test.setStudentNo(studentNo);
        test.setSubjectCd(subjectCd);
        test.setSchoolCd(school.getSchoolCd());  
        test.setNo(no);
        test.setPoint(point);

        TestDao dao = new TestDao();
        dao.update(test);

        res.sendRedirect("TestList.action");
    }
}
