package com.sbnz.sbnzproject.service;

import java.util.Collection;

import com.sbnz.sbnzproject.model.Medicine;

public interface MedicineService {

	Medicine create(Medicine medicine);

	void delete(Long id);

	Collection<Medicine> getAll();

	Medicine update(Medicine medicine);

}
