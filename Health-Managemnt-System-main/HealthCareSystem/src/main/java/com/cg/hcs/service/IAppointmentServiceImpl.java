package com.cg.hcs.service;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.cg.hcs.dao.IAppointmentRepository;
import com.cg.hcs.exception.AppointmentException;
import com.cg.hcs.model.Appointment;

@Service
public class IAppointmentServiceImpl implements IAppointmentService {
	@Autowired
	IAppointmentRepository repo;
	
	@Override
	public ResponseEntity<Appointment>  addAppointment(Appointment appointment) throws AppointmentException {
		if(repo.existsById(appointment.getId())) {
			throw new AppointmentException("Appointment already exist");
		}
		repo.save(appointment);
		return new ResponseEntity<Appointment>(appointment,HttpStatus.OK);
	}

	@Override
	public ResponseEntity<Set<Appointment>> viewAppointments(String patientName) throws AppointmentException {
		Set<Appointment> appointments=repo.viewAppointments(patientName);
		if(appointments.size()==0) {
			throw new AppointmentException("No appointments are available for the given patient name");
		}
		return new ResponseEntity<Set<Appointment>>(appointments, HttpStatus.OK);
	}

	@Override
	public ResponseEntity<Appointment> viewAppointment(int appointmentId) throws AppointmentException{
		if(repo.existsById(appointmentId)) {
			return new ResponseEntity<Appointment>(repo.findById(appointmentId).get(), HttpStatus.OK);
		}
		throw new AppointmentException("No appointment available for the given appointment Id");
	}

	@Override
	public ResponseEntity<Appointment> updateAppointment(Appointment appointment) throws AppointmentException{
		if(repo.existsById(appointment.getId())) {
			repo.save(appointment);
			return new ResponseEntity<Appointment>(appointment, HttpStatus.OK);
		}
		throw new AppointmentException("No appointment available for the given appointment Id");
	}

	@Override
	public ResponseEntity<List<Appointment>> getAppointmentList(int centerId, String test, String status) throws AppointmentException{
		List<Appointment> appointmnets= repo.getAppointmentList(centerId, test, status);
		if(appointmnets.size()==0) {
			throw new AppointmentException("No appointments found for the given details");
		}
		return new ResponseEntity<List<Appointment>>(appointmnets, HttpStatus.OK);
	}

	@Override
	public ResponseEntity<Appointment> removeAppointment(Appointment appointment) throws AppointmentException{
		if(repo.existsById(appointment.getId())) {
			Appointment a=repo.findById(appointment.getId()).get();
			a.setDiagnosticCenter(null);
			a.setDiagnosticTests(null);
			a.setPatient(null);
			a.setTestResult(null);
			repo.save(a);
			repo.deleteById(a.getId());
			return new ResponseEntity<Appointment>(appointment, HttpStatus.OK);
		}
		throw new AppointmentException("No appointment available for the given details");
	}

	public Appointment getAppointmentById(int id) {
		return repo.findById(id).get();
	}
	
	public List<Appointment> getAllAppointments() {
		return repo.findAll();
	}
}
