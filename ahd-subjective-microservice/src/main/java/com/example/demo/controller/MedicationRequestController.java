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

import com.example.demo.model.MedicationRequest;
import com.example.demo.repo.MedicationRequestRepository;

@RestController
@RequestMapping("/api/v1/medication-request")
public class MedicationRequestController {  
    private final MedicationRequestRepository medicationRequestRepository;

    @Autowired
    public MedicationRequestController(MedicationRequestRepository medicationRequestRepository) {
        this.medicationRequestRepository = medicationRequestRepository;
    }

    @PostMapping
    public ResponseEntity<MedicationRequest> create(@Valid @RequestBody MedicationRequest medicationRequest) {
    	MedicationRequest savedMedicationRequest = medicationRequestRepository.save(medicationRequest);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
            .buildAndExpand(savedMedicationRequest.getId()).toUri();

        return ResponseEntity.created(location).body(savedMedicationRequest);
    }

    @PutMapping("/{id}")
    public ResponseEntity<MedicationRequest> update(@PathVariable Integer id, @Valid @RequestBody MedicationRequest medicationRequest) {
        Optional<MedicationRequest> optionalMedicationRequest = medicationRequestRepository.findById(id);
        if (!optionalMedicationRequest.isPresent()) {
            return ResponseEntity.unprocessableEntity().build();
        }

        medicationRequest.setId(optionalMedicationRequest.get().getId());
        medicationRequestRepository.save(medicationRequest);

        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<MedicationRequest> delete(@PathVariable Integer id) {
        Optional<MedicationRequest> optionalMedicationRequest = medicationRequestRepository.findById(id);
        if (!optionalMedicationRequest.isPresent()) {
            return ResponseEntity.unprocessableEntity().build();
        }

        medicationRequestRepository.delete(optionalMedicationRequest.get());

        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<MedicationRequest> getById(@PathVariable Integer id) {
        Optional<MedicationRequest> optionalMedicationRequest = medicationRequestRepository.findById(id);
        if (!optionalMedicationRequest.isPresent()) {
            return ResponseEntity.unprocessableEntity().build();
        }
        Link selfLink = linkTo(methodOn(MedicationRequestController.class).getById(optionalMedicationRequest.get().getId())).withSelfRel();
        optionalMedicationRequest.get().add(selfLink);
        
        return ResponseEntity.ok(optionalMedicationRequest.get());
    }

    @GetMapping
    public ResponseEntity<Page<MedicationRequest>> getAll(Pageable pageable) {
        return ResponseEntity.ok(medicationRequestRepository.findAll(pageable));
    }
}
