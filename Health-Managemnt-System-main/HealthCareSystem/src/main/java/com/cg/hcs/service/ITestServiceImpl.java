package com.cg.hcs.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;


import com.cg.hcs.dao.ITestRepository;
import com.cg.hcs.exception.TestException;
import com.cg.hcs.model.DiagnosticTest;


@Service
public class ITestServiceImpl implements ITestService{
	@Autowired
	ITestRepository repo;
	
	public  ResponseEntity<DiagnosticTest> addTest(DiagnosticTest test) throws TestException{
		if(repo.existsById(test.getId())) {
			throw new TestException("Test with the given Id already Exists");
		}
		repo.save(test);
		return new ResponseEntity<DiagnosticTest>(test, HttpStatus.OK);
		
	}
	

	public ResponseEntity<DiagnosticTest> updateTest(DiagnosticTest test) throws TestException{
		if(repo.existsById(test.getId())) {
			repo.save(test);
			return new ResponseEntity<DiagnosticTest>(test, HttpStatus.OK);
		}
		throw new TestException("Test with the given Id doesn't Exists");
	}
	

	public ResponseEntity<DiagnosticTest> removeTest(DiagnosticTest test) throws TestException {
		if(repo.existsById(test.getId())) {
			repo.deleteById(test.getId());
			return new ResponseEntity<DiagnosticTest>(test, HttpStatus.OK);
			
		}
		throw new TestException("Test with the given Id doesn't Exists");
	}
	
	public ResponseEntity<List<DiagnosticTest>> viewAllTest(String criteria) throws TestException{
		List<DiagnosticTest> d=repo.viewAllTest(criteria);
		if(d.size()==0) {
			throw new TestException("Test with the given criteria doesn't Exists");
		}
		return new ResponseEntity<List<DiagnosticTest>>(d,HttpStatus.OK);
	}
}
