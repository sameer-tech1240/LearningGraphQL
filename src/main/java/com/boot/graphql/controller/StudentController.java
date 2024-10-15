package com.boot.graphql.controller;

import com.boot.graphql.entity.Student;
import com.boot.graphql.service.IStudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class StudentController {

    @Autowired
    private IStudentService iStudentService;

    @QueryMapping
    public List<Student> getAllStudents() {
        return iStudentService.getAllStudents();
    }
}
