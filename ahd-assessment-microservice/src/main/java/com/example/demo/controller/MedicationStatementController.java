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

import com.example.demo.model.MedicationStatement;
import com.example.demo.repo.MedicationStatementRepository;

@RestController
@RequestMapping("/api/v1/medication-statement")
public class MedicationStatementController {  
    private final MedicationStatementRepository medicationStatementRepository;

    @Autowired
    public MedicationStatementController(MedicationStatementRepository medicationStatementRepository) {
        this.medicationStatementRepository = medicationStatementRepository;
    }

    @PostMapping
    public ResponseEntity<MedicationStatement> create(@Valid @RequestBody MedicationStatement medicationStatement) {
    	MedicationStatement savedMedicationStatement = medicationStatementRepository.save(medicationStatement);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
            .buildAndExpand(savedMedicationStatement.getId()).toUri();

        return ResponseEntity.created(location).body(savedMedicationStatement);
    }

    @PutMapping("/{id}")
    public ResponseEntity<MedicationStatement> update(@PathVariable Integer id, @Valid @RequestBody MedicationStatement medicationStatement) {
        Optional<MedicationStatement> optionalSavedMedicationStatement = medicationStatementRepository.findById(id);
        if (!optionalSavedMedicationStatement.isPresent()) {
            return ResponseEntity.unprocessableEntity().build();
        }

        medicationStatement.setId(optionalSavedMedicationStatement.get().getId());
        medicationStatementRepository.save(medicationStatement);

        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<MedicationStatement> delete(@PathVariable Integer id) {
        Optional<MedicationStatement> optionalSavedMedicationStatement = medicationStatementRepository.findById(id);
        if (!optionalSavedMedicationStatement.isPresent()) {
            return ResponseEntity.unprocessableEntity().build();
        }

        medicationStatementRepository.delete(optionalSavedMedicationStatement.get());

        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<MedicationStatement> getById(@PathVariable Integer id) {
        Optional<MedicationStatement> optionalSavedMedicationStatement = medicationStatementRepository.findById(id);
        if (!optionalSavedMedicationStatement.isPresent()) {
            return ResponseEntity.unprocessableEntity().build();
        }

        return ResponseEntity.ok(optionalSavedMedicationStatement.get());
    }

    @GetMapping
    public ResponseEntity<Page<MedicationStatement>> getAll(Pageable pageable) {
        return ResponseEntity.ok(medicationStatementRepository.findAll(pageable));
    }
}
