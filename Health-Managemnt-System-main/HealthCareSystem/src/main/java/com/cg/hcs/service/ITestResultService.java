package com.cg.hcs.service;

import java.util.Set;

import org.springframework.http.ResponseEntity;

import com.cg.hcs.exception.TestResultException;
import com.cg.hcs.model.TestResult;

public interface ITestResultService {
	public ResponseEntity<TestResult> addTestResult(TestResult tr) throws TestResultException;
	public ResponseEntity<TestResult> updateResult(TestResult tr) throws TestResultException;
	public ResponseEntity<TestResult> removeTestResult(int id) throws TestResultException;
	public ResponseEntity<Set<TestResult>> viewResultsByPatient(int patientId)throws TestResultException;
}
