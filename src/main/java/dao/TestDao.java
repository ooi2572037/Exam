package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import bean.School;
import bean.Test;

public class TestDao extends Dao {

    public boolean save(Test test) throws Exception {

        Connection con = getConnection();
        String sql = "MERGE INTO test " +
                     "(student_no, subject_cd, school_cd, no, point, class_num) " +
                     "KEY(student_no, subject_cd, school_cd, no) " +
                     "VALUES (?, ?, ?, ?, ?, ?)";

        PreparedStatement st = con.prepareStatement(sql);

        try {
            st.setString(1, test.getStudentNo());
            st.setString(2, test.getSubjectCd());
            st.setString(3, test.getSchoolCd());  
            st.setInt(4, test.getNo());
            st.setInt(5, test.getPoint());
            st.setString(6, test.getClassNum());

            int count = st.executeUpdate();
            return count > 0;

        } finally {
            st.close();
            con.close();
        }
    }

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
    public List<Test> findByStudent(School school, String studentNo) throws Exception {

        List<Test> list = new ArrayList<>();

        String sql = 
            "SELECT t.student_no, t.subject_cd, t.school_cd, t.no, t.point, s.student_name " +
            "FROM test t " +
            "JOIN student s ON t.student_no = s.student_no AND t.school_cd = s.school_cd " +
            "WHERE t.school_cd=? AND t.student_no=? " +
            "ORDER BY t.subject_cd, t.no";

        try (Connection con = getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, school.getSchoolCd());
            ps.setString(2, studentNo);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Test t = new Test();
                t.setStudentNo(rs.getString("student_no"));
                t.setSubjectCd(rs.getString("subject_cd"));
                t.setSchoolCd(rs.getString("school_cd"));
                t.setNo(rs.getInt("no"));
                t.setPoint(rs.getInt("point"));

                t.setStudentName(rs.getString("student_name"));

                list.add(t);
            }
        }

        return list;
    }


}
