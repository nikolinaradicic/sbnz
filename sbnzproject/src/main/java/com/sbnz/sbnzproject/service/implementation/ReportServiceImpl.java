package com.sbnz.sbnzproject.service.implementation;

import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashSet;

import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.kie.api.KieBase;
import org.kie.api.conf.EventProcessingOption;
import org.kie.api.KieBaseConfiguration;
import org.kie.api.KieServices;
import org.kie.api.runtime.rule.QueryResults;
import org.kie.api.runtime.rule.QueryResultsRow;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sbnz.sbnzproject.model.Disease;
import com.sbnz.sbnzproject.model.Patient;
import com.sbnz.sbnzproject.repository.DiseaseRepository;
import com.sbnz.sbnzproject.repository.PatientRepository;
import com.sbnz.sbnzproject.service.ReportService;


@Service
public class ReportServiceImpl implements ReportService{
	
	@Autowired
	KieContainer kieContainer;
	
	@Autowired
	DiseaseRepository diseaseRepository;
	
	@Autowired
	PatientRepository patientReposiory;

	@Override
	public Collection<Patient> findChronic() {
		// TODO Auto-generated method stub		
		KieServices ks = KieServices.Factory.get();
		KieBaseConfiguration kbconf = ks.newKieBaseConfiguration();
		kbconf.setOption(EventProcessingOption.STREAM);
		KieBase kbase = kieContainer.newKieBase(kbconf);

		KieSession kieSession = kbase.newKieSession();
		
		
		Date today = new Date();
		Calendar cal = new GregorianCalendar();
		cal.setTime(today);
		cal.add(Calendar.MONTH, -24);
		Date date2yearsAgo = cal.getTime();

		kieSession.setGlobal("startDate", date2yearsAgo);
		Collection<Disease> all = diseaseRepository.findAll();
		for (Disease d : all) {
			if(!(d.getName().equals("Prehlada") || d.getName().equals("Groznica")))
			{
				kieSession.insert(d);
			}
		}
		
		Collection<Patient> patients = patientReposiory.findAll();
		for (Patient p: patients) {
			kieSession.insert(p);
		}
		
		QueryResults results = kieSession.getQueryResults("Pacijenti sa mogucim hronicnim oboljenjima");
		
		
		HashSet<Patient> retVal = new HashSet<>();
		for(QueryResultsRow qrr : results) {
			Patient p = (Patient) qrr.get("$patient");
			retVal.add(p);
		}
		kieSession.dispose();
		return retVal;
	}

	@Override
	public Collection<Patient> findAddicts() {
		// TODO Auto-generated method stub
		KieServices ks = KieServices.Factory.get();
		KieBaseConfiguration kbconf = ks.newKieBaseConfiguration();
		kbconf.setOption(EventProcessingOption.STREAM);
		KieBase kbase = kieContainer.newKieBase(kbconf);

		KieSession kieSession = kbase.newKieSession();
		
		Date today = new Date();
		Calendar cal = new GregorianCalendar();
		cal.setTime(today);
		cal.add(Calendar.MONTH, -6);
		Date date6MonthsAgo = cal.getTime();

		kieSession.setGlobal("startDate", date6MonthsAgo);
		
		Collection<Patient> patients = patientReposiory.findAll();
		for (Patient p: patients) {
			kieSession.insert(p);
		}
		
		QueryResults results = kieSession.getQueryResults("Spisak mogucih zavisnika");		
		HashSet<Patient> retVal = new HashSet<>();
		for(QueryResultsRow qrr : results) {
			Patient p = (Patient) qrr.get("$patient");
			retVal.add(p);
		}
		kieSession.dispose();
		return retVal;
	}

	@Override
	public Collection<Patient> findLowImunity() {
		// TODO Auto-generated method stub
		KieServices ks = KieServices.Factory.get();
		KieBaseConfiguration kbconf = ks.newKieBaseConfiguration();
		kbconf.setOption(EventProcessingOption.STREAM);
		KieBase kbase = kieContainer.newKieBase(kbconf);

		KieSession kieSession = kbase.newKieSession();
		
		Date today = new Date();
		Calendar cal = new GregorianCalendar();
		cal.setTime(today);
		cal.add(Calendar.MONTH, -12);
		Date date6MonthsAgo = cal.getTime();

		kieSession.setGlobal("startDate", date6MonthsAgo);
		
		Collection<Patient> patients = patientReposiory.findAll();
		for (Patient p: patients) {
			kieSession.insert(p);
		}
		
		QueryResults results = kieSession.getQueryResults("Spisak pacijenata sa oslabljenim imunitetom");		
		HashSet<Patient> retVal = new HashSet<>();
		for(QueryResultsRow qrr : results) {
			Patient p = (Patient) qrr.get("$patient");
			retVal.add(p);
		}
		kieSession.dispose();
		return retVal;
	}

}
