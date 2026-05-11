package scoremanager.main;

import bean.School;
import bean.Test;
import dao.TestDao;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import tool.Action;

public class TestUpdateExcuteAction extends Action {
    @Override
    public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
        // 1. 画面から送られてきたデータを受け取る
        String studentNo = req.getParameter("studentNo");
        String subjectCd = req.getParameter("subjectCd");
        int no = Integer.parseInt(req.getParameter("no"));
        int point = Integer.parseInt(req.getParameter("point")); // これが新しい点数

        HttpSession session = req.getSession();
        School school = (School) session.getAttribute("user_school");

        // 2. Beanに新しい点数をセット
        Test test = new Test();
        test.setStudentNo(studentNo);
        test.setSubjectCd(subjectCd);
        test.setSchoolCd(school.getSchoolCd());
        test.setNo(no);
        test.setPoint(point);

        // 3. DAOのupdateメソッドを呼ぶ
        TestDao dao = new TestDao();
        dao.update(test);

        // 4. 完了したら一覧へ戻る
        res.sendRedirect("TestList.action");
    }
}