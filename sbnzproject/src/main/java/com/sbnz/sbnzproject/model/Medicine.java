package com.sbnz.sbnzproject.model;

import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

@Entity
public class Medicine {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@ManyToMany(fetch= FetchType.EAGER)
	private Collection<MedicineComponent> components = new ArrayList<>();
	
	@Enumerated(EnumType.STRING)
	private MedicineType medicineType;
	
	public Medicine() {
		
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Collection<MedicineComponent> getComponents() {
		return components;
	}

	public void setComponents(Collection<MedicineComponent> components) {
		this.components = components;
	}

	public MedicineType getMedicineType() {
		return medicineType;
	}

	public void setMedicineType(MedicineType medicineType) {
		this.medicineType = medicineType;
	}
	
	

}