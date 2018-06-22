package com.sbnz.sbnzproject.service.implementation;

import java.util.Collection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sbnz.sbnzproject.model.MedicineComponent;
import com.sbnz.sbnzproject.repository.MedicineComponentRepository;
import com.sbnz.sbnzproject.service.MedicineComponentService;

@Service
public class MedicineComponentServiceImpl implements MedicineComponentService{
	
	@Autowired
    MedicineComponentRepository medicineComponentRepository;

    @Override
    public MedicineComponent create(MedicineComponent component) {
        return medicineComponentRepository.save(component);
    }

    @Override
    public void delete(Long id) {
        medicineComponentRepository.deleteById(id);
    }

    @Override
    public Collection<MedicineComponent> getAll() {
        return medicineComponentRepository.findAll();
    }

	@Override
	public MedicineComponent update(MedicineComponent component) {
		// TODO Auto-generated method stub
		MedicineComponent toUpdate = medicineComponentRepository.getOne(component.getId());
		toUpdate.setName(component.getName());
		return medicineComponentRepository.save(toUpdate);
	}



}
