package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import bean.School;
import bean.Test;

public class TestDao extends Dao {


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


    public List<Test> findBySubject(School school, String subjectCd) throws Exception {

        List<Test> list = new ArrayList<>();

        Connection con = getConnection();

        PreparedStatement st = con.prepareStatement(
            "SELECT * FROM test WHERE school_cd=? AND subject_cd=? ORDER BY class_num, student_no, no"
        );

        st.setString(1, school.getSchoolCd());
        st.setString(2, subjectCd);

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
    /**
     * 学校に紐づく全成績を取得
     */
    public List<Test> findAll(School school) throws Exception {

        List<Test> list = new ArrayList<>();

        Connection con = getConnection();

        PreparedStatement st = con.prepareStatement(
            "SELECT * FROM test WHERE school_cd=? ORDER BY subject_cd, class_num, student_no, no"
        );

        st.setString(1, school.getSchoolCd());

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

}
