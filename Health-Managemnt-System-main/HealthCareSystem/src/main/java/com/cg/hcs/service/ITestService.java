package com.cg.hcs.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.cg.hcs.exception.TestException;
import com.cg.hcs.model.DiagnosticTest;


public interface ITestService {
	public ResponseEntity<DiagnosticTest> addTest(DiagnosticTest test) throws TestException;
	public ResponseEntity<DiagnosticTest> updateTest(DiagnosticTest test)throws TestException;
	public ResponseEntity<DiagnosticTest> removeTest(DiagnosticTest test) throws TestException;
	public ResponseEntity<List<DiagnosticTest>> viewAllTest(String criteria)throws TestException;
}