package com.sbnz.sbnzproject.repository;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sbnz.sbnzproject.model.Symptom;
import com.sbnz.sbnzproject.model.SymptomType;

public interface SymptomRepository extends JpaRepository<Symptom, Long> {
	
	Collection<Symptom> findBySymptomType(SymptomType type);

}
