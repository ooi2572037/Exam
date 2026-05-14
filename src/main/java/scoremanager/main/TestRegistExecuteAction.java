package scoremanager.main;

import java.util.List;

import bean.Student;
import bean.Teacher;
import bean.Test;
import dao.TestDao;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import tool.Action;

public class TestRegistExecuteAction extends Action {

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {

        HttpSession session = req.getSession();
        Teacher teacher = (Teacher) session.getAttribute("user");

        @SuppressWarnings("unchecked")
        List<Student> students = (List<Student>) session.getAttribute("students");

        String subjectCd = req.getParameter("subject_cd");
        String testNoStr = req.getParameter("test_no");

        int testNo = 0;
        if (testNoStr != null && !testNoStr.isEmpty()) {
            testNo = Integer.parseInt(testNoStr);
        }

        TestDao dao = new TestDao();

        if (students != null && subjectCd != null && teacher != null) {

            for (Student st : students) {

                String pointStr = req.getParameter("point_" + st.getStudentNo());

                if (pointStr != null && !pointStr.isEmpty()) {

                    int point = Integer.parseInt(pointStr);

                    Test test = new Test();
                    test.setStudentNo(st.getStudentNo());
                    test.setSchoolCd(teacher.getSchool().getSchoolCd());
                    test.setSubjectCd(subjectCd);
                    test.setNo(testNo);
                    test.setPoint(point);
                    test.setClassNum(st.getClassNum());

                    dao.save(test);
                }
            }
        }

        req.getRequestDispatcher("/scoremanager/main/test_regist_done.jsp")
           .forward(req, res);
    }
}
