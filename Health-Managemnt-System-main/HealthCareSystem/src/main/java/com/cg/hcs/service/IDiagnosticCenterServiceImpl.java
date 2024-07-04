package com.cg.hcs.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.cg.hcs.dao.IDiagnosticCenterRepository;
import com.cg.hcs.exception.DiagnosticCenterException;
import com.cg.hcs.model.Appointment;
import com.cg.hcs.model.DiagnosticCenter;
import com.cg.hcs.model.DiagnosticTest;

@Service
public class IDiagnosticCenterServiceImpl implements IDiagnosticCenterService {
	@Autowired
	IDiagnosticCenterRepository repo;

	@Override
	public ResponseEntity<List<DiagnosticCenter>> getAllDiagnosticCenters() throws DiagnosticCenterException {
		if (repo.findAll().size() == 0) {
			throw new DiagnosticCenterException("No Appointments are available");
		}
		return new ResponseEntity<List<DiagnosticCenter>>(repo.findAll(), HttpStatus.OK);
	}

	@Override
	public ResponseEntity<DiagnosticCenter> addDiagnosticCenter(DiagnosticCenter diagnosticCenter)
			throws DiagnosticCenterException {
		if (repo.existsById(diagnosticCenter.getId())) {
			throw new DiagnosticCenterException("Center with the given Id already Exists");
		}
		repo.save(diagnosticCenter);
		return new ResponseEntity<DiagnosticCenter>(diagnosticCenter, HttpStatus.OK);
	}

	@Override
	public ResponseEntity<DiagnosticCenter> getDiagnosticCenterById(int diagnosticCenterId)
			throws DiagnosticCenterException {
		if (repo.existsById(diagnosticCenterId)) {
			return new ResponseEntity<DiagnosticCenter>(repo.findById(diagnosticCenterId).get(), HttpStatus.OK);
		}
		throw new DiagnosticCenterException("Center with the given Id doesn't Exists");
	}

	@Override
	public ResponseEntity<DiagnosticCenter> updateDiagnosticCenter(DiagnosticCenter diagnosticCenter)
			throws DiagnosticCenterException {
		if (repo.existsById(diagnosticCenter.getId())) {
			repo.save(diagnosticCenter);
			return new ResponseEntity<DiagnosticCenter>(diagnosticCenter, HttpStatus.OK);
		}
		throw new DiagnosticCenterException("Center with the given Id doesn't Exists");
	}

	@Override
	public ResponseEntity<DiagnosticTest> viewTestDetails(int diagnosticCenterId, String testName)
			throws DiagnosticCenterException {
		if (!repo.existsById(diagnosticCenterId)) {
			throw new DiagnosticCenterException("Center with the given Id doesn't Exists");
		}
		DiagnosticTest test = repo.viewTestDetails(diagnosticCenterId, testName);
		if (test == null) {
			throw new DiagnosticCenterException("Test with the given name doesn't Exists");
		}
		return new ResponseEntity<DiagnosticTest>(test, HttpStatus.OK);
	}

	@Override
	public ResponseEntity<DiagnosticTest> addTest(int diagnosticCenterId, DiagnosticTest test)
			throws DiagnosticCenterException {
		if (repo.existsById(diagnosticCenterId)) {
			DiagnosticCenter diagnosticCenter = repo.findById(diagnosticCenterId).get();
			if (diagnosticCenter.getTests().contains(test)) {
				throw new DiagnosticCenterException("Test already available in the Center");
			}
			diagnosticCenter.getTests().add(test);
			repo.save(diagnosticCenter);
			return new ResponseEntity<DiagnosticTest>(test, HttpStatus.OK);
		}
		throw new DiagnosticCenterException("Center with the given Id doesn't Exists");
	}

	@Override
	public ResponseEntity<DiagnosticCenter> getDiagnosticCenter(String centerName) throws DiagnosticCenterException {
		DiagnosticCenter center=repo.getDiagnosticCenter(centerName);
		if(center==null) {
			throw new DiagnosticCenterException("No Center exists with the given name");
		}
		return new ResponseEntity<DiagnosticCenter>(center, HttpStatus.OK);
	}

	@Override
	public ResponseEntity<DiagnosticCenter> removeDiagnosticCenter(int id) throws DiagnosticCenterException {
		if(repo.existsById(id)) {
			DiagnosticCenter c=repo.findById(id).get();
			c.getAppointments().stream().forEach(a->a.setDiagnosticCenter(null));
			c.setAppointments(null);
			c.setTests(null);
			repo.save(c);
			repo.delete(c);
			return new ResponseEntity<DiagnosticCenter>(c, HttpStatus.OK);
		}
		throw new DiagnosticCenterException("Center with the given Id doesn't Exists");
	}

	@Override
	public ResponseEntity<List<Appointment>> getListOfAppointments(String centerName) throws DiagnosticCenterException {
		List<Appointment> al=repo.getListOfAppointments(centerName);
		if(al.size()==0) {
			throw new DiagnosticCenterException("No Center exists with the given name");
		}
		return new ResponseEntity<List<Appointment>>(al,HttpStatus.OK);
	}

}