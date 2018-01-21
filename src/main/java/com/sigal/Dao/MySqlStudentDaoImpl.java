package com.sigal.Dao;

import com.sigal.Entity.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.List;

// responsible to connect to MySql database
@Repository("mysql")
public class MySqlStudentDaoImpl implements StudentDao {
    //use JDBC temp to perform queries
    @Autowired
    private JdbcTemplate jdbcTemplate;

    private static class StudentRowMapper implements RowMapper<Student>{

        @Override
        public Student mapRow(ResultSet resultSet, int i) throws SQLException {
            Student student = new Student();
            student.setId(resultSet.getInt("id"));
            student.setName(resultSet.getString("name"));
            student.setCourse(resultSet.getString("course"));
            return student;
        }
    }

    @Override
    public Collection<Student> getAllStudents() {
        // RowMapper will give us row we are interested in
        final String sql = "SELECT id, name, course FROM students";
        List<Student> students = jdbcTemplate.query(sql, new StudentRowMapper());
        return students;
    }

    @Override
    public Student getStudentById(int id) {
        // '?' avoids SQL injection
       // "SELECT id, name, course FROM students WHERE column = value
        final String sql = "SELECT id, name, course FROM students WHERE id = ?";
        Student student = jdbcTemplate.queryForObject(sql, new StudentRowMapper(), id);
        return student;
    }

    @Override
    public void removeStudentById(int id) {
        //"DELETE FROM x WHERE y = ?";
       final String sql= "DELETE FROM students WHERE id = ?";
        jdbcTemplate.update(sql, id);

    }

    @Override
    public void updateStudent(Student student) {
        final String sql= "UPDATE students SET name = ?, course = ? WHERE id= ?";
        // attribute from student passed in
        int id= student.getId();
        final String name= student.getName();
        final String course= student.getCourse();
        // updating student
        jdbcTemplate.update(sql, new Object [] {name, course, id});
    }

    @Override
    public void insertStudentToDb(Student student) {
        final String sql= "INSERT INTO students (name,  course) VALUES (?, ?)";
        final String name= student.getName();
        final String course= student.getCourse();
        jdbcTemplate.update(sql, new Object [] {name, course});
    }
}
