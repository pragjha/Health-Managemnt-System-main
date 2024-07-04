package com.cg.hcs.model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "diagnosticcenter_tbl")
public class DiagnosticCenter {
	@Id
	@Column(name = "Id")
	private Integer id;
	@Column(name = "Name",length=20)
	private String name;
	@Column(name = "Contactno",length=20)
	private String contactNo;
	@Column(name = "Address",length=20)
	private String address;
	@Column(name = "Email",length=30)
	private String contactEmail;
	@ElementCollection
	@CollectionTable(name = "center_services"
	, joinColumns = @JoinColumn(name = "diagnosticcenter_id"))
	@Column(name = "services",length=40)
	private List<String> servicesOffered=new ArrayList<String>();
	@ManyToMany(cascade=CascadeType.ALL)
	@JoinTable(name="diagnosticcenter_tests"
	,joinColumns= {@JoinColumn(name="diagnosticcenter_id")}
	,inverseJoinColumns= {@JoinColumn(name="diagnostictest_id")})
	private Set<DiagnosticTest> tests = new HashSet<DiagnosticTest>();
	@JsonIgnore
	@OneToMany(mappedBy="diagnosticCenter",cascade=CascadeType.ALL)
	private Set<Appointment> appointments=new HashSet<Appointment>();
	public DiagnosticCenter() {
		// TODO Auto-generated constructor stub
	}
	public DiagnosticCenter(Integer id, String name, String contactNo,
			String address, String contactEmail){
		this.id = id;
		this.name = name;
		this.contactNo = contactNo;
		this.address = address;
		this.contactEmail = contactEmail;
	}
	public DiagnosticCenter(Integer id, String name, String contactNo,
			String address, String contactEmail,
			List<String> servicesOffered, Set<DiagnosticTest> tests) {
		this.id = id;
		this.name = name;
		this.contactNo = contactNo;
		this.address = address;
		this.contactEmail = contactEmail;
		this.servicesOffered = servicesOffered;
		this.tests = tests;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getContactNo() {
		return contactNo;
	}

	public void setContactNo(String contactNo) {
		this.contactNo = contactNo;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getContactEmail() {
		return contactEmail;
	}

	public void setContactEmail(String contactEmail) {
		this.contactEmail = contactEmail;
	}

	public List<String> getServicesOffered() {
		return servicesOffered;
	}

	public void setServicesOffered(List<String> servicesOffered) {
		this.servicesOffered = servicesOffered;
	}

	public Set<DiagnosticTest> getTests() {
		return tests;
	}

	public void setTests(Set<DiagnosticTest> tests) {
		this.tests = tests;
	}

	public Set<Appointment> getAppointments() {
		return appointments;
	}
	public void setAppointments(Set<Appointment> appointments) {
		this.appointments = appointments;
	}
	

}