package com.cg.hcs.controller;

import java.time.LocalDateTime;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cg.hcs.exception.AppointmentException;
import com.cg.hcs.exception.ErrorInfo;
import com.cg.hcs.exception.TestResultException;
import com.cg.hcs.model.Appointment;
import com.cg.hcs.model.TestResult;
import com.cg.hcs.service.IAppointmentServiceImpl;
import com.cg.hcs.service.ITestResultServiceImpl;

@RestController
@RequestMapping("/testResult")
@CrossOrigin("*")
public class TestResultController {
	@Autowired
	ITestResultServiceImpl impl;
	@Autowired
	IAppointmentServiceImpl appointimpl;
	
	@PostMapping("{id}/addTestResult")
	public ResponseEntity<TestResult> addTestResult(@PathVariable("id") int id,
			@RequestBody TestResult tr) throws TestResultException, AppointmentException {
		Appointment a=appointimpl.getAppointmentById(id);
		ResponseEntity<TestResult> re=impl.addTestResult(tr);
		tr.setAppointment(a);
		a.getTestResult().add(tr);
		appointimpl.updateAppointment(a);
		return re;
	}
	
	@PutMapping("/updateResult")
	public ResponseEntity<TestResult> updateResult(@RequestBody TestResult tr) throws TestResultException{
		ResponseEntity<TestResult> re=impl.updateResult(tr);
		return re;
	}
	
	@DeleteMapping("/removeTestResult/{id}")
	public ResponseEntity<TestResult> removeTestResult(@PathVariable("id") int id) throws TestResultException{
		return impl.removeTestResult(id);
	}
	
	@GetMapping("/viewResultsByPatient/{id}")
	public ResponseEntity<Set<TestResult>> viewResultsByPatient(@PathVariable("id") int id) throws TestResultException{
		return impl.viewResultsByPatient(id);
	}
	
	@ExceptionHandler(Exception.class)
	public ResponseEntity<ErrorInfo> handleTestResultException(Exception e, HttpServletRequest req){
		ErrorInfo info=new ErrorInfo(LocalDateTime.now(), e.getMessage(), req.getRequestURI());
		return new ResponseEntity<ErrorInfo>(info, HttpStatus.NOT_FOUND);
	}
}
