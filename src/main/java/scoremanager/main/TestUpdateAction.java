package scoremanager.main;

import bean.Teacher;
import bean.Test;
import dao.TestDao;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import tool.Action;

public class TestUpdateAction extends Action {

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {

        HttpSession session = req.getSession();
        Teacher teacher = (Teacher) session.getAttribute("user");

        if (teacher == null) {
            req.setAttribute("error", "ログイン情報がありません");
            req.getRequestDispatcher("/error.jsp").forward(req, res);
            return;
        }

        String studentNo = req.getParameter("studentNo");
        String subjectCd = req.getParameter("subjectCd");
        String noStr = req.getParameter("no");

        if (studentNo == null || subjectCd == null || noStr == null) {
            req.setAttribute("error", "パラメータが不足しています");
            req.getRequestDispatcher("/error.jsp").forward(req, res);
            return;
        }

        int no = Integer.parseInt(noStr);

        TestDao dao = new TestDao();

        Test test = dao.get(studentNo, subjectCd, teacher.getSchool(), no);

        if (test == null) {
            req.setAttribute("error", "成績データが見つかりません");
            req.getRequestDispatcher("/error.jsp").forward(req, res);
            return;
        }

        req.setAttribute("test", test);

        req.getRequestDispatcher("/scoremanager/main/test_update.jsp")
           .forward(req, res);
    }
}
