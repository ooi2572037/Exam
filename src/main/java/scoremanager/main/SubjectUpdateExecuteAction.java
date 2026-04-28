package scoremanager.main;

import bean.School;
import bean.Subject;
import dao.SubjectDao;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import tool.Action;

public class SubjectUpdateExecuteAction extends Action {
    @Override
    public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
        HttpSession session = req.getSession();
        School school = (School) session.getAttribute("user_school");

        // フォームから科目コードと新しい科目名を取得
        String subjectCd = req.getParameter("cd");
        String subjectName = req.getParameter("name");

        // Subjectオブジェクトに値をセット
        Subject subject = new Subject();
        subject.setSubjectCd(subjectCd);
        subject.setSubjectName(subjectName);
        subject.setSchool(school);

        // DAOで更新実行
        SubjectDao sDao = new SubjectDao();
        sDao.update(subject);

        // 更新完了画面へ遷移
        req.getRequestDispatcher("subject_update_done.jsp").forward(req, res);
    }
}