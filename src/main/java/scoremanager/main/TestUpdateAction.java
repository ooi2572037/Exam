package scoremanager.main;

import bean.School;
import bean.Test;
import dao.TestDao;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import tool.Action;

public class TestUpdateAction extends Action {
    @Override
    public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
        // 1. パラメータを受け取る（どのデータを変更するか特定するため）
        String studentNo = req.getParameter("studentNo");
        String subjectCd = req.getParameter("subjectCd");
        int no = Integer.parseInt(req.getParameter("no"));
        
        // 2. セッションからログインユーザーの学校情報を取得
        HttpSession session = req.getSession();
        School school = (School) session.getAttribute("user_school");

        // 3. DAOを使って、現在の成績データを1件取得する
        TestDao dao = new TestDao();
        Test test = dao.get(studentNo, subjectCd, school, no);

        // 4. 取得したデータをリクエストにセット（JSPで表示するため）
        req.setAttribute("test", test);

        // 5. 変更入力用のJSPへ移動
        req.getRequestDispatcher("test_update.jsp").forward(req, res);
    }
}