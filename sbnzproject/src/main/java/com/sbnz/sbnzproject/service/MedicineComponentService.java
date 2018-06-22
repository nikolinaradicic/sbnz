package com.sbnz.sbnzproject.service;

import java.util.Collection;

import com.sbnz.sbnzproject.model.MedicineComponent;

public interface MedicineComponentService {

    MedicineComponent create(MedicineComponent component);
    
    MedicineComponent update(MedicineComponent componennt);

    void delete(Long id);

    Collection<MedicineComponent> getAll();
}
