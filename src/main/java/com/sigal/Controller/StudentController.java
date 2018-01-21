package com.sigal.Controller;


import com.sigal.Entity.Student;
import com.sigal.Service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.awt.*;
import java.util.Collection;
//offloads requests to the service, handling all HTTP requests
@RestController
@RequestMapping("/students")
public class StudentController {

    @Autowired
    private StudentService studentService;

    @RequestMapping(method =  RequestMethod.GET)
    // return students in hashmap
    public Collection<Student> getAllStudents(){
        return studentService.getAllStudents();
    }
    //anticipating id from URL
    // http://localhost:8080/students/1 --> Sigal, id 1 is fed into method by URL
    @RequestMapping(value= "/{id}", method= RequestMethod.GET)
    public Student getStudentById(@PathVariable("id") int id){
        return studentService.getStudentById(id);
    }

    @RequestMapping(value= "/{id}", method= RequestMethod.DELETE)
    public void deleteStudentById(@PathVariable("id") int id){
        studentService.removeStudentById(id);
    }
    @RequestMapping(method= RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
    // read body sent to the request, in the form of JSON
    public void deleteStudentById(@RequestBody Student student){
        studentService.updateStudent(student);
    }

    @RequestMapping(method= RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    // read body sent to the request
    public void insertStudentById(@RequestBody Student student){
        studentService.insertStudent(student);
    }
}
