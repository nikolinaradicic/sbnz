package com.sbnz.sbnzproject.service;

import java.util.Collection;

import com.sbnz.sbnzproject.model.Symptom;

public interface SymptomService {

	Symptom create(Symptom symptom);

	void delete(Long id);

	Collection<Symptom> getAll();

	Symptom update(Symptom symptom);

}
