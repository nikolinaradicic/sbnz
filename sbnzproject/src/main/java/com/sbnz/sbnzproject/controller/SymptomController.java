package com.sbnz.sbnzproject.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import com.sbnz.sbnzproject.model.Symptom;
import com.sbnz.sbnzproject.service.SymptomService;

import java.util.Collection;

@RestController
@RequestMapping(value = "/api/symptom")
@CrossOrigin
public class SymptomController {
	private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    SymptomService symptomService;

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<Symptom> create(@RequestBody Symptom symptom) {
        logger.info("> create symptom");
        Symptom s = symptomService.create(symptom);
        logger.info("< create symptom");
        return new ResponseEntity<>(s, HttpStatus.CREATED);
    }

    @DeleteMapping(value = "/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        logger.info("> delete symptom");
        symptomService.delete(id);
        logger.info("< delete symptom");
        return new ResponseEntity<>(true, HttpStatus.OK);
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Collection<Symptom>> getAll() {
        logger.info("> get symptoms");
        Collection<Symptom> symptoms = symptomService.getAll();
        logger.info("< get symptoms");
        return new ResponseEntity<>(symptoms, HttpStatus.OK);
    }


    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<Symptom> update(@RequestBody Symptom symptom) {
        logger.info("> update symptom");
        Symptom updated = symptomService.update(symptom);
        return new ResponseEntity<>(updated, HttpStatus.OK);
    }

}
