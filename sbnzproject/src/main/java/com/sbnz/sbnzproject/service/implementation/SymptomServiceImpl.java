package com.sbnz.sbnzproject.service.implementation;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sbnz.sbnzproject.model.Symptom;
import com.sbnz.sbnzproject.repository.SymptomRepository;
import com.sbnz.sbnzproject.service.SymptomService;

@Service
public class SymptomServiceImpl implements SymptomService{
	
	@Autowired
	SymptomRepository symptomRepository;
	@Override
	public Symptom create(Symptom symptom) {
		// TODO Auto-generated method stub
		return symptomRepository.save(symptom);
	}

	@Override
	public void delete(Long id) {
		symptomRepository.deleteById(id);
		
	}

	@Override
	public Collection<Symptom> getAll() {
		// TODO Auto-generated method stub
		return symptomRepository.findAll();
	}

	@Override
	public Symptom update(Symptom symptom) {
		// TODO Auto-generated method stub
		Symptom toUpdate = symptomRepository.getOne(symptom.getId());
		toUpdate.setName(symptom.getName());
		return symptomRepository.save(toUpdate);
	}

}
