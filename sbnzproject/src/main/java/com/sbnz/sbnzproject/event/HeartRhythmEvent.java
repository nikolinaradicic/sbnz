package com.sbnz.sbnzproject.event;

import org.kie.api.definition.type.Expires;
import org.kie.api.definition.type.Role;

import com.sbnz.sbnzproject.model.Patient;

@Role(Role.Type.EVENT)
@Expires("15m")
public class HeartRhythmEvent {

	private Patient patient;
	private String message;
	
	
	public HeartRhythmEvent() {
		super();
		// TODO Auto-generated constructor stub
	}
	public HeartRhythmEvent(Patient patient, String message) {
		super();
		this.patient = patient;
		this.message = message;
	}
	public Patient getPatient() {
		return patient;
	}
	public void setPatient(Patient patient) {
		this.patient = patient;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
}
