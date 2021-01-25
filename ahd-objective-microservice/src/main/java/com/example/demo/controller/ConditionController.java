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

import com.example.demo.repo.ConditionRepository;
import com.example.demo.model.Condition;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;
@RestController
@RequestMapping("/api/v1/condition")
public class ConditionController {  
    private final ConditionRepository conditionRepository;

    @Autowired
    public ConditionController(ConditionRepository conditionRepository) {
        this.conditionRepository = conditionRepository;
    }

    @PostMapping
    public ResponseEntity<Condition> create(@Valid @RequestBody Condition encounter) {
    	Condition savedEncounter = conditionRepository.save(encounter);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
            .buildAndExpand(savedEncounter.getId()).toUri();

        return ResponseEntity.created(location).body(savedEncounter);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Condition> update(@PathVariable Integer id, @Valid @RequestBody Condition encounter) {
        Optional<Condition> optionalEncounter = conditionRepository.findById(id);
        if (!optionalEncounter.isPresent()) {
            return ResponseEntity.unprocessableEntity().build();
        }

        encounter.setId(optionalEncounter.get().getId());
        conditionRepository.save(encounter);

        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Condition> delete(@PathVariable Integer id) {
        Optional<Condition> optionalEncounter = conditionRepository.findById(id);
        if (!optionalEncounter.isPresent()) {
            return ResponseEntity.unprocessableEntity().build();
        }

        conditionRepository.delete(optionalEncounter.get());

        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Condition> getById(@PathVariable Integer id) {
        Optional<Condition> optionalCondition = conditionRepository.findById(id);
        if (!optionalCondition.isPresent()) {
            return ResponseEntity.unprocessableEntity().build();
        }
        Link selfLink = linkTo(methodOn(ConditionController.class).getById(optionalCondition.get().getId())).withSelfRel();
        optionalCondition.get().add(selfLink);
        
        return ResponseEntity.ok(optionalCondition.get());
    }

    @GetMapping
    public ResponseEntity<Page<Condition>> getAll(Pageable pageable) {
        return ResponseEntity.ok(conditionRepository.findAll(pageable));
    }
}
