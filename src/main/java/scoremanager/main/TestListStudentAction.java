package scoremanager.main;

import java.util.List;

import bean.Teacher;
import bean.Test;
import dao.TestDao;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import tool.Action;

public class TestListStudentAction extends Action {

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {

        HttpSession session = req.getSession();
        Teacher teacher = (Teacher) session.getAttribute("user");

        if (teacher == null) {
            req.getRequestDispatcher("/login.jsp").forward(req, res);
            return;
        }

        // 学生番号を取得
        String studentNo = req.getParameter("student_no");

        if (studentNo == null || studentNo.isEmpty()) {
            req.setAttribute("error", "学生番号が指定されていません");
            req.getRequestDispatcher("/error.jsp").forward(req, res);
            return;
        }

        // DAOのインスタンス化
        TestDao testDao = new TestDao();

        // 学生の全成績を取得
        List<Test> testList = testDao.findByStudent(
            teacher.getSchool(),
            studentNo
        );

        // --- 追加したバリデーション処理 ---
        if (testList == null || testList.isEmpty()) {
            // エラーメッセージをセット
            req.setAttribute("errors", "学生情報が存在しませんでした");
            
            // 検索画面（TestSearch.action）を呼び出して戻る
            // ※URLは環境に合わせて適宜調整してください
            req.getRequestDispatcher("TestSearch.action").forward(req, res);
            return;
        }
        // --------------------------------

        // データがある場合は結果画面へ
        req.setAttribute("studentNo", studentNo);
        req.setAttribute("testList", testList);

        req.getRequestDispatcher("/scoremanager/main/test_list_student.jsp")
            .forward(req, res);
    }
}