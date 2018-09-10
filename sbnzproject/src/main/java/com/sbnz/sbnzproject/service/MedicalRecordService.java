package com.sbnz.sbnzproject.service;

import java.util.Collection;

import com.sbnz.sbnzproject.model.MedicalRecord;
import com.sbnz.sbnzproject.model.Medicine;

public interface MedicalRecordService {
	public void create(Long patientId, MedicalRecord md);

	Collection<Medicine> checkAlergies(Long patientId, MedicalRecord md);
}
