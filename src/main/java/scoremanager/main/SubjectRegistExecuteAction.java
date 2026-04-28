package scoremanager.main;

import bean.School;
import bean.Subject;
import dao.SchoolDao;
import dao.SubjectDao;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import tool.Action;

public class SubjectRegistExecuteAction extends Action {

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse res) 
            throws Exception {

        req.setCharacterEncoding("UTF-8");

        String schoolCd = req.getParameter("schoolCd");
        String subjectCd = req.getParameter("subjectCd");
        String subjectName = req.getParameter("subjectName");

        SchoolDao schoolDao = new SchoolDao();
        School school = schoolDao.get(schoolCd);

        Subject subject = new Subject();
        subject.setSubjectCd(subjectCd);
        subject.setSubjectName(subjectName);
        subject.setSchool(school);
        subject.setSchoolCd(schoolCd); 

        SubjectDao dao = new SubjectDao();
        dao.insert(subject);

        req.setAttribute("subject", subject);

        return "subject_regist_done.jsp";
    }
}