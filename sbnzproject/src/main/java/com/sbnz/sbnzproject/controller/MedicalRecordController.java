package com.sbnz.sbnzproject.controller;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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
import com.sbnz.sbnzproject.model.User;
import com.sbnz.sbnzproject.repository.UserRepository;
import com.sbnz.sbnzproject.security.JwtTokenUtil;
import com.sbnz.sbnzproject.service.MedicalRecordService;

@RestController
@RequestMapping(value = "/api/medicalRecord")
@CrossOrigin
public class MedicalRecordController {

	@Autowired
	MedicalRecordService mrService;
	
	@Autowired
	private JwtTokenUtil jwtTokenUtil;

	@Value("${jwt.header}")
	private String tokenHeader;
	
	@Autowired
	UserRepository userService;
	
	@PostMapping(value = "/{patientId}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@PreAuthorize("hasAuthority('DOCTOR')")
	public ResponseEntity<Collection<Medicine>> createPatient(@PathVariable Long patientId, @RequestBody MedicalRecord mr, HttpServletRequest request) {
		Collection<Medicine> alergyCheck = mrService.checkAlergies(patientId, mr);
		if (alergyCheck.isEmpty()) {
			String token = request.getHeader(tokenHeader);
			String username = jwtTokenUtil.getUsernameFromToken(token);
			User user = userService.findByUsername(username);
			mr.setDoctor(user);
			mrService.create(patientId, mr);
		}
		return new ResponseEntity<>(alergyCheck,HttpStatus.OK);
		
	}
}
