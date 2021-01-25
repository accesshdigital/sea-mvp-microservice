package com.example.demo.controller;



import java.net.URI;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.example.demo.model.Observation;
import com.example.demo.repo.ObservationRepository;

@RestController
@RequestMapping("/api/v1/observation")
public class ObservationController {  
    private final ObservationRepository observationRepository;

    @Autowired
    public ObservationController(ObservationRepository observationRepository) {
        this.observationRepository = observationRepository;
    }

    @PostMapping
    public ResponseEntity<Observation> create(@Valid @RequestBody Observation observation) {
    	Observation savedObservation = observationRepository.save(observation);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
            .buildAndExpand(savedObservation.getId()).toUri();

        return ResponseEntity.created(location).body(savedObservation);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Observation> update(@PathVariable Integer id, @Valid @RequestBody Observation observation) {
        Optional<Observation> optionalObservation = observationRepository.findById(id);
        if (!optionalObservation.isPresent()) {
            return ResponseEntity.unprocessableEntity().build();
        }

        observation.setId(optionalObservation.get().getId());
        observationRepository.save(observation);

        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Observation> delete(@PathVariable Integer id) {
        Optional<Observation> optionalObservation = observationRepository.findById(id);
        if (!optionalObservation.isPresent()) {
            return ResponseEntity.unprocessableEntity().build();
        }

        observationRepository.delete(optionalObservation.get());

        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Observation> getById(@PathVariable Integer id) {
        Optional<Observation> optionalObservation = observationRepository.findById(id);
        if (!optionalObservation.isPresent()) {
            return ResponseEntity.unprocessableEntity().build();
        }

        return ResponseEntity.ok(optionalObservation.get());
    }

    @GetMapping
    public ResponseEntity<Page<Observation>> getAll(Pageable pageable) {
        return ResponseEntity.ok(observationRepository.findAll(pageable));
    }
}
