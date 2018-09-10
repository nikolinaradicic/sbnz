package com.sbnz.sbnzproject;

import static org.junit.Assert.assertEquals;

import java.util.Collection;
import java.util.concurrent.TimeUnit;

import org.drools.core.ClockType;
import org.drools.core.time.SessionPseudoClock;
import org.junit.Test;
import org.kie.api.KieBase;
import org.kie.api.KieBaseConfiguration;
import org.kie.api.KieServices;
import org.kie.api.conf.EventProcessingOption;
import org.kie.api.runtime.ClassObjectFilter;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.kie.api.runtime.KieSessionConfiguration;
import org.kie.api.runtime.conf.ClockTypeOption;

import com.sbnz.sbnzproject.event.OxygenCriticalEvent;
import com.sbnz.sbnzproject.event.OxygenEvent;
import com.sbnz.sbnzproject.model.Patient;

public class OxygenTest {
	
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

		KieSessionConfiguration ksconf2 = ks.newKieSessionConfiguration();
		ksconf2.setOption(ClockTypeOption.get(ClockType.PSEUDO_CLOCK.getId()));
		KieSession ksession2 = kbase.newKieSession(ksconf2, null);
		
		realtimeTest(ksession1);
		pseudoClockTest(ksession2);

	}
	
	
	private void pseudoClockTest(KieSession ksession) {
		Patient p = new Patient(1L, "prvi", "prvi");
		SessionPseudoClock clock = ksession.getSessionClock();
		for (int index = 0; index < 20; index++) {
			OxygenEvent oe = new OxygenEvent(66, p);
			ksession.insert(oe);
			clock.advanceTime(10, TimeUnit.MILLISECONDS);
			int fired = ksession.fireAllRules();
			//nista se ne okida
			assertEquals(fired, 0L);
		}
		ksession.getAgenda().getAgendaGroup("monitoring").setFocus();
		clock.advanceTime(15, TimeUnit.MINUTES);
		int fired = ksession.fireAllRules();
		//nakon 15 min pravilo za kiseonik
		assertEquals(fired, 1L);
		Collection<?> newEvents = ksession.getObjects(new ClassObjectFilter(OxygenCriticalEvent.class));
		//kod jednog pacijenta
		assertEquals(1L, newEvents.size());
	}
	
	private void realtimeTest(final KieSession ksession) {
		Thread t = new Thread() {
			@Override
			public void run() {
				Patient p = new Patient(1L, "prvi", "prvi");
				for (int index = 0; index < 10; index++) {
					OxygenEvent oe = new OxygenEvent(66, p);
					System.out.println(oe.getExecutionTime());
					ksession.insert(oe);
					try {
						Thread.sleep(100000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
			}
			}
		};
		t.setDaemon(true);
		t.start();
		try {
			Thread.sleep(1000);;
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		ksession.getAgenda().getAgendaGroup("monitoring").setFocus();
		ksession.fireUntilHalt();
		Collection<?> newEvents = ksession.getObjects(new ClassObjectFilter(OxygenCriticalEvent.class));
		assertEquals(1L,newEvents.size());
	}


}
