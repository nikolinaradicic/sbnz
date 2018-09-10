package com.sbnz.sbnzproject.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;


@Entity
public class MedicalRecord {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@ManyToMany()
	private Collection<Disease> disease = new ArrayList<>();
	
	@ManyToMany(fetch = FetchType.EAGER)
	private Collection<Symptom> symptoms = new ArrayList<>();
	
	@ManyToMany(fetch = FetchType.EAGER)
	private Set<Medicine> medicine = new HashSet<>();
	
	private Date recordedDate;

	public MedicalRecord() {
		
	}
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	
	public Collection<Disease> getDisease() {
		return disease;
	}
	public void setDisease(Collection<Disease> disease) {
		this.disease = disease;
	}
	public Collection<Symptom> getSymptoms() {
		return symptoms;
	}
	public void setSymptoms(Collection<Symptom> symptoms) {
		this.symptoms = symptoms;
	}
	public Collection<Medicine> getMedicine() {
		return medicine;
	}
	public void setMedicine(Set<Medicine> medicine) {
		this.medicine = medicine;
	}

	public void addDisease(Disease d) {
		if(disease == null) {
			disease = new ArrayList<>();
		}
		this.disease.add(d);
	}
	public Date getRecordedDate() {
		return recordedDate;
	}
	public void setRecordedDate(Date recordedDate) {
		this.recordedDate = recordedDate;
	}
	
	
	@Override
    public int hashCode() {
        int hash = 7;
        hash = 67 * hash + Objects.hashCode(this.recordedDate);
        return hash;
    }
	
	 @Override
	    public boolean equals(Object o) { 
	        if (o == this) { 
	            return true; 
	        } 
	  
	        if (!(o instanceof MedicalRecord)) { 
	            return false; 
	        } 
	        
	        MedicalRecord c = (MedicalRecord) o;
	        if (this.id != null && c.getId() != null) {
	        	return c.getId() == this.id;
	        }
	        
	        return false;
	 }
	
	
	public Collection<Symptom> collectSymptoms(){
		ArrayList<Symptom> collected = new ArrayList<>();
		for (Disease d: disease) {
			collected.addAll(d.getSymptoms());
		}
		return collected;
	}
	
	public boolean hasMedType(MedicineType type) {
		for (Medicine m : this.medicine) {
			if(m.getMedicineType() == type)
				return true;
		}
		return false;
	}
}
