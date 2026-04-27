package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import bean.School;
import bean.Subject;

public class SubjectDao extends Dao {

    /**
     * 学校に紐づく科目一覧を取得する
     */
    public List<Subject> filter(School school) throws Exception {
        List<Subject> list = new ArrayList<>();
        Connection connection = getConnection();
        PreparedStatement statement = null;

        try {
            // SQL文：列名を SUBJECT_CD に修正
            statement = connection.prepareStatement(
                "SELECT * FROM SUBJECT WHERE SCHOOL_CD = ? ORDER BY SUBJECT_CD ASC"
            );
            
            // Schoolクラスのメソッド名は getSchoolCd() を使用
            statement.setString(1, school.getSchoolCd()); 
            
            ResultSet rSet = statement.executeQuery();

            while (rSet.next()) {
                Subject subject = new Subject();
                // データベースの列名に合わせて取得
                subject.setCd(rSet.getString("SUBJECT_CD"));
                subject.setName(rSet.getString("SUBJECT_NAME"));
                subject.setSchool(school);
                list.add(subject);
            }
        } catch (Exception e) {
            throw e;
        } finally {
            if (statement != null) {
                statement.close();
            }
            if (connection != null) {
                connection.close();
            }
        }
        return list;
    }
}