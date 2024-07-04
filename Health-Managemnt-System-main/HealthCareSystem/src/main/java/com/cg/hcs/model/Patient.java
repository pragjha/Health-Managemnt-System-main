package com.cg.hcs.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "patient_tbl")
public class Patient {
	@Id
	@Column(name = "patientid")
	private Integer patientid;

	@Column(name = "name", length = 20)
	private String name;

	@Column(name = "phoneno", length = 20)
	private String phoneNo;

	@Column(name = "age", length = 20)
	private Integer age;

	@Column(name = "gender", length = 20)
	private String gender;
	@OneToMany(mappedBy="patient",cascade=CascadeType.ALL)
	@JsonIgnore
	private Set<Appointment> appointments = new HashSet<Appointment>();

	public Patient() {
		// TODO Auto-generated constructor stub
	}
//	public Patient(Integer patientid, String name, String phoneNo, 
//			Integer age, String gender) {
//		this.patientid = patientid;
//		this.name = name;
//		this.phoneNo = phoneNo;
//		this.age = age;
//		this.gender = gender;
//		this.appointments=new HashSet<Appointment>();
//	}
//
//	public Patient(Integer patientid, String name, String phoneNo, 
//			Integer age, String gender,
//			Set<Appointment> appointments) {
//		this.patientid = patientid;
//		this.name = name;
//		this.phoneNo = phoneNo;
//		this.age = age;
//		this.gender = gender;
//		this.appointments = appointments;
//	}

	public Integer getPatientid() {
		return patientid;
	}

	public void setPatientid(Integer patientid) {
		this.patientid = patientid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhoneNo() {
		return phoneNo;
	}

	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public Set<Appointment> getAppointments() {
		return appointments;
	}

	public void setAppointments(Set<Appointment> appointments) {
		this.appointments = appointments;
	}
}
