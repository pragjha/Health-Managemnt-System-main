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
import com.cg.hcs.exception.ErrorInfo;
import com.cg.hcs.model.Appointment;
import com.cg.hcs.model.DiagnosticCenter;
import com.cg.hcs.model.DiagnosticTest;
import com.cg.hcs.service.IAppointmentServiceImpl;
import com.cg.hcs.service.IDiagnosticCenterServiceImpl;

@RestController
@RequestMapping("/center")
@CrossOrigin("*")
public class DiagnosticCenterContoller {
	@Autowired
	IDiagnosticCenterServiceImpl impl;
	@Autowired
	IAppointmentServiceImpl appointmentImpl;
	
	@GetMapping("/getCenters")
	public ResponseEntity<List<DiagnosticCenter>> getAllDiagnosticCenters() throws DiagnosticCenterException {
		return impl.getAllDiagnosticCenters();
	}
	
	@PostMapping("/addCenter")
	public ResponseEntity<DiagnosticCenter> addCenter(
			@RequestBody DiagnosticCenter diagnosticCenter)
			throws DiagnosticCenterException {
		ResponseEntity<DiagnosticCenter> re=impl.addDiagnosticCenter(diagnosticCenter);
		return re;
	}
	
	@PostMapping("{id}/addCenter")
	public ResponseEntity<DiagnosticCenter> addDiagnosticCenter(@PathVariable("id") int id,
			@RequestBody DiagnosticCenter diagnosticCenter)
			throws DiagnosticCenterException, AppointmentException {
		boolean flag=false;
		Appointment a=appointmentImpl.getAppointmentById(id);
		if(a.getDiagnosticCenter()==null) {
			a.setDiagnosticCenter(diagnosticCenter);
			diagnosticCenter.getAppointments().add(a);
			for(DiagnosticCenter c:impl.getAllDiagnosticCenters().getBody()) {
				if(c.getId()==diagnosticCenter.getId()) {
					flag=true;
				}
			}
			if(flag==true) {
				impl.updateDiagnosticCenter(diagnosticCenter);
				appointmentImpl.updateAppointment(a);
				return new ResponseEntity<DiagnosticCenter>(diagnosticCenter, HttpStatus.OK);
			}
			ResponseEntity<DiagnosticCenter> addDiagnosticCenter = impl.addDiagnosticCenter(diagnosticCenter);
			appointmentImpl.updateAppointment(a);
			return addDiagnosticCenter;
		}
		throw new AppointmentException("Appointmnet already has a diagnostic Center");
	}

	@GetMapping("/getCenter/{id}")
	public ResponseEntity<DiagnosticCenter> getDiagnosticCenterById(@PathVariable("id") int diagnosticCenterId)
			throws DiagnosticCenterException {
		return impl.getDiagnosticCenterById(diagnosticCenterId);
	}

	@PutMapping("/modifyCenter")
	public ResponseEntity<DiagnosticCenter> updateDiagnosticCenter(
			@RequestBody DiagnosticCenter diagnosticCenter)
			throws DiagnosticCenterException, AppointmentException {
		
		ResponseEntity<DiagnosticCenter> re=impl.updateDiagnosticCenter(diagnosticCenter);

		return re;
	}

	@GetMapping("/testDetail/{id}/{name}")
	public ResponseEntity<DiagnosticTest> viewTestDetails(@PathVariable("id") int diagnosticCenterId,
			@PathVariable("name") String testName) throws DiagnosticCenterException {
		return impl.viewTestDetails(diagnosticCenterId, testName);
	}

	@PostMapping("/addTest/{id}")
	public ResponseEntity<DiagnosticTest> addTest(@PathVariable("id") int diagnosticCenterId,
			@RequestBody DiagnosticTest test) throws DiagnosticCenterException {
		return impl.addTest(diagnosticCenterId, test);
	}

	@GetMapping("/getCenterByName/{name}")
	public ResponseEntity<DiagnosticCenter> getDiagnosticCenter(@PathVariable("name") String centerName)
			throws DiagnosticCenterException {
		return impl.getDiagnosticCenter(centerName);
	}

	@DeleteMapping("/removeCenter/{id}")
	public ResponseEntity<DiagnosticCenter> removeDiagnosticCenter(@PathVariable("id") int id)
			throws DiagnosticCenterException {
		return impl.removeDiagnosticCenter(id);
	}

	@GetMapping("/getAppointmentsByCenter/{name}")
	public ResponseEntity<List<Appointment>> getListOfAppointments(@PathVariable("name") String centerName)
			throws DiagnosticCenterException {
		return impl.getListOfAppointments(centerName);
	}

	@ExceptionHandler(Exception.class)
	public ResponseEntity<ErrorInfo> handleDiagnosticCenterException(Exception e,
			HttpServletRequest req) {
		ErrorInfo info = new ErrorInfo(LocalDateTime.now(), e.getMessage(), req.getRequestURI());
		return new ResponseEntity<ErrorInfo>(info, HttpStatus.NOT_FOUND);
	}
}
