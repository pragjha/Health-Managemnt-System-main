package com.cg.hcs.controller;

import java.time.LocalDateTime;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cg.hcs.exception.ErrorInfo;
import com.cg.hcs.exception.PatientException;
import com.cg.hcs.model.Patient;
import com.cg.hcs.model.TestResult;
import com.cg.hcs.service.IPatientServiceImpl;


@RestController
@RequestMapping("/patient")
@CrossOrigin("*")
public class IPatientController {
	@Autowired
	IPatientServiceImpl impl;
	
	@PostMapping("/registerPatient")
	public ResponseEntity<Patient> registerPatient(@RequestBody Patient patient) throws PatientException {
		return impl.registerPatient(patient);
	}
	
	@PutMapping("/updatePatient")
	public ResponseEntity<Patient> updatePatientDetails(@RequestBody Patient patient) throws PatientException{
		return impl.updatePatientDetails(patient);	
	}
	
	@GetMapping("/viewPatient/{name}")
	public ResponseEntity<Patient> viewPatient(@PathVariable("name") String patientUserName) throws PatientException{
		return impl.viewPatient(patientUserName);	
	}
	
	@GetMapping("/getAllTests/{name}")
	public ResponseEntity<List<TestResult>> getAllTestResult(@PathVariable("name") String patientUserName) throws PatientException{
		return impl.getAllTestResult(patientUserName);	
	}
	
	@GetMapping("/viewTestResult/{id}")
	public ResponseEntity<TestResult> viewTestResult(@PathVariable("id") int TestresultId) throws PatientException{
		return impl.viewTestResult(TestresultId);	
	}
	
	@ExceptionHandler(Exception.class)
	public ResponseEntity<ErrorInfo> handleDiagnosticCenterException(Exception e, HttpServletRequest req){
		ErrorInfo info=new ErrorInfo(LocalDateTime.now(), e.getMessage(), req.getRequestURI());
		return new ResponseEntity<ErrorInfo>(info, HttpStatus.NOT_FOUND);
	}

}
