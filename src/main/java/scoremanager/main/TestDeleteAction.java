package scoremanager.main;

import bean.School;
import bean.Test;
import dao.TestDao;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import tool.Action;

public class TestDeleteAction extends Action {
    public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
        // 1. 画面（リクエスト）からパラメータを受け取る
        String studentNo = req.getParameter("studentNo");
        String subjectCd = req.getParameter("subjectCd");
        int no = Integer.parseInt(req.getParameter("no"));
        
        // 2. セッションから学校情報を取得（ログイン中のユーザーの学校）
        HttpSession session = req.getSession();
        School school = (School)session.getAttribute("user_school");

        // 3. BeanにセットしてDAOを実行
        Test test = new Test();
        test.setStudentNo(studentNo);
        test.setSubjectCd(subjectCd);
        test.setSchoolCd(school.getSchoolCd());
        test.setNo(no);

        TestDao dao = new TestDao();
        dao.delete(test); // ★ここでDAOの削除メソッドを呼ぶ！

        // 4. 完了したら一覧画面へ戻る
        res.sendRedirect("TestList.action");
    }
}