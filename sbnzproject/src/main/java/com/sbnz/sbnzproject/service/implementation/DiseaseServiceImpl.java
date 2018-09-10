package com.sbnz.sbnzproject.service.implementation;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;

import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.kie.api.runtime.rule.QueryResults;
import org.kie.api.runtime.rule.QueryResultsRow;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sbnz.sbnzproject.model.Disease;
import com.sbnz.sbnzproject.model.PossibleDisease;
import com.sbnz.sbnzproject.model.Symptom;
import com.sbnz.sbnzproject.repository.DiseaseRepository;
import com.sbnz.sbnzproject.service.DiseaseService;

@Service
public class DiseaseServiceImpl implements DiseaseService{

	@Autowired
	private DiseaseRepository diseaseRepository;
	
	@Autowired
	private KieContainer kieContainer;
	
	
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

	@Override
	public Collection<PossibleDisease> findPossible(Collection<Symptom> symptoms) {
		// TODO Auto-generated method stub

		KieSession kieSession = kieContainer.newKieSession("ksession-rules");
		Collection<Disease> all = diseaseRepository.findAll();
		for (Disease d : all) {
			kieSession.insert(d);
		}
		QueryResults results = kieSession.getQueryResults("Bolesti koje sadrze simptome", symptoms);
		
		ArrayList<PossibleDisease> retVal = new ArrayList<>();
		for(QueryResultsRow qrr : results) {
			Disease d = (Disease) qrr.get("$d");
			long num = (long) qrr.get("$nSymptoms");
			retVal.add(new PossibleDisease(num, 0L, d));
		}
		kieSession.dispose();
		Collections.sort(retVal, new Comparator<PossibleDisease>() {
						public int compare(PossibleDisease o1, PossibleDisease o2) 
						{
							return -Long.compare(o1.getNumSymptoms(), o2.getNumSymptoms());
						}
        			}    
				);
		return retVal;
	}

}
