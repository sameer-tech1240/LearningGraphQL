package com.boot.graphql.service;

import com.boot.graphql.entity.Student;
import com.boot.graphql.repo.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService implements IStudentService {

    @Autowired
    private StudentRepository repository;


    @Override
    public List<Student> getAllStudents() {
        return repository.findAll();
    }
}
