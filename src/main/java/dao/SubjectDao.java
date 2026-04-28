package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import bean.School;
import bean.Subject;

public class SubjectDao extends Dao {

    private SchoolDao schoolDao = new SchoolDao();

    // 科目一覧（学校コードで絞り込み）
    public List<Subject> filter(School school) throws Exception {

        List<Subject> list = new ArrayList<>();

        Connection con = getConnection();

        PreparedStatement st = con.prepareStatement(
            "SELECT subject_cd, subject_name FROM subject WHERE school_cd = ? ORDER BY subject_cd"
        );
        st.setString(1, school.getSchoolCd());

        ResultSet rs = st.executeQuery();

        while (rs.next()) {
            Subject s = new Subject();
            s.setSubjectCd(rs.getString("subject_cd"));
            s.setSubjectName(rs.getString("subject_name"));
            s.setSchool(school);
            list.add(s);
        }

        rs.close();
        st.close();
        con.close();

        return list;
    }

    // 科目一覧（全件取得）
    public List<Subject> findAll() throws Exception {

        List<Subject> list = new ArrayList<>();

        Connection con = getConnection();

        PreparedStatement st = con.prepareStatement(
            "SELECT subject_cd, subject_name, school_cd FROM subject ORDER BY subject_cd"
        );

        ResultSet rs = st.executeQuery();

        while (rs.next()) {
            Subject s = new Subject();
            s.setSubjectCd(rs.getString("subject_cd"));
            s.setSubjectName(rs.getString("subject_name"));

            School school = schoolDao.get(rs.getString("school_cd"));
            s.setSchool(school);

            list.add(s);
        }

        rs.close();
        st.close();
        con.close();

        return list;
    }

    // 科目1件取得
    public Subject get(String subjectCd, School school) throws Exception {

        Subject subject = null;

        Connection con = getConnection();

        PreparedStatement st = con.prepareStatement(
            "SELECT subject_cd, subject_name FROM subject WHERE subject_cd = ? AND school_cd = ?"
        );
        st.setString(1, subjectCd);
        st.setString(2, school.getSchoolCd());

        ResultSet rs = st.executeQuery();

        if (rs.next()) {
            subject = new Subject();
            subject.setSubjectCd(rs.getString("subject_cd"));
            subject.setSubjectName(rs.getString("subject_name"));
            subject.setSchool(school);
        }

        rs.close();
        st.close();
        con.close();

        return subject;
    }

    // 科目登録
    public int insert(Subject subject) throws Exception {

        Connection con = getConnection();

        PreparedStatement st = con.prepareStatement(
            "INSERT INTO subject (school_cd, subject_cd, subject_name) VALUES (?, ?, ?)"
        );
        st.setString(1, subject.getSchool().getSchoolCd());
        st.setString(2, subject.getSubjectCd());
        st.setString(3, subject.getSubjectName());

        int line = st.executeUpdate();

        st.close();
        con.close();

        return line;
    }

    // 科目更新
    public int update(Subject subject) throws Exception {

        Connection con = getConnection();

        PreparedStatement st = con.prepareStatement(
            "UPDATE subject SET subject_name = ? WHERE subject_cd = ? AND school_cd = ?"
        );
        st.setString(1, subject.getSubjectName());
        st.setString(2, subject.getSubjectCd());
        st.setString(3, subject.getSchool().getSchoolCd());

        int line = st.executeUpdate();

        st.close();
        con.close();

        return line;
    }

    // 科目削除
    public int delete(String subjectCd, School school) throws Exception {

        Connection con = getConnection();

        PreparedStatement st = con.prepareStatement(
            "DELETE FROM subject WHERE subject_cd = ? AND school_cd = ?"
        );
        st.setString(1, subjectCd);
        st.setString(2, school.getSchoolCd());

        int line = st.executeUpdate();

        st.close();
        con.close();

        return line;
    }
}