package com.cg.hcs.dao;

import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.cg.hcs.model.TestResult;

@Repository
public interface ITestResultRepository extends JpaRepository<TestResult, Integer> {
	@Query("select t from TestResult t join t.appointment a join a.patient p where p.patientid=?1 ")
	public Set<TestResult> viewResultsByPatient(int patientId);
}