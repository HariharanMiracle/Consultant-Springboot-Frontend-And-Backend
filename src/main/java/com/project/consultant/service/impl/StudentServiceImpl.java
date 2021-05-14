package com.project.consultant.service.impl;

import com.project.consultant.model.Student;
import com.project.consultant.repository.StudentRepository;
import com.project.consultant.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class StudentServiceImpl implements StudentService {

    @Autowired
    private StudentRepository studentRepository;

    @Override
    public Student getStudentInfo(int id) {
        return studentRepository.findById(id).get();
    }

    @Override
    public Student saveStudent(Student student) {
        return studentRepository.save(student);
    }

    @Override
    public List<Student> ListStudents() {
        List<Student> studentList = new ArrayList<>();
        studentRepository.findAll().forEach(studentList::add);
        return studentList;
    }

    @Override
    public Student deleteStudent(int id) {
        Student student = studentRepository.findById(id).get();
        studentRepository.delete(student);
        return student;
    }

    @Override
    public Student updateStudent(Student student) {
        return studentRepository.save(student);
    }

    @Override
    public Student findByUsername(String username) {
        try{
            return studentRepository.findByUsername(username).get();
        }
        catch (Exception e){
            System.out.println("Error StudentService: " + e);
            e.printStackTrace();
            return new Student();
        }
    }
}
