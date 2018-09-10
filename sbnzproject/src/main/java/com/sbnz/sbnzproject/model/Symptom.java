package com.sbnz.sbnzproject.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Symptom {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(nullable = false)
	private String name;
	
	@Enumerated(EnumType.STRING)
	private SymptomType symptomType;
	
	public Symptom() {
		
	}

	public Symptom(String name) {
		this.name = name;
	}
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public SymptomType getSymptomType() {
		return symptomType;
	}

	public void setSymptomType(SymptomType symptomType) {
		this.symptomType = symptomType;
	}
	
	 @Override
	    public boolean equals(Object o) { 
	        if (o == this) { 
	            return true; 
	        } 
	  
	        if (!(o instanceof Symptom)) { 
	            return false; 
	        } 
	        
	        Symptom c = (Symptom) o;
	        return c.getName().equals(this.name); 
	    }
	
}
