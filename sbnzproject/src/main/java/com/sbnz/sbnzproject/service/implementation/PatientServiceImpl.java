package com.sbnz.sbnzproject.service.implementation;

import java.util.ArrayList;
import java.util.Collection;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sbnz.sbnzproject.model.Disease;
import com.sbnz.sbnzproject.model.MedicalRecord;
import com.sbnz.sbnzproject.model.Patient;
import com.sbnz.sbnzproject.model.PossibleDisease;
import com.sbnz.sbnzproject.model.PossibleSymptom;
import com.sbnz.sbnzproject.model.Symptom;
import com.sbnz.sbnzproject.repository.DiseaseRepository;
import com.sbnz.sbnzproject.repository.PatientRepository;
import com.sbnz.sbnzproject.repository.SymptomRepository;
import com.sbnz.sbnzproject.service.PatientService;

@Service
public class PatientServiceImpl implements PatientService{
	
	@Autowired
	private PatientRepository patientRepository;
	
	@Autowired
	private DiseaseRepository diseaseRepository;
	
	@Autowired
	private SymptomRepository symptomRepository;
	
	@Autowired
	private KieContainer kieContainer;

	@Override
	public Patient create(Patient p) {
		// TODO Auto-generated method stub
		return patientRepository.save(p);
	}

	@Override
	public Collection<Patient> getAll() {
		// TODO Auto-generated method stub
		return patientRepository.findAll();
	}

	@Override
	public MedicalRecord diagnose(Long patientId, Collection<Symptom> symptoms) {
		// TODO Auto-generated method stub
		Collection<Disease> diseases = diseaseRepository.findAll();
		KieSession kieSession = kieContainer.newKieSession("ksession-rules");
		Collection<PossibleDisease> possible = new ArrayList<>();
		for (Disease d: diseases) {
			PossibleDisease pd = new PossibleDisease(0L, d);
			kieSession.insert(pd);
			possible.add(pd);
		}
		MedicalRecord md = new MedicalRecord();
		md.setSymptoms(symptoms);
		//kieSession.insert(md);
		for (Symptom s: symptoms) {
			Symptom saved = symptomRepository.getOne(s.getId());
			kieSession.insert(new PossibleSymptom(saved));
		}
		kieSession.fireAllRules();
		for (PossibleDisease pd: possible) {
			System.out.println(pd.getNumSymptoms());
			System.out.println(pd.getDisease().getName());
			
		}
		kieSession.dispose();
		return md;
	}

}
