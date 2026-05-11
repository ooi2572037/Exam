package scoremanager.main;

import bean.School;
import bean.Test;
import dao.TestDao;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import tool.Action;

public class TestDeleteExcuteAction extends Action {
    @Override
    public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
        // 1. 画面から送られてきた主キーを受け取る
        String studentNo = req.getParameter("studentNo");
        String subjectCd = req.getParameter("subjectCd");
        int no = Integer.parseInt(req.getParameter("no"));

        HttpSession session = req.getSession();
        School school = (School) session.getAttribute("user_school");

        // 2. Beanにセット（削除条件として使用）
        Test test = new Test();
        test.setStudentNo(studentNo);
        test.setSubjectCd(subjectCd);
        test.setSchoolCd(school.getSchoolCd());
        test.setNo(no);

        // 3. DAOのdeleteメソッドを呼び出す
        TestDao dao = new TestDao();
        dao.delete(test);

        // 4. 削除が終わったら一覧画面へ戻る
        res.sendRedirect("TestList.action");
    }
}