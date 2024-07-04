package com.cg.hcs.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.cg.hcs.model.Patient;
import com.cg.hcs.model.TestResult;
@Repository
public interface IPatientRepository extends JpaRepository<Patient, Integer> {
	@Query("select r from Patient p join p.appointments a join a.testResult r where p.name=?1")
	public List<TestResult> getAllTestResult(String patientUserName);
	@Query("select r from Patient p join p.appointments a join a.testResult r where r.id=?1")
	public TestResult viewTestResult(int TestresultId);
	@Query("select p from Patient p where p.name=?1")
	public Patient viewPatient(String patientUserName);
}
