package com.cg.hcs.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.cg.hcs.exception.PatientException;
import com.cg.hcs.model.Patient;
import com.cg.hcs.model.TestResult;

public interface IPatientService{
	public ResponseEntity<Patient> registerPatient(Patient patient) throws PatientException;
	public ResponseEntity<Patient> updatePatientDetails(Patient patient)throws PatientException;
	public ResponseEntity<Patient> viewPatient(String patientUserName) throws PatientException;
	public ResponseEntity<List<TestResult>>  getAllTestResult(String patientUserName)throws PatientException;
	public ResponseEntity<TestResult> viewTestResult(int TestresultId)throws PatientException;
}

