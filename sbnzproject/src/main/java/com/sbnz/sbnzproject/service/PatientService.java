package com.sbnz.sbnzproject.service;

import java.util.Collection;

import com.sbnz.sbnzproject.model.Patient;

public interface PatientService {
	
	public Patient create(Patient p);

	public Collection<Patient> getAll();

}
