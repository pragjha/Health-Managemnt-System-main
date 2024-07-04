package com.cg.hcs.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.cg.hcs.model.DiagnosticTest;

@Repository
public interface ITestRepository extends JpaRepository<DiagnosticTest,Integer> {
	@Query("select b from DiagnosticTest b where b.testName=?1")
	public List<DiagnosticTest> viewAllTest(String criteria);
}
