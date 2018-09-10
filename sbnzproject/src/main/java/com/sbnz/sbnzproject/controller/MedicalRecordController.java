package com.sbnz.sbnzproject.controller;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sbnz.sbnzproject.model.MedicalRecord;
import com.sbnz.sbnzproject.model.Medicine;
import com.sbnz.sbnzproject.service.MedicalRecordService;

@RestController
@RequestMapping(value = "/api/medicalRecord")
@CrossOrigin
public class MedicalRecordController {

	@Autowired
	MedicalRecordService mrService;
	
	@PostMapping(value = "/{patientId}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@PreAuthorize("hasAuthority('DOCTOR')")
	public ResponseEntity<Collection<Medicine>> createPatient(@PathVariable Long patientId, @RequestBody MedicalRecord mr) {
		Collection<Medicine> alergyCheck = mrService.checkAlergies(patientId, mr);
		if (alergyCheck.isEmpty()) {
			mrService.create(patientId, mr);
		}
		return new ResponseEntity<>(alergyCheck,HttpStatus.OK);
		
	}
}
