package com.example.demo.controller;



import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.net.URI;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.hateoas.Link;
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

import com.example.demo.model.AllergyIntolerance;
import com.example.demo.repo.AllergyIntoleranceRepository;

@RestController
@RequestMapping("/api/v1/allergey")
public class AllergyIntoleranceController {  
    private final AllergyIntoleranceRepository allergyIntoleranceRepository;

    @Autowired
    public AllergyIntoleranceController(AllergyIntoleranceRepository allergyIntoleranceRepository) {
        this.allergyIntoleranceRepository = allergyIntoleranceRepository;
    }

    @PostMapping
    public ResponseEntity<AllergyIntolerance> create(@Valid @RequestBody AllergyIntolerance encounter) {
    	AllergyIntolerance savedEncounter = allergyIntoleranceRepository.save(encounter);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
            .buildAndExpand(savedEncounter.getId()).toUri();

        return ResponseEntity.created(location).body(savedEncounter);
    }

    @PutMapping("/{id}")
    public ResponseEntity<AllergyIntolerance> update(@PathVariable Integer id, @Valid @RequestBody AllergyIntolerance encounter) {
        Optional<AllergyIntolerance> optionalEncounter = allergyIntoleranceRepository.findById(id);
        if (!optionalEncounter.isPresent()) {
            return ResponseEntity.unprocessableEntity().build();
        }

        encounter.setId(optionalEncounter.get().getId());
        allergyIntoleranceRepository.save(encounter);

        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<AllergyIntolerance> delete(@PathVariable Integer id) {
        Optional<AllergyIntolerance> optionalEncounter = allergyIntoleranceRepository.findById(id);
        if (!optionalEncounter.isPresent()) {
            return ResponseEntity.unprocessableEntity().build();
        }

        allergyIntoleranceRepository.delete(optionalEncounter.get());

        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<AllergyIntolerance> getById(@PathVariable Integer id) {
        Optional<AllergyIntolerance> optionalAllergyIntolerance = allergyIntoleranceRepository.findById(id);
        if (!optionalAllergyIntolerance.isPresent()) {
            return ResponseEntity.unprocessableEntity().build();
        }
        Link selfLink = linkTo(methodOn(AllergyIntoleranceController.class).getById(optionalAllergyIntolerance.get().getId())).withSelfRel();
        optionalAllergyIntolerance.get().add(selfLink);
        

        return ResponseEntity.ok(optionalAllergyIntolerance.get());
    }

    @GetMapping
    public ResponseEntity<Page<AllergyIntolerance>> getAll(Pageable pageable) {
        return ResponseEntity.ok(allergyIntoleranceRepository.findAll(pageable));
    }
}
