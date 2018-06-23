package com.sbnz.sbnzproject.model;

public class PossibleDisease {
	
	private Disease disease;
	private Long numSymptoms;
	public PossibleDisease(Long numSymptoms, Disease disease) {
		super();
		this.numSymptoms = numSymptoms;
		this.disease = disease;
	}
	public Disease getDisease() {
		return disease;
	}
	public void setDisease(Disease disease) {
		this.disease = disease;
	}
	public Long getNumSymptoms() {
		return numSymptoms;
	}
	public void setNumSymptoms(Long numSymptoms) {
		this.numSymptoms = numSymptoms;
	}

}
