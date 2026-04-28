package scoremanager.main;

import bean.Teacher;
import dao.SubjectDao;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import tool.Action;

public class SubjectDeleteExecuteAction extends Action {

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

        // 削除処理
        SubjectDao dao = new SubjectDao();
        dao.delete(subjectCd, teacher.getSchool());

        // 完了画面に表示するためセット
        req.setAttribute("subject_cd", subjectCd);

        req.getRequestDispatcher("subject_delete_done.jsp").forward(req, res);
    }
}
