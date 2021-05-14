package com.project.consultant.service;

import com.project.consultant.model.Student;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface StudentService {
    Student getStudentInfo(int id);
    Student saveStudent(Student student);
    List<Student> ListStudents();
    Student deleteStudent(int id);
    Student updateStudent(Student student);
    Student findByUsername(String username);
}
