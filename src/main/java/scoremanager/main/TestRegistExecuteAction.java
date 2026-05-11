package scoremanager.main;

import java.util.List;

import bean.Student;
import bean.Teacher;
import bean.Test;
import dao.TestDao;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import tool.Action;

public class TestRegistExecuteAction extends Action {

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
        // セッションの取得
        HttpSession session = req.getSession();
        // ログイン中の教員情報を取得（SCHOOL_CD用）
        Teacher teacher = (Teacher) session.getAttribute("user");
        
        // TestRegistActionで保存した学生リストをセッションから取得
        @SuppressWarnings("unchecked")
        List<Student> students = (List<Student>) session.getAttribute("students");

        // --- 1. JSPの登録用フォームから「科目」と「回数」を取得 ---
        String subjectCd = req.getParameter("subject_cd"); // SUBJECT_CD用
        String testNoStr = req.getParameter("test_no");    // NO用
        
        int testNo = 0;
        if (testNoStr != null && !testNoStr.isEmpty()) {
            testNo = Integer.parseInt(testNoStr);
        }

        // DAOの初期化
        TestDao dao = new TestDao();

        // 必須データ（学生リスト、科目、教員情報）が揃っているかチェック
        if (students != null && subjectCd != null && teacher != null) {
            
            // 検索された学生一人ひとりに対して点数を保存
            for (Student st : students) {
                // 各行の入力欄（point_学籍番号）から点数を取得
                String pointStr = req.getParameter("point_" + st.getStudentNo());

                // 点数が入力されている場合のみ保存処理を行う
                if (pointStr != null && !pointStr.isEmpty()) {
                    int point = Integer.parseInt(pointStr);
                    
                    // --- 2. テーブルのカラム定義に合わせてデータをセット ---
                    Test test = new Test();
                    
                    // STUDENT_NO: 学生オブジェクトから取得
                    test.setStudentNo(st.getStudentNo());
                    
                    // SCHOOL_CD: ログイン教員の所属校から取得
                    test.setSchool(teacher.getSchool());
                    
                    // SUBJECT_CD: JSPのプルダウンから取得
                    test.setSubjectCd(subjectCd);
                    
                    // NO: JSPのプルダウンから取得
                    test.setNo(testNo);
                    
                    // POINT: JSPの入力欄から取得
                    test.setPoint(point);
                    
                    // CLASS_NUM: 学生オブジェクトから取得
                    test.setClassNum(st.getClassNum());

                    // --- 3. データベースへ保存 ---
                    // TestDaoのsaveメソッド（MERGE文など）を呼び出し
                    dao.save(test);
                }
            }
        }

        // 完了後はメニュー画面へリダイレクト
        res.sendRedirect("Menu.action");
    }
}