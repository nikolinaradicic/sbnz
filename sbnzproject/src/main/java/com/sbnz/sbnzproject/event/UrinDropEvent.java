package com.sbnz.sbnzproject.event;

import java.util.Date;

import org.kie.api.definition.type.Expires;
import org.kie.api.definition.type.Role;
import org.kie.api.definition.type.Timestamp;

import com.sbnz.sbnzproject.model.Patient;

@Role(Role.Type.EVENT)
@Timestamp("executionTime")
@Expires("12h")
public class UrinDropEvent {
	
	private Date executionTime;
	private Integer quantity;
	private Patient patient;
	
	
	public UrinDropEvent() {
		super();
		// TODO Auto-generated constructor stub
	}
	public UrinDropEvent(Integer quantity, Patient patient) {
		super();
		this.executionTime = new Date();
		this.quantity = quantity;
		this.patient = patient;
	}
	public Date getExecutionTime() {
		return executionTime;
	}
	public void setExecutionTime(Date executionTime) {
		this.executionTime = executionTime;
	}
	public Integer getQuantity() {
		return quantity;
	}
	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}
	public Patient getPatient() {
		return patient;
	}
	public void setPatient(Patient patient) {
		this.patient = patient;
	}

}
