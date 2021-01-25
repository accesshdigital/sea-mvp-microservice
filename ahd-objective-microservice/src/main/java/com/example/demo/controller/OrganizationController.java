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

import com.example.demo.model.Organization;
import com.example.demo.repo.OrganizationRepository;

@RestController
@RequestMapping("/api/v1/organization")
public class OrganizationController {  
    private final OrganizationRepository organizationRepository;

    @Autowired
    public OrganizationController(OrganizationRepository organizationRepository) {
        this.organizationRepository = organizationRepository;
    }

    @PostMapping
    public ResponseEntity<Organization> create(@Valid @RequestBody Organization encounter) {
    	Organization savedEncounter = organizationRepository.save(encounter);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
            .buildAndExpand(savedEncounter.getId()).toUri();

        return ResponseEntity.created(location).body(savedEncounter);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Organization> update(@PathVariable Integer id, @Valid @RequestBody Organization encounter) {
        Optional<Organization> optionalEncounter = organizationRepository.findById(id);
        if (!optionalEncounter.isPresent()) {
            return ResponseEntity.unprocessableEntity().build();
        }

        encounter.setId(optionalEncounter.get().getId());
        organizationRepository.save(encounter);

        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Organization> delete(@PathVariable Integer id) {
        Optional<Organization> optionalEncounter = organizationRepository.findById(id);
        if (!optionalEncounter.isPresent()) {
            return ResponseEntity.unprocessableEntity().build();
        }

        organizationRepository.delete(optionalEncounter.get());

        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Organization> getById(@PathVariable Integer id) {
        Optional<Organization> optionalEncounter = organizationRepository.findById(id);
        if (!optionalEncounter.isPresent()) {
            return ResponseEntity.unprocessableEntity().build();
        }

        return ResponseEntity.ok(optionalEncounter.get());
    }

    @GetMapping
    public ResponseEntity<Page<Organization>> getAll(Pageable pageable) {
        return ResponseEntity.ok(organizationRepository.findAll(pageable));
    }
}
