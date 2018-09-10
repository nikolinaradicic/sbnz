package com.sbnz.sbnzproject.service;

import java.util.Collection;

import com.sbnz.sbnzproject.model.Disease;
import com.sbnz.sbnzproject.model.PossibleDisease;
import com.sbnz.sbnzproject.model.Symptom;

public interface DiseaseService {

	Disease create(Disease disease);

	void delete(Long id);

	Collection<Disease> getAll();

	Disease update(Disease disease);

	Collection<PossibleDisease> findPossible(Collection<Symptom> symptoms);

}
