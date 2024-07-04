package com.cg.hcs.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.cg.hcs.model.Appointment;
import com.cg.hcs.model.DiagnosticCenter;
import com.cg.hcs.model.DiagnosticTest;

@Repository
public interface IDiagnosticCenterRepository extends JpaRepository<DiagnosticCenter, Integer> {
	@Query("select t from DiagnosticCenter c join c.tests t where c.id=?1 and t.testName=?2")
	public DiagnosticTest viewTestDetails(int diagnosticCenterId,String testName);
	
	@Query("select c from DiagnosticCenter c where c.name=?1")
	public DiagnosticCenter getDiagnosticCenter(String centerName);
	
	@Query("select a from DiagnosticCenter c join c.appointments a where c.name=?1")
	public List<Appointment> getListOfAppointments(String centerName);
}
