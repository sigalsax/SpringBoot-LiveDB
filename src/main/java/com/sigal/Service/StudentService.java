package com.sigal.Service;

import com.sigal.Dao.StudentDao;
import com.sigal.Entity.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
// take requests from controller manage logic, switching between databases

public class StudentService {

    @Autowired
    @Qualifier("mysql") //change according to the different classes implementation same interface
    private StudentDao studentDao;

    // return students in hashmap
    public Collection<Student> getAllStudents(){
        return this.studentDao.getAllStudents();
    }
    public Student getStudentById(int id){
        return this.studentDao.getStudentById(id);
    }

    public void removeStudentById(int id) {
        this.studentDao.removeStudentById(id);
    }
    public void updateStudent(Student student){
        this.studentDao.updateStudent(student);
    }

    public void insertStudent(Student student) {
        this.studentDao.insertStudentToDb(student);
    }
}
