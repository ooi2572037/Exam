package scoremanager.main;

import bean.School;
import bean.Subject;
import dao.SchoolDao;
import dao.SubjectDao;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import tool.Action;

public class SubjectRegistExecuteAction extends Action {

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse res) 
            throws Exception {

        req.setCharacterEncoding("UTF-8");

        String schoolCd = req.getParameter("schoolCd");
        String subjectCd = req.getParameter("subjectCd");
        String subjectName = req.getParameter("subjectName");

        SchoolDao schoolDao = new SchoolDao();
        School school = schoolDao.get(schoolCd);

        SubjectDao dao = new SubjectDao();

        // ★ 重複チェック
        if (dao.exists(schoolCd, subjectCd)) {
            req.setAttribute("error", "科目コードが重複しています。");
            req.setAttribute("schoolList", schoolDao.findAll()); // ← 再表示用
            req.getRequestDispatcher("/scoremanager/main/subject_regist.jsp")
               .forward(req, res);
            return;
        }

        // ★ 重複していなければ登録
        Subject subject = new Subject();
        subject.setSubjectCd(subjectCd);
        subject.setSubjectName(subjectName);
        subject.setSchool(school);

        dao.insert(subject);

        req.setAttribute("subject", subject);

        req.getRequestDispatcher("/scoremanager/main/subject_regist_done.jsp")
           .forward(req, res);
    }
}

