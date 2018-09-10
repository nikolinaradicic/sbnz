package com.sbnz.sbnzproject.controller;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sbnz.sbnzproject.model.Patient;
import com.sbnz.sbnzproject.service.ReportService;

@RestController
@RequestMapping(value = "/api/report")
@CrossOrigin
public class ReportController {
	
	@Autowired
	ReportService reportService;
	
	@GetMapping(value = "/chronic", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Collection<Patient>> getChronic() {
		Collection<Patient> patients = reportService.findChronic();
        return new ResponseEntity<>(patients, HttpStatus.OK);
    }
	
	@GetMapping(value = "/addicts", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Collection<Patient>> getAddicts() {
		Collection<Patient> patients = reportService.findAddicts();
        return new ResponseEntity<>(patients, HttpStatus.OK);
    }

	@GetMapping(value = "/lowImunity", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Collection<Patient>> getLowImunity() {
		Collection<Patient> patients = reportService.findLowImunity();
        return new ResponseEntity<>(patients, HttpStatus.OK);
    }
}
