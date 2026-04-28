package scoremanager.main;

import java.util.List;

import bean.School;
import dao.SchoolDao;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import tool.Action;

public class SubjectRegistAction extends Action {

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse res)
            throws Exception {

        SchoolDao dao = new SchoolDao();
        List<School> list = dao.findAll();

        req.setAttribute("schoolList", list);

        return "/scoremanager/main/subject_regist.jsp";

    }
}
