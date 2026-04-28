package scoremanager.main;

import java.util.List;

import bean.School;
import dao.SchoolDao;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import tool.Action;

public class SubjectRegistAction extends Action {

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse res)
            throws Exception {

        SchoolDao dao = new SchoolDao();
        List<School> list = dao.list();   // ← 追加した list() を使う

        req.setAttribute("schoolList", list);

        req.getRequestDispatcher("/scoremanager/main/subject_regist.jsp")
           .forward(req, res);
    }
}
