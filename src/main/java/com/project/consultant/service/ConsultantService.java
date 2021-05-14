package com.project.consultant.service;

import com.project.consultant.model.Consultant;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ConsultantService {
    Consultant getConsultantInfo(int id);
    Consultant saveConsultant(Consultant consultant);
    List<Consultant> ListConsultants();
    Consultant deleteConsultant(int id);
    Consultant updateConsultant(Consultant consultant);
    Consultant findByUsername(String username);
}
