package com.cg.hcs.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.cg.hcs.exception.DiagnosticTestException;
import com.cg.hcs.model.DiagnosticTest;

public interface IDiagnosticTestService{
	public ResponseEntity<List<DiagnosticTest>> getAllTest() throws DiagnosticTestException;
	public ResponseEntity<DiagnosticTest> addNewTest(DiagnosticTest test) throws DiagnosticTestException;
	public ResponseEntity<List<DiagnosticTest>> getTestsOfDiagnosticCenter(int centerid) throws DiagnosticTestException;
	public  ResponseEntity<DiagnosticTest> updateTestDetail(DiagnosticTest test) throws DiagnosticTestException;
	public ResponseEntity<DiagnosticTest> removeTestFromDiagnosticCenter(int centerid,DiagnosticTest test) throws DiagnosticTestException;
}

