package com.cg.hcs.controller;

import java.time.LocalDateTime;
import java.util.List;
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
import com.cg.hcs.exception.PatientException;
import com.cg.hcs.model.Appointment;
import com.cg.hcs.model.Patient;
import com.cg.hcs.service.IAppointmentServiceImpl;
import com.cg.hcs.service.IPatientServiceImpl;

@RestController
@RequestMapping("/appointment")
@CrossOrigin("*")
public class AppointmentController {
	@Autowired
	IAppointmentServiceImpl impl;
	@Autowired
	IPatientServiceImpl patientImpl;
	
	@PostMapping("{patientId}/addAppointment")
	public ResponseEntity<Appointment>  addAppointment(@PathVariable("patientId") int patientId,
			@RequestBody Appointment appointment) throws AppointmentException, PatientException{
		Patient p=patientImpl.getPatientById(patientId);
		ResponseEntity<Appointment> re=impl.addAppointment(appointment);
		p.getAppointments().add(appointment);
		appointment.setPatient(p);
		patientImpl.updatePatientDetails(p);
		return re;
	}
	
	@GetMapping("/viewAppointmentsByname/{name}")
	public ResponseEntity<Set<Appointment>> viewAppointments(@PathVariable("name") String patientName) throws AppointmentException{
		return impl.viewAppointments(patientName);
	}
	
	@GetMapping("/viewAppointment/{id}")
	public ResponseEntity<Appointment> viewAppointment(@PathVariable("id") int appointmentId) throws AppointmentException{
		return impl.viewAppointment(appointmentId);
	}
	
	@PutMapping("{id}/updateAppointment")
	public ResponseEntity<Appointment> updateAppointment(@PathVariable("id") int id
			,@RequestBody Appointment appointment) throws AppointmentException, PatientException{
		Patient p=patientImpl.getPatientById(id);
		ResponseEntity<Appointment> re=impl.updateAppointment(appointment);
		p.getAppointments().add(appointment);
		appointment.setPatient(p);
		patientImpl.updatePatientDetails(p);
		return re;
	}
	
	@GetMapping("/getAppointments/{id}/{test}/{status}")
	public ResponseEntity<List<Appointment>> getAppointmentList(@PathVariable("id") int centerId, @PathVariable("test") String test, 
			@PathVariable("status") String status) throws AppointmentException{
		return impl.getAppointmentList(centerId, test, status);
	}
	
	@DeleteMapping("/removeAppointment")
	public ResponseEntity<Appointment> removeAppointment(@RequestBody Appointment appointment) throws AppointmentException{
		return impl.removeAppointment(appointment);
	}
	
	@GetMapping("/getAllApointments")
	public ResponseEntity<List<Appointment>> getAllAppointments() throws AppointmentException{
		return new ResponseEntity<List<Appointment>>(impl.getAllAppointments(), HttpStatus.OK);
	}
	
	@ExceptionHandler(Exception.class)
	public ResponseEntity<ErrorInfo> handleTestResultException(Exception e, HttpServletRequest req){
		ErrorInfo info=new ErrorInfo(LocalDateTime.now(), e.getMessage(), req.getRequestURI());
		return new ResponseEntity<ErrorInfo>(info, HttpStatus.NOT_FOUND);
	}
}