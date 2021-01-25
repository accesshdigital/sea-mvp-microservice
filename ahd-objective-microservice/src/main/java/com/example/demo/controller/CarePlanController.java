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

import com.example.demo.model.CarePlan;
import com.example.demo.repo.CarePlanRepository;
@RestController
@RequestMapping("/api/v1/care-plan")
public class CarePlanController {  
    private final CarePlanRepository carePlanRepository;

    @Autowired
    public CarePlanController(CarePlanRepository carePlanRepository) {
        this.carePlanRepository = carePlanRepository;
    }

    @PostMapping
    public ResponseEntity<CarePlan> create(@Valid @RequestBody CarePlan carePlan) {
    	CarePlan savedCarePlan = carePlanRepository.save(carePlan);
    	
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
            .buildAndExpand(savedCarePlan.getId()).toUri();

        return ResponseEntity.created(location).body(savedCarePlan);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CarePlan> update(@PathVariable Integer id, @Valid @RequestBody CarePlan carePlan) {
        Optional<CarePlan> optionalCarePlan = carePlanRepository.findById(id);
        if (!optionalCarePlan.isPresent()) {
            return ResponseEntity.unprocessableEntity().build();
        }

      
        
        carePlan.setId(optionalCarePlan.get().getId());
        carePlanRepository.save(carePlan);

        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<CarePlan> delete(@PathVariable Integer id) {
        Optional<CarePlan> optionalCarePlan = carePlanRepository.findById(id);
        if (!optionalCarePlan.isPresent()) {
            return ResponseEntity.unprocessableEntity().build();
        }

        carePlanRepository.delete(optionalCarePlan.get());

        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<CarePlan> getById(@PathVariable Integer id) {
        Optional<CarePlan> optionalCarePlan = carePlanRepository.findById(id);
        if (!optionalCarePlan.isPresent()) {
            return ResponseEntity.unprocessableEntity().build();
        }
        Link selfLink = linkTo(methodOn(CarePlanController.class).getById(optionalCarePlan.get().getId())).withSelfRel();
        optionalCarePlan.get().add(selfLink);
        return ResponseEntity.ok(optionalCarePlan.get());
    }

    @GetMapping
    public ResponseEntity<Page<CarePlan>> getAll(Pageable pageable) {
        return ResponseEntity.ok(carePlanRepository.findAll(pageable));
    }
}
