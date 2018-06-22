package com.sbnz.sbnzproject.service.implementation;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sbnz.sbnzproject.model.Disease;
import com.sbnz.sbnzproject.repository.DiseaseRepository;
import com.sbnz.sbnzproject.service.DiseaseService;

@Service
public class DiseaseServiceImpl implements DiseaseService{

	@Autowired
	private DiseaseRepository diseaseRepository;
	
	@Override
	public Disease create(Disease disease) {
		// TODO Auto-generated method stub
		return diseaseRepository.save(disease);
	}

	@Override
	public void delete(Long id) {
		// TODO Auto-generated method stub
		diseaseRepository.deleteById(id);
		
	}

	@Override
	public Collection<Disease> getAll() {
		// TODO Auto-generated method stub
		return diseaseRepository.findAll();
	}

	@Override
	public Disease update(Disease disease) {
		// TODO Auto-generated method stub
		Disease toUpdate = diseaseRepository.getOne(disease.getId());
		toUpdate.setDiseaseType(disease.getDiseaseType());
		toUpdate.setName(disease.getName());
		toUpdate.setSymptoms(disease.getSymptoms());
		return diseaseRepository.save(toUpdate);
	}

}
