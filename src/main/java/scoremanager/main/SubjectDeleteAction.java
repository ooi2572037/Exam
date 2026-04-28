package scoremanager.main;

import bean.Subject;
import bean.Teacher;
import dao.SubjectDao;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import tool.Action;

public class SubjectDeleteAction extends Action {

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {

        HttpSession session = req.getSession();
        Teacher teacher = (Teacher) session.getAttribute("user");

        // ログインチェック
        if (teacher == null) {
            req.getRequestDispatcher("login.jsp").forward(req, res);
            return;
        }

        // パラメータ取得
        String subjectCd = req.getParameter("subject_cd");

        // 科目情報取得
        SubjectDao dao = new SubjectDao();
        Subject subject = dao.get(subjectCd, teacher.getSchool());

        // JSP に渡す
        req.setAttribute("subject", subject);

        req.getRequestDispatcher("subject_delete.jsp").forward(req, res);
    }
}
