package com.project.consultant.service.impl;

import com.project.consultant.model.Consultant;
import com.project.consultant.repository.ConsultantRepository;
import com.project.consultant.service.ConsultantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ConsultantServiceImpl implements ConsultantService {

    @Autowired
    private ConsultantRepository consultantRepository;

    @Override
    public Consultant getConsultantInfo(int id) {
        return consultantRepository.findById(id).get();
    }

    @Override
    public Consultant saveConsultant(Consultant consultant) {
        return consultantRepository.save(consultant);
    }

    @Override
    public List<Consultant> ListConsultants() {
        List<Consultant> consultantList = new ArrayList<>();
        consultantRepository.findAll().forEach(consultantList::add);
        return consultantList;
    }

    @Override
    public Consultant deleteConsultant(int id) {
        Consultant consultant = consultantRepository.findById(id).get();
        consultantRepository.delete(consultant);
        return consultant;
    }

    @Override
    public Consultant updateConsultant(Consultant consultant) {
        return consultantRepository.save(consultant);
    }

    @Override
    public Consultant findByUsername(String username) {
        try{
            return consultantRepository.findByUsername(username).get();
        }
        catch (Exception e){
            System.out.println("Error ConsultantService: " + e);
            e.printStackTrace();
            return new Consultant();
        }
    }
}
