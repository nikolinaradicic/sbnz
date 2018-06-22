package com.sbnz.sbnzproject.controller;

import java.util.Collection;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sbnz.sbnzproject.model.Medicine;
import com.sbnz.sbnzproject.service.MedicineService;

@RestController
@RequestMapping(value = "/api/medicine")
@CrossOrigin
public class MedicineController {
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    MedicineService medicineService;

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<Medicine> create(@RequestBody Medicine medicine) {
        logger.info("> create medicine");
        Medicine m = medicineService.create(medicine);
        logger.info("< create medicine");
        return new ResponseEntity<>(m, HttpStatus.CREATED);
    }

    @DeleteMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        logger.info("> delete medicine ");
        medicineService.delete(id);
        logger.info("< delete medicine");
        return new ResponseEntity<>(true,HttpStatus.OK);
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Collection<Medicine>> getAll() {
        logger.info("> get all");
        Collection<Medicine> medicine = medicineService.getAll();
        logger.info("size: {}", medicine.size());
        return new ResponseEntity<>(medicine, HttpStatus.OK);
    }

    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> update(@RequestBody Medicine medicine) {
        logger.info("> update medicine");
        Medicine updated = medicineService.update(medicine);
        logger.info("< update medicine");
        return new ResponseEntity<>(updated, HttpStatus.OK);
    }

}
