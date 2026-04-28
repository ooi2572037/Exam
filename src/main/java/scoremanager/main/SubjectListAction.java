package scoremanager.main;

import java.util.List;

import bean.Subject;
import bean.Teacher;
import dao.SubjectDao;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import tool.Action;

public class SubjectListAction extends Action {

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        // セッションからユーザー情報を取得
        HttpSession session = request.getSession();
        Teacher teacher = (Teacher) session.getAttribute("user");

        // 科目DAOをインスタンス化
        SubjectDao sDao = new SubjectDao();
        // ログインユーザーの所属校に紐づく科目一覧を取得
        List<Subject> subjects = sDao.filter(teacher.getSchool());

        // JSPに渡すデータをリクエスト属性にセット
        request.setAttribute("subjects", subjects);

        // JSPへフォワード（ファイル名が subject_list.jsp であることを確認してください）
        request.getRequestDispatcher("subject_list.jsp").forward(request, response);
    }
}