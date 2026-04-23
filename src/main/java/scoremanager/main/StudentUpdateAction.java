package scoremanager.main;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import bean.Student;
import bean.Teacher;
import dao.ClassNumDao;
import dao.StudentDao;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import tool.Action;

public class StudentUpdateAction extends Action {

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {

        HttpSession session = req.getSession();
        Teacher teacher = (Teacher) session.getAttribute("user");

        // ログインチェック
        if (teacher == null) {
            req.getRequestDispatcher("login.jsp").forward(req, res);
            return;
        }

        // パラメータ取得（student_no）
        String studentNo = req.getParameter("student_no");

        // 学生情報取得
        StudentDao sDao = new StudentDao();
        Student student = sDao.get(studentNo, teacher.getSchool());

        // 入学年度セット
        List<Integer> entYearSet = new ArrayList<>();
        int year = LocalDate.now().getYear();
        for (int i = year - 10; i <= year; i++) {
            entYearSet.add(i);
        }

        // クラス番号セット
        ClassNumDao cDao = new ClassNumDao();
        List<String> classNumSet = cDao.filter(teacher.getSchool());

        // JSP に渡す
        req.setAttribute("student", student);
        req.setAttribute("ent_year_set", entYearSet);
        req.setAttribute("class_num_set", classNumSet);

        req.getRequestDispatcher("student_update.jsp").forward(req, res);
    }
}
