package com.sbnz.sbnzproject.controller;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import com.sbnz.sbnzproject.model.Disease;
import com.sbnz.sbnzproject.model.PossibleDisease;
import com.sbnz.sbnzproject.model.Symptom;
import com.sbnz.sbnzproject.service.DiseaseService;

import java.util.Collection;

@RestController
@RequestMapping(value = "/api/disease")
@CrossOrigin
public class DiseaseController {
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    DiseaseService diseaseService;

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<Disease> create(@RequestBody Disease disease) {
        logger.info("> create disease");
        Disease saved = diseaseService.create(disease);
        logger.info("< create disease");
        return new ResponseEntity<>(saved, HttpStatus.CREATED);
    }

    @DeleteMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        logger.info("> delete disease");
        diseaseService.delete(id);
        logger.info("> delete disease");
        return new ResponseEntity<>(true, HttpStatus.OK);
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Collection<Disease>> getAll() {
        logger.info("> get all");
        Collection<Disease> diseases = diseaseService.getAll();
        logger.info("< get all");
        return new ResponseEntity<>(diseases, HttpStatus.OK);
    }

    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<?> update(@RequestBody Disease disease) {
        logger.info("> update disease");
        Disease updated = diseaseService.update(disease);
        return new ResponseEntity<>(updated, HttpStatus.OK);
    }
    
    @PostMapping(value="/forSymptoms", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@PreAuthorize("hasAuthority('DOCTOR')")
	public ResponseEntity<Collection<PossibleDisease>> getPossible(@RequestBody Collection<Symptom> symptoms) {
		logger.info("> create patient");
		Collection<PossibleDisease> saved = diseaseService.findPossible(symptoms);
		return new ResponseEntity<>(saved,HttpStatus.CREATED);
	}

}
