package com.sbnz.sbnzproject.service.implementation;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sbnz.sbnzproject.model.Medicine;
import com.sbnz.sbnzproject.repository.DiseaseRepository;
import com.sbnz.sbnzproject.repository.MedicineRepository;
import com.sbnz.sbnzproject.repository.PatientRepository;
import com.sbnz.sbnzproject.repository.SymptomRepository;
import com.sbnz.sbnzproject.service.MedicineService;

@Service
public class MedicineServiceImpl implements MedicineService{
	
	@Autowired
	MedicineRepository medicineRepository;
	
	@Override
	public Medicine create(Medicine medicine) {
		// TODO Auto-generated method stub
		return medicineRepository.save(medicine);
	}

	@Override
	public void delete(Long id) {
		// TODO Auto-generated method stub
		medicineRepository.deleteById(id);
		
	}

	@Override
	public Collection<Medicine> getAll() {
		// TODO Auto-generated method stub
		return medicineRepository.findAll();
	}

	@Override
	public Medicine update(Medicine medicine) {
		// TODO Auto-generated method stub
		Medicine toUpdate = medicineRepository.getOne(medicine.getId());
		toUpdate.setName(medicine.getName());
		toUpdate.setMedicineType(medicine.getMedicineType());
		toUpdate.setComponents(medicine.getComponents());
		return medicineRepository.save(toUpdate);
	}

}
