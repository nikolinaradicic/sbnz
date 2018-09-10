package com.sbnz.sbnzproject.service;

import java.util.Collection;

import com.sbnz.sbnzproject.model.Patient;

public interface ReportService {

	Collection<Patient> findChronic();
	Collection<Patient> findAddicts();
	Collection<Patient> findLowImunity();
}
