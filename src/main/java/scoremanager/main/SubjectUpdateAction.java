package scoremanager.main;

import bean.School;
import bean.Subject;
import dao.SubjectDao;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import tool.Action;

public class SubjectUpdateAction extends Action {
    @Override
    public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
        // セッションからログインユーザーの学校情報を取得
        HttpSession session = req.getSession();
        School school = (School) session.getAttribute("user_school");

        // リクエストパラメータから科目コードを取得
        String subjectCd = req.getParameter("cd");

        // DAOを使って現在の科目情報を取得
        SubjectDao sDao = new SubjectDao();
        Subject subject = sDao.get(subjectCd, school);

        // 取得した科目をリクエストにセットしてJSPへ
        req.setAttribute("subject", subject);
        req.getRequestDispatcher("subject_update.jsp").forward(req, res);
    }
}