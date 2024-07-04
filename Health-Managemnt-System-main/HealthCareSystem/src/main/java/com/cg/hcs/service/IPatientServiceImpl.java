package com.cg.hcs.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.cg.hcs.dao.IPatientRepository;
import com.cg.hcs.exception.PatientException;
import com.cg.hcs.model.Patient;
import com.cg.hcs.model.TestResult;

@Service
public class IPatientServiceImpl implements IPatientService {
	@Autowired
	IPatientRepository repo;

	@Override
	public ResponseEntity<Patient> registerPatient(Patient patient) throws PatientException {
		if (repo.existsById(patient.getPatientid())) {
			throw new PatientException("Patient with the given Id already Exists");
		}
		repo.save(patient);
		return new ResponseEntity<Patient>(patient, HttpStatus.OK);
	}

	@Override
	public ResponseEntity<Patient> updatePatientDetails(Patient patient) throws PatientException {
		if (repo.existsById(patient.getPatientid())) {
			repo.save(patient);
			return new ResponseEntity<Patient>(patient, HttpStatus.OK);
		}
		throw new PatientException("Patient with the given Id doesn't Exists");

	}

	@Override
	public ResponseEntity<Patient> viewPatient(String patientUserName) throws PatientException {
		Patient p = repo.viewPatient(patientUserName);
		if (p != null)
			return new ResponseEntity<Patient>(p, HttpStatus.OK);

		throw new PatientException("Patient with the given Id doesn't Exists");

	}

	@Override
	public ResponseEntity<List<TestResult>> getAllTestResult(String patientUserName) throws PatientException {
		List<TestResult> testres = repo.getAllTestResult(patientUserName);
		if (testres.size() == 0)
			throw new PatientException("No test result available for the patient");
		return new ResponseEntity<List<TestResult>>(testres, HttpStatus.OK);
	}

	@Override
	public ResponseEntity<TestResult> viewTestResult(int TestresultId) throws PatientException {
		TestResult test = repo.viewTestResult(TestresultId);
		if (test == null) {
			throw new PatientException("Test with the given test id doesn't Exists");
		}
		return new ResponseEntity<TestResult>(test, HttpStatus.OK);

	}

	public Patient getPatientById(int patientId) {
		return repo.findById(patientId).get();
	}

}
