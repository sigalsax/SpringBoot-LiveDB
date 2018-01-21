package com.sigal.Dao;

import com.sigal.Entity.Student;

import java.util.Collection;

public interface StudentDao {
    // return students in hashmap
    // uses interface in order to switch implementating with 1 line of code (Qualifiers) in Service
    Collection<Student> getAllStudents();

    Student getStudentById(int id);

    void removeStudentById(int id);

    void updateStudent(Student student);

    void insertStudentToDb(Student student);
}
