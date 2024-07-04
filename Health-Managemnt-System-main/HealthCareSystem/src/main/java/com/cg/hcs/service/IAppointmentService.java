package com.cg.hcs.service;

import java.util.List;
import java.util.Set;

import org.springframework.http.ResponseEntity;

import com.cg.hcs.exception.AppointmentException;
import com.cg.hcs.model.Appointment;


public interface IAppointmentService {
	public ResponseEntity<Appointment> addAppointment(Appointment appointment) throws AppointmentException;
	public ResponseEntity<Set<Appointment>> viewAppointments(String patientName) throws AppointmentException;
	public ResponseEntity<Appointment> viewAppointment(int appointmentId) throws AppointmentException;
	public ResponseEntity<Appointment> updateAppointment(Appointment appointment) throws AppointmentException;
	public ResponseEntity<List<Appointment>> getAppointmentList(int centerId,String test,String status) throws AppointmentException;
	public ResponseEntity<Appointment> removeAppointment(Appointment appointment) throws AppointmentException;
}
