package com.sbnz.sbnzproject.model;

import java.util.Objects;

public class PossibleDisease {
	
	private Disease disease;
	private Long numSymptoms;
	private Long numSpecSymptoms;
	
	public PossibleDisease() {
		
	}
	
	public PossibleDisease(Long numSymptoms, Long numSpecSymptoms, Disease disease) {
		super();
		this.numSymptoms = numSymptoms;
		this.disease = disease;
		this.numSpecSymptoms = numSpecSymptoms;
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
	public Long getNumSpecSymptoms() {
		return numSpecSymptoms;
	}
	public void setNumSpecSymptoms(Long numSpecSymptoms) {
		this.numSpecSymptoms = numSpecSymptoms;
	}

	@Override
    public boolean equals(Object o) { 
        if (o == this) { 
            return true; 
        } 
  
        if (!(o instanceof PossibleDisease)) { 
            return false; 
        } 
        
        PossibleDisease c = (PossibleDisease) o;
        return c.getDisease().equals(this.disease); 
    }
	
	@Override
    public int hashCode() {
        int hash = 7;
        hash = 67 * hash + Objects.hashCode(this.disease.getName());
        return hash;
    }
}
