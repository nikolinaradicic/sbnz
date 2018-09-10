package com.sbnz.sbnzproject.event;

import java.util.Date;

import org.kie.api.definition.type.Expires;
import org.kie.api.definition.type.Role;
import org.kie.api.definition.type.Timestamp;

import com.sbnz.sbnzproject.model.Patient;

@Role(Role.Type.EVENT)
@Timestamp("executionTime")
@Expires("15m")

public class OxygenEvent {
	private Date executionTime;
	private Integer oxygenLevel;
	private Patient patient;
	
	public OxygenEvent() {
		super();
		// TODO Auto-generated constructor stub
	}
	public OxygenEvent(Integer oxygenLevel, Patient patient) {
		super();
		this.oxygenLevel = oxygenLevel;
		this.patient = patient;
		this.executionTime = new Date();
	}
	public Date getExecutionTime() {
		return executionTime;
	}
	public void setExecutionTime(Date executionTime) {
		this.executionTime = executionTime;
	}
	public Integer getOxygenLevel() {
		return oxygenLevel;
	}
	public void setOxygenLevel(Integer oxygenLevel) {
		this.oxygenLevel = oxygenLevel;
	}
	public Patient getPatient() {
		return patient;
	}
	public void setPatient(Patient patient) {
		this.patient = patient;
	}
	
	

}
