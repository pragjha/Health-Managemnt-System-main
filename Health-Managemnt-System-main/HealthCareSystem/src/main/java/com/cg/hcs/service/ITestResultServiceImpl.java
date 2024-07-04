package com.cg.hcs.service;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.cg.hcs.dao.ITestResultRepository;
import com.cg.hcs.exception.TestResultException;
import com.cg.hcs.model.TestResult;

@Service
public class ITestResultServiceImpl implements ITestResultService{
	@Autowired
	ITestResultRepository repo;
	@Override
	public ResponseEntity<TestResult> addTestResult(TestResult tr) throws TestResultException {
		if(repo.existsById(tr.getId())) {
			throw new TestResultException("TestResult with the given Id already Exists");
		}
		repo.save(tr);
		return new ResponseEntity<TestResult>(tr, HttpStatus.OK);
	}
	
	@Override
	public ResponseEntity<TestResult> updateResult(TestResult tr) throws TestResultException{
		if(repo.existsById(tr.getId())) {
			
			TestResult t=new TestResult();
			t.setId(tr.getId());
			t.setCondition(tr.getCondition());
			t.setTestReading(tr.getTestReading());
			t.setAppointment(repo.getById(tr.getId()).getAppointment());
			repo.deleteById(tr.getId());
			repo.save(t);
			return new ResponseEntity<TestResult>(tr, HttpStatus.OK);
		}
		throw new TestResultException("TestResult with the given Id doesn't Exists");
	}
	
	@Override
	public ResponseEntity<TestResult> removeTestResult(int id) throws TestResultException{
		if(repo.existsById(id)) {
			TestResult tr = repo.findById(id).get();
			tr.setAppointment(null);
			repo.save(tr);
			repo.deleteById(tr.getId());
			return new ResponseEntity<TestResult>(tr, HttpStatus.OK);
		}
		throw new TestResultException("TestResult with the given Id doesn't Exists");
	}
	
	@Override
	public ResponseEntity<Set<TestResult>> viewResultsByPatient(int id) throws TestResultException{
		Set<TestResult> t=repo.viewResultsByPatient(id);
		if(t.size()==0) {
			throw new TestResultException("TestResult with the given Patient Details doesn't Exists");
		}
		return new ResponseEntity<Set<TestResult>>(t, HttpStatus.OK);
	}

	public TestResult getResultById(Integer id) {
		return repo.findById(id).get();
	}
}