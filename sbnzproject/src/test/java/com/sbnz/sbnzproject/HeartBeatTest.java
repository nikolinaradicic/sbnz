package com.sbnz.sbnzproject;

import static org.junit.Assert.assertEquals;

import java.util.Collection;
import org.drools.core.ClassObjectFilter;
import org.drools.core.ClockType;
import org.junit.Test;
import org.kie.api.KieBase;
import org.kie.api.KieBaseConfiguration;
import org.kie.api.KieServices;
import org.kie.api.conf.EventProcessingOption;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.kie.api.runtime.KieSessionConfiguration;
import org.kie.api.runtime.conf.ClockTypeOption;

import com.sbnz.sbnzproject.event.HeartRhythmEvent;
import com.sbnz.sbnzproject.event.HeartbeatEvent;
import com.sbnz.sbnzproject.model.Patient;

public class HeartBeatTest {
	
	@Test
	public void test() {
		KieServices ks = KieServices.Factory.get();
		KieContainer kContainer = ks.newKieContainer(ks.newReleaseId("drools-spring-v2", "drools-spring-v2-kjar", "0.0.1-SNAPSHOT"));

		KieBaseConfiguration kbconf = ks.newKieBaseConfiguration();
		kbconf.setOption(EventProcessingOption.STREAM);
		KieBase kbase = kContainer.newKieBase(kbconf);

		KieSessionConfiguration ksconf1 = ks.newKieSessionConfiguration();
		ksconf1.setOption(ClockTypeOption.get(ClockType.REALTIME_CLOCK.getId()));
		KieSession ksession1 = kbase.newKieSession(ksconf1, null);

		realtimeClockTest(ksession1);

	}

	private void realtimeClockTest(final KieSession ksession) {
		Thread t = new Thread() {
			@Override
			public void run() {
				Patient p = new Patient(2L, "p", "p");
				for (int index = 0; index < 30; index++) {
					HeartbeatEvent event = new HeartbeatEvent(p);
					ksession.insert(event);
					try {
						Thread.sleep(10);
					} catch (InterruptedException e) {
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
		Collection<?> newEvents = ksession.getObjects(new ClassObjectFilter(HeartRhythmEvent.class));
		assertEquals(1L,newEvents.size());
	}



}
