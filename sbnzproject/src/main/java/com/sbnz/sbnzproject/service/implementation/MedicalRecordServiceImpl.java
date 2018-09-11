package com.sbnz.sbnzproject.service.implementation;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;

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
import com.sbnz.sbnzproject.model.Medicine;
import com.sbnz.sbnzproject.model.MedicineAlergy;
import com.sbnz.sbnzproject.model.MedicineComponent;
import com.sbnz.sbnzproject.model.Patient;
import com.sbnz.sbnzproject.model.Symptom;
import com.sbnz.sbnzproject.repository.DiseaseRepository;
import com.sbnz.sbnzproject.repository.MedicalRecordRepository;
import com.sbnz.sbnzproject.repository.MedicineRepository;
import com.sbnz.sbnzproject.repository.PatientRepository;
import com.sbnz.sbnzproject.repository.SymptomRepository;
import com.sbnz.sbnzproject.service.MedicalRecordService;

@Service
public class MedicalRecordServiceImpl implements MedicalRecordService{
	
	@Autowired
	private KieContainer kieContainer;
	
	@Autowired
	private MedicalRecordRepository mrRepository;
	
	@Autowired
	PatientRepository patientRepository;
	
	@Autowired
	DiseaseRepository diseaseRepository;
	
	@Autowired
	SymptomRepository symptomRepository;
	
	@Autowired
	MedicineRepository medicineRepository;
	

	@Override
	public Collection<Medicine> checkAlergies(Long pId, MedicalRecord md) {
		// TODO Auto-generated method stub
		KieServices ks = KieServices.Factory.get();
		KieBaseConfiguration kbconf = ks.newKieBaseConfiguration();
		kbconf.setOption(EventProcessingOption.STREAM);
		KieBase kbase = kieContainer.newKieBase(kbconf);

		KieSession kieSession = kbase.newKieSession();
		Patient patient = patientRepository.getOne(pId);
		kieSession.insert(patient);
		Collection<MedicineAlergy> alergies = new ArrayList<>();
		
		for(Medicine m: md.getMedicine()) {
			MedicineAlergy ma = new MedicineAlergy(m);
			alergies.add(ma);
			kieSession.insert(ma);
			for(MedicineComponent mc: m.getComponents()) {
				kieSession.insert(mc);
			}
		}
		
		kieSession.getAgenda().getAgendaGroup("lijekovi").setFocus();
		kieSession.fireAllRules();
		Collection<Medicine> retVal = new ArrayList<>();
		for (MedicineAlergy m: alergies) {
			if(m.isAlergic())
				retVal.add(m.getMedicine());
		}
		kieSession.dispose();
		return retVal;
	}

	@Override
	public void create(Long pId,MedicalRecord md) {
		// TODO Auto-generated method stub
		Collection<Disease> savedD = new ArrayList<>();
		for (Disease d : md.getDisease()) {
			savedD.add(diseaseRepository.getOne(d.getId()));
		}
		md.setDisease(savedD);
		Collection<Symptom> savedS = new ArrayList<>();
		for (Symptom s : md.getSymptoms()) {
			savedS.add(symptomRepository.getOne(s.getId()));
		}
		md.setSymptoms(savedS);
		
		HashSet<Medicine> savedM = new HashSet<>();
		for (Medicine m : md.getMedicine()) {
			savedM.add(medicineRepository.getOne(m.getId()));
		}
		md.setMedicine(savedM);
		md.setRecordedDate(new Date());
		MedicalRecord saved = mrRepository.save(md);
		Patient patient = patientRepository.getOne(pId);
		patient.addRecord(saved);
		patientRepository.save(patient);
	}

}
