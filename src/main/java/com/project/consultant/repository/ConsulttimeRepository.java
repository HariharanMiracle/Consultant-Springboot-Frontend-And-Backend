package com.project.consultant.repository;

import com.project.consultant.model.Consulttime;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.util.List;

@Repository
public interface ConsulttimeRepository extends CrudRepository<Consulttime, Integer> {
    @Query(value = "SELECT * FROM consulttime c WHERE c.consultantid = ? AND c.date = ?", nativeQuery = true)
    public List<Consulttime> findByConsultantidAndDate(int consultantId, Date date);

    @Query(value="SELECT * FROM consulttime c WHERE c.consultantid = ? ORDER BY DATE DESC", nativeQuery = true)
    public List<Consulttime> findConsulttimeByconsultantid(int consultantId);

    @Query(value="SELECT * FROM consulttime c WHERE c.consultantid = ? AND c.date > SYSDATE() ORDER BY DATE DESC", nativeQuery = true)
    public List<Consulttime> findConsulttimeByconsultantidAndAfterToday(int consultantId);
}
