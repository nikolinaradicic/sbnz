package com.sbnz.sbnzproject.service;

import java.util.Collection;

import com.sbnz.sbnzproject.model.MedicalRecord;
import com.sbnz.sbnzproject.model.Patient;
import com.sbnz.sbnzproject.model.Symptom;

public interface PatientService {
	
	public Patient create(Patient p);

	public Collection<Patient> getAll();

	public MedicalRecord diagnose(Long patientId, Collection<Symptom> symptoms);

}
