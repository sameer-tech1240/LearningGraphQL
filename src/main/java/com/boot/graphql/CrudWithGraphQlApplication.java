package com.boot.graphql;

import com.boot.graphql.entity.Student;
import com.boot.graphql.repo.StudentRepository;
import jakarta.annotation.PostConstruct;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Arrays;
import java.util.List;

@SpringBootApplication
@Log4j2
public class CrudWithGraphQlApplication {
    /* @Autowired
     private StudentRepository studentRepository;*/
    public static void main(String[] args) {
        SpringApplication.run(CrudWithGraphQlApplication.class, args);
        log.info("Spring Boot GraphQL Application Started...");
    }
    /*@PostConstruct
    public void init() {
        List<Student> students = List.of(
                new Student("John Doe", "john@example.com", "1234567890", "New York", "password123"),
                new Student("Jane Smith", "jane@example.com", "0987654321", "Los Angeles", "password456"),
                new Student("Alice Johnson", "alice@example.com", "1122334455", "Chicago", "password789"),
                new Student("Robert Brown", "robert@example.com", "6677889900", "Houston", "password321"),
                new Student("Emily Davis", "emily@example.com", "2233445566", "Phoenix", "password654"),
                new Student("Michael Wilson", "michael@example.com", "9988776655", "Philadelphia", "password987"),
                new Student("Sarah Lee", "sarah@example.com", "4455667788", "San Antonio", "password741"),
                new Student("David Clark", "david@example.com", "5544332211", "San Diego", "password852"),
                new Student("Laura Martinez", "laura@example.com", "6677881122", "Dallas", "password963"),
                new Student("Daniel Walker", "daniel@example.com", "7788993344", "San Jose", "password159")
        );

        studentRepository.saveAll(students);
        System.out.println("Preloaded 10 student records.");
    }*/
}
