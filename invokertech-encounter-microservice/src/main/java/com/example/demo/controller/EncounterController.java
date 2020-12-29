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

import com.example.demo.model.Encounter;
import com.example.demo.repo.EncounterRepository;



@RestController
@RequestMapping("/api/v1/encounter")
public class EncounterController {  
    private final EncounterRepository encounterRepository;

    @Autowired
    public EncounterController(EncounterRepository encounterRepository) {
        this.encounterRepository = encounterRepository;
    }

    @PostMapping
    public ResponseEntity<Encounter> create(@Valid @RequestBody Encounter encounter) {
        Encounter savedEncounter = encounterRepository.save(encounter);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
            .buildAndExpand(savedEncounter.getId()).toUri();

        return ResponseEntity.created(location).body(savedEncounter);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Encounter> update(@PathVariable Integer id, @Valid @RequestBody Encounter encounter) {
        Optional<Encounter> optionalEncounter = encounterRepository.findById(id);
        if (!optionalEncounter.isPresent()) {
            return ResponseEntity.unprocessableEntity().build();
        }

        encounter.setId(optionalEncounter.get().getId());
        encounterRepository.save(encounter);

        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Encounter> delete(@PathVariable Integer id) {
        Optional<Encounter> optionalEncounter = encounterRepository.findById(id);
        if (!optionalEncounter.isPresent()) {
            return ResponseEntity.unprocessableEntity().build();
        }

        encounterRepository.delete(optionalEncounter.get());

        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Encounter> getById(@PathVariable Integer id) {
        Optional<Encounter> optionalEncounter = encounterRepository.findById(id);
        if (!optionalEncounter.isPresent()) {
            return ResponseEntity.unprocessableEntity().build();
        }
        Link selfLink = linkTo(methodOn(EncounterController.class).getById(optionalEncounter.get().getId())).withSelfRel();
        optionalEncounter.get().add(selfLink);
        
        return ResponseEntity.ok(optionalEncounter.get());
    }

    @GetMapping
    public ResponseEntity<Page<Encounter>> getAll(Pageable pageable) {
        return ResponseEntity.ok(encounterRepository.findAll(pageable));
    }
}
