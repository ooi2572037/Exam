package dao;

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