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

import com.example.demo.model.Immunization;
import com.example.demo.repo.ImmunizationRepository;

@RestController
@RequestMapping("/api/v1/immunization")
public class ImmunizationController {  
    private final ImmunizationRepository immunizationRepository;

    @Autowired
    public ImmunizationController(ImmunizationRepository immunizationRepository) {
        this.immunizationRepository = immunizationRepository;
    }

    @PostMapping
    public ResponseEntity<Immunization> create(@Valid @RequestBody Immunization encounter) {
    	Immunization savedEncounter = immunizationRepository.save(encounter);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
            .buildAndExpand(savedEncounter.getId()).toUri();

        return ResponseEntity.created(location).body(savedEncounter);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Immunization> update(@PathVariable Integer id, @Valid @RequestBody Immunization immunization) {
        Optional<Immunization> optionalImmunization = immunizationRepository.findById(id);
        if (!optionalImmunization.isPresent()) {
            return ResponseEntity.unprocessableEntity().build();
        }

        immunization.setId(optionalImmunization.get().getId());
        immunizationRepository.save(immunization);

        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Immunization> delete(@PathVariable Integer id) {
        Optional<Immunization> optionalImmunization = immunizationRepository.findById(id);
        if (!optionalImmunization.isPresent()) {
            return ResponseEntity.unprocessableEntity().build();
        }

        immunizationRepository.delete(optionalImmunization.get());

        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Immunization> getById(@PathVariable Integer id) {
        Optional<Immunization> optionalImmunization = immunizationRepository.findById(id);
        if (!optionalImmunization.isPresent()) {
            return ResponseEntity.unprocessableEntity().build();
        }

        return ResponseEntity.ok(optionalImmunization.get());
    }

    @GetMapping
    public ResponseEntity<Page<Immunization>> getAll(Pageable pageable) {
        return ResponseEntity.ok(immunizationRepository.findAll(pageable));
    }
}
