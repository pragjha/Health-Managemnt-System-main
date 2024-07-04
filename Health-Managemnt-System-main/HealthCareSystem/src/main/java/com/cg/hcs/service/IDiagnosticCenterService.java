package com.cg.hcs.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.cg.hcs.exception.DiagnosticCenterException;
import com.cg.hcs.model.Appointment;
import com.cg.hcs.model.DiagnosticCenter;
import com.cg.hcs.model.DiagnosticTest;

public interface IDiagnosticCenterService {
	public ResponseEntity<List<DiagnosticCenter>> getAllDiagnosticCenters() throws DiagnosticCenterException;	
	public ResponseEntity<DiagnosticCenter> addDiagnosticCenter(DiagnosticCenter diagnosticCenter) throws DiagnosticCenterException;
	public ResponseEntity<DiagnosticCenter> getDiagnosticCenterById(int diagnosticCenterId) throws DiagnosticCenterException;
	public ResponseEntity<DiagnosticCenter> updateDiagnosticCenter(DiagnosticCenter diagnosticCenter) throws DiagnosticCenterException;
	public ResponseEntity<DiagnosticTest> viewTestDetails(int diagnosticCenterId,String testName) throws DiagnosticCenterException;
	public ResponseEntity<DiagnosticTest> addTest(int diagnosticCenterId,DiagnosticTest test) throws DiagnosticCenterException;
	public ResponseEntity<DiagnosticCenter> getDiagnosticCenter(String centerName) throws DiagnosticCenterException;
	public ResponseEntity<DiagnosticCenter> removeDiagnosticCenter(int id) throws DiagnosticCenterException;
	public ResponseEntity<List<Appointment>> getListOfAppointments(String centerName) throws DiagnosticCenterException;
}
