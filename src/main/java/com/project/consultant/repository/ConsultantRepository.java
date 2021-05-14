package com.project.consultant.repository;

import com.project.consultant.model.Consultant;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ConsultantRepository extends CrudRepository<Consultant, Integer> {
    public Optional<Consultant> findByUsername(String username);
}
