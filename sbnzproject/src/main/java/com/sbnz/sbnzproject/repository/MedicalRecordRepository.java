package com.sbnz.sbnzproject.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sbnz.sbnzproject.model.MedicalRecord;

public interface MedicalRecordRepository extends JpaRepository<MedicalRecord, Long> {

}
