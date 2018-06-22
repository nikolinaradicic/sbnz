package com.sbnz.sbnzproject.controller;

import java.util.Collection;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sbnz.sbnzproject.model.Patient;
import com.sbnz.sbnzproject.service.PatientService;

@RestController
@RequestMapping(value = "/api/patient")
@CrossOrigin
public class PatientController {
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	PatientService patientService;

	
	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@PreAuthorize("hasAuthority('DOCTOR')")
	public ResponseEntity<Patient> createPatient(@RequestBody Patient patient) {
		logger.info("> create patient");
		Patient saved = patientService.create(patient);
		return new ResponseEntity<>(saved,HttpStatus.CREATED);
	}
	
	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	@PreAuthorize("hasAuthority('DOCTOR')")
	public ResponseEntity<Collection<Patient>> getPatients() {
		logger.info("> get patients");
		Collection<Patient> saved = patientService.getAll();
		logger.info("< get patients");
		return new ResponseEntity<>(saved,HttpStatus.CREATED);
	}


}
