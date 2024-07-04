package com.cg.hcs.model;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "appointment_tbl")
public class Appointment {
	@Id
	@Column(name = "Id")
	private Integer id;
	@Column(name = "Appointmentdate")
	private LocalDate appointmentDate;
	@Column(name="Approvalstatus",length=30)
	private String approvalStatus;
	@JsonIgnore
	@OneToMany(cascade=CascadeType.ALL)
	@JoinTable(name="appointment_tests"
	,joinColumns= {@JoinColumn(name="appointment_id")}
	,inverseJoinColumns= {@JoinColumn(name="test_id")})
	private Set<DiagnosticTest> diagnosticTests = new HashSet<DiagnosticTest>();
	@ManyToOne
	@JoinColumn(name="patient_id")
	private Patient patient;
	@ManyToOne(cascade=CascadeType.ALL)
	private DiagnosticCenter diagnosticCenter;
	@OneToMany(mappedBy="appointment",cascade=CascadeType.ALL)
	private Set<TestResult> testResult = new HashSet<TestResult>();

	public Appointment() {

	}
//	public Appointment(int id, LocalDate appointmentDate, String approvalStatus) {
//		this.id = id;
//		this.appointmentDate = appointmentDate;
//		this.approvalStatus = approvalStatus;
//	}
//	public Appointment(int id, LocalDate appointmentDate, String approvalStatus, Set<DiagnosticTest> diagnosticTests,
//			Patient patient, DiagnosticCenter diagnosticCenter, Set<TestResult> testResult) {
//		this.id = id;
//		this.appointmentDate = appointmentDate;
//		this.approvalStatus = approvalStatus;
//		this.diagnosticTests = diagnosticTests;
//		this.patient = patient;
//		this.diagnosticCenter = diagnosticCenter;
//		this.testResult = testResult;
//	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public LocalDate getAppointmentDate() {
		return appointmentDate;
	}

	public void setAppointmentDate(LocalDate appointmentDate) {
		this.appointmentDate = appointmentDate;
	}

	public String isApprovalStatus() {
		return approvalStatus;
	}

	public void setApprovalStatus(String approvalStatus) {
		this.approvalStatus = approvalStatus;
	}

	public Set<DiagnosticTest> getDiagnosticTests() {
		return diagnosticTests;
	}

	public void setDiagnosticTests(Set<DiagnosticTest> diagnosticTests) {
		this.diagnosticTests = diagnosticTests;
	}

	public Patient getPatient() {
		return patient;
	}

	public void setPatient(Patient patient) {
		this.patient = patient;
	}

	public DiagnosticCenter getDiagnosticCenter() {
		return diagnosticCenter;
	}

	public void setDiagnosticCenter(DiagnosticCenter diagnosticCenter) {
		this.diagnosticCenter = diagnosticCenter;
	}

	public Set<TestResult> getTestResult() {
		return testResult;
	}

	public void setTestResult(Set<TestResult> testResult) {
		this.testResult = testResult;
	}


}