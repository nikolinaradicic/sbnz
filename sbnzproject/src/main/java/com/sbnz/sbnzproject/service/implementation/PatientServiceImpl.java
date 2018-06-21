package com.sbnz.sbnzproject.service.implementation;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sbnz.sbnzproject.model.Patient;
import com.sbnz.sbnzproject.repository.PatientRepository;
import com.sbnz.sbnzproject.service.PatientService;

@Service
public class PatientServiceImpl implements PatientService{
	
	@Autowired
	private PatientRepository patientRepository;

	@Override
	public Patient create(Patient p) {
		// TODO Auto-generated method stub
		return patientRepository.save(p);
	}

	@Override
	public Collection<Patient> getAll() {
		// TODO Auto-generated method stub
		return patientRepository.findAll();
	}

}
