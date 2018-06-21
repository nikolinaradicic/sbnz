package com.sbnz.sbnzproject.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sbnz.sbnzproject.model.Symptom;

public interface SymptomRepository extends JpaRepository<Symptom, Long> {

}
