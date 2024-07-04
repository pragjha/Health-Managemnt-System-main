package com.cg.hcs.controller;

import java.time.LocalDateTime;
import java.util.List;

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
import com.cg.hcs.exception.DiagnosticCenterException;
import com.cg.hcs.exception.DiagnosticTestException;
import com.cg.hcs.exception.ErrorInfo;
import com.cg.hcs.exception.TestException;
import com.cg.hcs.model.Appointment;
import com.cg.hcs.model.DiagnosticCenter;
import com.cg.hcs.model.DiagnosticTest;
import com.cg.hcs.service.IAppointmentServiceImpl;
import com.cg.hcs.service.IDiagnosticCenterServiceImpl;
import com.cg.hcs.service.IDiagnosticTestServiceImpl;
import com.cg.hcs.service.ITestServiceImpl;

@RestController
@RequestMapping("/diagnostictest")
@CrossOrigin("*")
public class DiagnosticTestController {
	@Autowired
	IDiagnosticTestServiceImpl impl;
	@Autowired
	IAppointmentServiceImpl appointmentImpl;
	@Autowired
	IDiagnosticCenterServiceImpl centerImpl;
	@Autowired
	ITestServiceImpl testimpl;
	
	@GetMapping("/getAllTests")
	public ResponseEntity<List<DiagnosticTest>> getAllTest() throws DiagnosticTestException{
		return impl.getAllTest();
	}
	
	@PostMapping("{id}/addTest")
	public ResponseEntity<DiagnosticTest> addNewTest(
			@PathVariable("id") int id,
			@RequestBody DiagnosticTest test) throws DiagnosticTestException, DiagnosticCenterException, AppointmentException, TestException{
		Appointment a=appointmentImpl.getAppointmentById(id);
		DiagnosticCenter c=a.getDiagnosticCenter();
		c.getTests().add(test);
		a.getDiagnosticTests().add(test);
		appointmentImpl.updateAppointment(a);
		return new ResponseEntity<DiagnosticTest>(test, HttpStatus.OK);
	}

	
	@GetMapping("/getTests/{id}")
	public ResponseEntity<List<DiagnosticTest>> getTestsOfDiagnosticCenter(@PathVariable("id") int centerid) throws DiagnosticTestException{
		return impl.getTestsOfDiagnosticCenter(centerid);
	
	}
	
	@PutMapping("/updateTests")
	public ResponseEntity<DiagnosticTest> updateTestDetail(
			@RequestBody DiagnosticTest test) throws DiagnosticTestException, DiagnosticCenterException, AppointmentException{
		return impl.updateTestDetail(test);
	}
	
	@DeleteMapping("/removeTest/{id}")
	public ResponseEntity<DiagnosticTest> removeTestFromDiagnosticCenter(@PathVariable("id") int centerid, 
			@RequestBody DiagnosticTest test)throws DiagnosticTestException, DiagnosticCenterException, AppointmentException{
		DiagnosticCenter c=centerImpl.getDiagnosticCenterById(centerid).getBody();
		c.getTests().removeIf(t->t.getId()==test.getId());
		centerImpl.updateDiagnosticCenter(c);
		List<Appointment> al=appointmentImpl.getAllAppointments();
		for(Appointment a:al) {
			if(a.getDiagnosticCenter().getId()==c.getId()) {
				for(DiagnosticTest t:a.getDiagnosticTests()) {
					if(t.getId()==test.getId()) {
						a.getDiagnosticTests().remove(t);
						appointmentImpl.updateAppointment(a);
						break;
					}
				}
			}
		}
		ResponseEntity<DiagnosticTest> removeTestFromDiagnosticCenter = impl.removeTestFromDiagnosticCenter(centerid, test);
		return removeTestFromDiagnosticCenter;
	
	}
	
	
	@ExceptionHandler(Exception.class)
	public ResponseEntity<ErrorInfo> handleDiagnosticTestException(Exception e, HttpServletRequest req){
		ErrorInfo info=new ErrorInfo(LocalDateTime.now(), e.getMessage(), req.getRequestURI());
		return new ResponseEntity<ErrorInfo>(info, HttpStatus.NOT_FOUND);
	}

}