package dao;
<<<<<<< HEAD

import java.sql.Connection;
import java.sql.PreparedStatement;

import bean.Test;

public class TestDao extends Dao {

    /**
     * 成績情報をデータベースに保存（または更新）するメソッド
     * * @param test 保存する成績データ
     * @return 成功した場合は true
     * @throws Exception
     */
    public boolean save(Test test) throws Exception {
        // データベース接続を取得
        Connection connection = getConnection();
        // 実行するSQL文（存在すれば更新、なければ挿入）
        // ※H2データベースの MERGE INTO 構文を使用すると便利です
        String sql = "MERGE INTO TEST (STUDENT_NO, SUBJECT_CD, SCHOOL_CD, NO, POINT, CLASS_NUM) KEY(STUDENT_NO, SUBJECT_CD, NO) VALUES (?, ?, ?, ?, ?, ?)";
        
        PreparedStatement st = connection.prepareStatement(sql);
        
        try {
            // パラメータの設定
            st.setString(1, test.getStudentNo());
            st.setString(2, test.getSubjectCd());
            st.setString(3, "tes"); // 本来は test.getSchool().getSchoolCd() ですが、現在は固定値
            st.setInt(4, test.getNo());
            st.setInt(5, test.getPoint());
            st.setString(6, test.getClassNum());
            
            // 実行
            int count = st.executeUpdate();
            
            if (count > 0) {
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            throw e;
        } finally {
            // 接続を閉じる
            if (st != null) st.close();
            if (connection != null) connection.close();
        }
    }
}
=======
 
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import bean.School;
import bean.Test;
 
public class TestDao extends Dao {
 
    /**

     * 指定された条件に一致する成績情報を1件取得します。

     * 主キー：student_no, subject_cd, school_cd, no

     */

    public Test get(String studentNo, String subjectCd, School school, int no) throws Exception {
 
        Test test = null;

        Connection con = getConnection();
 
        PreparedStatement st = con.prepareStatement(

            "SELECT * FROM test WHERE student_no=? AND subject_cd=? AND school_cd=? AND no=?"

        );
 
        st.setString(1, studentNo);

        st.setString(2, subjectCd);

        st.setString(3, school.getSchoolCd());

        st.setInt(4, no);
 
        ResultSet rs = st.executeQuery();
 
        if (rs.next()) {

            test = new Test();

            test.setStudentNo(rs.getString("student_no"));

            test.setSubjectCd(rs.getString("subject_cd"));

            test.setSchoolCd(rs.getString("school_cd"));

            test.setNo(rs.getInt("no"));

            test.setPoint(rs.getInt("point"));

            test.setClassNum(rs.getString("class_num"));

        }
 
        rs.close();

        st.close();

        con.close();
 
        return test;

    }
 
    /**

     * 学校、科目、クラスを条件に成績一覧を取得します。

     */

    public List<Test> findByCondition(School school, String subjectCd, String classNum) throws Exception {
 
        List<Test> list = new ArrayList<>();

        Connection con = getConnection();
 
        PreparedStatement st = con.prepareStatement(

            "SELECT * FROM test WHERE school_cd=? AND subject_cd=? AND class_num=? ORDER BY student_no, no"

        );
 
        st.setString(1, school.getSchoolCd());

        st.setString(2, subjectCd);

        st.setString(3, classNum);
 
        ResultSet rs = st.executeQuery();
 
        while (rs.next()) {

            Test test = new Test();

            test.setStudentNo(rs.getString("student_no"));

            test.setSubjectCd(rs.getString("subject_cd"));

            test.setSchoolCd(rs.getString("school_cd"));

            test.setNo(rs.getInt("no"));

            test.setPoint(rs.getInt("point"));

            test.setClassNum(rs.getString("class_num"));
 
            list.add(test);

        }
 
        rs.close();

        st.close();

        con.close();
 
        return list;

    }
 
    /**

     * 成績情報（点数）を更新します。

     */

    public void update(Test test) throws Exception {
 
        Connection con = getConnection();
 
        PreparedStatement st = con.prepareStatement(

            "UPDATE test SET point=? WHERE student_no=? AND subject_cd=? AND school_cd=? AND no=?"

        );
 
        st.setInt(1, test.getPoint());

        st.setString(2, test.getStudentNo());

        st.setString(3, test.getSubjectCd());

        st.setString(4, test.getSchoolCd());

        st.setInt(5, test.getNo());
 
        st.executeUpdate();
 
        st.close();

        con.close();

    }
 
    /**

     * 成績情報を削除します。

     */

    public void delete(Test test) throws Exception {
 
        Connection con = getConnection();
 
        PreparedStatement st = con.prepareStatement(

            "DELETE FROM test WHERE student_no=? AND subject_cd=? AND school_cd=? AND no=?"

        );
 
        st.setString(1, test.getStudentNo());

        st.setString(2, test.getSubjectCd());

        st.setString(3, test.getSchoolCd());

        st.setInt(4, test.getNo());
 
        st.executeUpdate();
 
        st.close();

        con.close();

    }

}
 
>>>>>>> branch 'master' of https://github.com/ooi2572037/Exam.git
