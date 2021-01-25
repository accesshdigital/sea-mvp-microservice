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

import com.example.demo.model.Specimen;
import com.example.demo.repo.SpecimenRepository;

@RestController
@RequestMapping("/api/v1/specimen")
public class SpecimenController {  
    private final SpecimenRepository specimenRepository;

    @Autowired
    public SpecimenController(SpecimenRepository specimenRepository) {
        this.specimenRepository = specimenRepository;
    }

    @PostMapping
    public ResponseEntity<Specimen> create(@Valid @RequestBody Specimen encounter) {
    	Specimen savedEncounter = specimenRepository.save(encounter);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
            .buildAndExpand(savedEncounter.getId()).toUri();

        return ResponseEntity.created(location).body(savedEncounter);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Specimen> update(@PathVariable Integer id, @Valid @RequestBody Specimen encounter) {
        Optional<Specimen> optionalEncounter = specimenRepository.findById(id);
        if (!optionalEncounter.isPresent()) {
            return ResponseEntity.unprocessableEntity().build();
        }

        encounter.setId(optionalEncounter.get().getId());
        specimenRepository.save(encounter);

        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Specimen> delete(@PathVariable Integer id) {
        Optional<Specimen> optionalEncounter = specimenRepository.findById(id);
        if (!optionalEncounter.isPresent()) {
            return ResponseEntity.unprocessableEntity().build();
        }

        specimenRepository.delete(optionalEncounter.get());

        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Specimen> getById(@PathVariable Integer id) {
        Optional<Specimen> optionalEncounter = specimenRepository.findById(id);
        if (!optionalEncounter.isPresent()) {
            return ResponseEntity.unprocessableEntity().build();
        }

        return ResponseEntity.ok(optionalEncounter.get());
    }

    @GetMapping
    public ResponseEntity<Page<Specimen>> getAll(Pageable pageable) {
        return ResponseEntity.ok(specimenRepository.findAll(pageable));
    }
}
