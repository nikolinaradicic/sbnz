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

import com.sbnz.sbnzproject.model.MedicineComponent;
import com.sbnz.sbnzproject.service.MedicineComponentService;

@RestController
@RequestMapping(value = "/api/component")
@CrossOrigin
public class ComponentController {
	

    private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
    MedicineComponentService componentService;

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<MedicineComponent> create(@RequestBody MedicineComponent component) {
        logger.info("> create component ");

        MedicineComponent saved = componentService.create(component);

        logger.info("< create component");
        return new ResponseEntity<>(saved, HttpStatus.CREATED);
    }

    @DeleteMapping(value = "/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        logger.info("> delete component ");
        componentService.delete(id);
        logger.info("< delete component");
        return new ResponseEntity<>(true, HttpStatus.OK);
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Collection<MedicineComponent>> getAll() {
        logger.info("> get all");
        Collection<MedicineComponent> components = componentService.getAll();
        logger.info("< get all");
        return new ResponseEntity<>(components, HttpStatus.OK);
    }


    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<MedicineComponent> update(@RequestBody MedicineComponent component) {
        logger.info("> update component");
        MedicineComponent updated = componentService.update(component);
        logger.info("< update component");
        return new ResponseEntity<>(updated, HttpStatus.OK);
    }

}
