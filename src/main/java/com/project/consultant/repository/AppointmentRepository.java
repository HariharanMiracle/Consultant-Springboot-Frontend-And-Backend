package com.project.consultant.repository;

import com.project.consultant.model.Appointment;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AppointmentRepository extends CrudRepository<Appointment, Integer> {
    @Query(value="SELECT * FROM appointment a WHERE a.studentid = ? ORDER BY DATE DESC", nativeQuery = true)
    public List<Appointment> findAppointmentByStudentId(int studentId);

    @Query(value="SELECT * FROM appointment a WHERE a.consultantid = ? ORDER BY DATE DESC", nativeQuery = true)
    public List<Appointment> findAppointmentByConsultantid(int consultantid);
}
