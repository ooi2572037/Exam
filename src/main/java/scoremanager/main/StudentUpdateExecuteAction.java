package scoremanager.main;

import java.util.HashMap;
import java.util.Map;

import bean.Student;
import bean.Teacher;
import dao.StudentDao;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import tool.Action;

public class StudentUpdateExecuteAction extends Action {

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {

        HttpSession session = req.getSession();
        Teacher teacher = (Teacher) session.getAttribute("user");

        // ログインチェック
        if (teacher == null) {
            req.getRequestDispatcher("login.jsp").forward(req, res);
            return;
        }

        // パラメータ取得
        String studentNo = req.getParameter("student_no");
        String studentName = req.getParameter("student_name");
        String entYearStr = req.getParameter("ent_year");
        String classNum = req.getParameter("class_num");
        String isAttendStr = req.getParameter("is_attend");

        Map<String, String> errors = new HashMap<>();

        // 入力チェック
        if (studentName == null || studentName.isEmpty()) {
            errors.put("student_name", "氏名を入力してください");
        }
        if (entYearStr == null || entYearStr.isEmpty()) {
            errors.put("ent_year", "入学年度を選択してください");
        }
        if (classNum == null || classNum.isEmpty()) {
            errors.put("class_num", "クラス番号を選択してください");
        }

        // エラーがある場合 → 編集画面に戻す
        if (!errors.isEmpty()) {

            Student stu = new Student();
            stu.setStudentNo(studentNo);
            stu.setStudentName(studentName);
            stu.setEntYear(Integer.parseInt(entYearStr));
            stu.setClassNum(classNum);
            stu.setAttend(isAttendStr != null);

            req.setAttribute("errors", errors);
            req.setAttribute("student", stu);

            req.getRequestDispatcher("student_update.jsp").forward(req, res);
            return;
        }

        // Student オブジェクトにセット
        Student student = new Student();
        student.setStudentNo(studentNo);
        student.setStudentName(studentName);
        student.setEntYear(Integer.parseInt(entYearStr));
        student.setClassNum(classNum);
        student.setAttend(isAttendStr != null);
        student.setSchool(teacher.getSchool()); 

        // 更新処理
        StudentDao dao = new StudentDao();
        dao.update(student);

        // 完了画面へ
        req.getRequestDispatcher("student_update_done.jsp").forward(req, res);
    }
}
