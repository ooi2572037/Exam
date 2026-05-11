package scoremanager.main;

import java.util.List;

import bean.School;
import bean.Teacher;
import bean.Test;
import dao.TestDao;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import tool.Action;

public class TestListAction extends Action {

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {

        HttpSession session = req.getSession();
        
        // ログインユーザーを Teacher クラスとして取得
        Teacher teacher = (Teacher) session.getAttribute("user");

        TestDao dao = new TestDao();
        List<Test> list = null;

        // teacherがnullでないことを確認してから学校情報を取得
        if (teacher != null) {
            School school = teacher.getSchool();
            // findAllを実行
            list = dao.findAll(school);
        }

        req.setAttribute("testList", list);

        // JSPへフォワード
        req.getRequestDispatcher("test_list.jsp").forward(req, res);
    }
}