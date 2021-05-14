package com.project.consultant.repository;

import com.project.consultant.model.Student;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface StudentRepository extends CrudRepository<Student, Integer> {
    public Optional<Student> findByUsername(String username);
}
