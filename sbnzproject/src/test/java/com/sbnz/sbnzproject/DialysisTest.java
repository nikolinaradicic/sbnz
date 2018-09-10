package com.sbnz.sbnzproject;

import static org.junit.Assert.assertEquals;

import java.util.Collection;
import java.util.concurrent.TimeUnit;

import org.drools.core.ClassObjectFilter;
import org.drools.core.ClockType;
import org.drools.core.time.SessionPseudoClock;
import org.junit.Test;
import org.kie.api.KieBase;
import org.kie.api.KieBaseConfiguration;
import org.kie.api.KieServices;
import org.kie.api.conf.EventProcessingOption;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.kie.api.runtime.KieSessionConfiguration;
import org.kie.api.runtime.conf.ClockTypeOption;

import com.sbnz.sbnzproject.event.DialysisEvent;
import com.sbnz.sbnzproject.event.HeartbeatEvent;
import com.sbnz.sbnzproject.event.UrinDropEvent;
import com.sbnz.sbnzproject.model.Disease;
import com.sbnz.sbnzproject.model.MedicalRecord;
import com.sbnz.sbnzproject.model.Patient;

public class DialysisTest {
	@Test
	public void test() {
		KieServices ks = KieServices.Factory.get();
		KieContainer kContainer = ks
				.newKieContainer(ks.newReleaseId("drools-spring-v2", "drools-spring-v2-kjar", "0.0.1-SNAPSHOT"));

		KieBaseConfiguration kbconf = ks.newKieBaseConfiguration();
		kbconf.setOption(EventProcessingOption.STREAM);
		KieBase kbase = kContainer.newKieBase(kbconf);

		KieSessionConfiguration ksconf1 = ks.newKieSessionConfiguration();
		ksconf1.setOption(ClockTypeOption.get(ClockType.REALTIME_CLOCK.getId()));
		KieSession ksession1 = kbase.newKieSession(ksconf1, null);

		KieSessionConfiguration ksconf2 = ks.newKieSessionConfiguration();
		ksconf2.setOption(ClockTypeOption.get(ClockType.PSEUDO_CLOCK.getId()));
		KieSession ksession2 = kbase.newKieSession(ksconf2, null);

		realtimeClockTest(ksession1);
		pseudoClockTest(ksession2);

	}

	private void pseudoClockTest(KieSession ksession) {
		SessionPseudoClock clock = ksession.getSessionClock();

		Patient patient = new Patient(1L, "pac", "pac");
		Disease disease = new Disease();
		disease.setName("Hronicna bubrezna bolest");
		MedicalRecord m = new MedicalRecord();
		m.addDisease(disease);
		patient.addRecord(m);
		ksession.insert(patient);

		for (int index = 0; index < 11; index++) {
			HeartbeatEvent event = new HeartbeatEvent(patient);
			ksession.insert(event);
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}

		for (int index = 0; index < 50; index++) {
			UrinDropEvent event = new UrinDropEvent(1, patient);
			ksession.insert(event);
			clock.advanceTime(10, TimeUnit.MILLISECONDS);
			int ruleCount = ksession.fireAllRules();
			assertEquals(0L, ruleCount);
		}

		ksession.getAgenda().getAgendaGroup("monitoring").setFocus();
		int ruleCount = ksession.fireAllRules();
		assertEquals(ruleCount, 1L);
		Collection<?> newEvents = ksession.getObjects(new ClassObjectFilter(DialysisEvent.class));
		assertEquals(newEvents.size(), 1L);
	}

	private void realtimeClockTest(final KieSession ksession) {
		Thread t = new Thread() {
			@Override
			public void run() {
				Patient patient = new Patient(1L, "pac", "pac");
				Disease disease = new Disease();
				disease.setName("Hronicna bubrezna bolest");
				MedicalRecord m = new MedicalRecord();
				m.addDisease(disease);
				patient.addRecord(m);
				ksession.insert(patient);
				for (int index = 0; index < 11; index++) {
					HeartbeatEvent event = new HeartbeatEvent(patient);
					ksession.insert(event);
					try {
						Thread.sleep(100);
					} catch (InterruptedException e) {
					}
				}

				for (int index = 0; index < 13; index++) {
					UrinDropEvent event = new UrinDropEvent(1, patient);
					ksession.insert(event);
					try {
						Thread.sleep(1000*3600);
					} catch (InterruptedException e) {
						// do nothing
					}
				}
			}
		};
		t.setDaemon(true);
		t.start();
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// do nothing
		}
		ksession.getAgenda().getAgendaGroup("monitoring").setFocus();
		ksession.fireUntilHalt();
		Collection<?> newEvents = ksession.getObjects(new ClassObjectFilter(DialysisEvent.class));
		assertEquals(newEvents.size(), 1L);
	}

}
