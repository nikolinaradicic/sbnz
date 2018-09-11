package com.sbnz.sbnzproject.service.implementation;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.GregorianCalendar;

import org.kie.api.KieBase;
import org.kie.api.KieBaseConfiguration;
import org.kie.api.KieServices;
import org.kie.api.conf.EventProcessingOption;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sbnz.sbnzproject.model.Disease;
import com.sbnz.sbnzproject.model.MedicalRecord;
import com.sbnz.sbnzproject.model.Patient;
import com.sbnz.sbnzproject.model.PossibleDisease;
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
		Patient patient = patientRepository.getOne(patientId);
		Collection<Disease> diseases = diseaseRepository.findAll();
		
		KieServices ks = KieServices.Factory.get();
		KieBaseConfiguration kbconf = ks.newKieBaseConfiguration();
		kbconf.setOption(EventProcessingOption.STREAM);
		KieBase kbase = kieContainer.newKieBase(kbconf);

		KieSession kieSession = kbase.newKieSession();
		
		Collection<PossibleDisease> possible = new ArrayList<>();
		for (Disease d: diseases) {
			PossibleDisease pd = new PossibleDisease(0L, 0L, d);
			kieSession.insert(pd);
			possible.add(pd);
		}
		MedicalRecord md = new MedicalRecord();
		md.setSymptoms(symptoms);
		md.setRecordedDate(new Date());
		kieSession.insert(md);
		kieSession.insert(patient);
		for (Symptom s: symptoms) {
			Symptom saved = symptomRepository.getOne(s.getId());
			kieSession.insert(saved);
		}
		
		Date today = new Date();
		Calendar cal = new GregorianCalendar();
		
		cal.setTime(today);
		cal.add(Calendar.DAY_OF_MONTH, -14);
		Date date14daysAgo = cal.getTime();

		cal.setTime(today);
		cal.add(Calendar.DAY_OF_MONTH, -21);
		Date date21daysAgo = cal.getTime();

		cal.setTime(today);
		cal.add(Calendar.MONTH, -6);
		Date date6monthsAgo = cal.getTime();
		
		cal.setTime(today);
		cal.add(Calendar.DAY_OF_MONTH, -60);
		Date date60daysAgo = cal.getTime();

		kieSession.setGlobal("date14daysAgo", date14daysAgo);
		kieSession.setGlobal("date21daysAgo", date21daysAgo);
		kieSession.setGlobal("date60daysAgo", date60daysAgo);
		kieSession.setGlobal("date6monthsAgo", date6monthsAgo);
		kieSession.fireAllRules();
		for (PossibleDisease pd: possible) {
			System.out.println(pd.getNumSymptoms());
			System.out.println(pd.getDisease().getName());
			
		}
		kieSession.dispose();
		System.out.println("Dijagnoza je: ");
		for (Disease el: md.getDisease()) {
			System.out.println(el.getName());
		}
		return md;
	}

	@Override
	public Patient getOne(Long id) {
		// TODO Auto-generated method stub
		return patientRepository.getOne(id);
	}

}
