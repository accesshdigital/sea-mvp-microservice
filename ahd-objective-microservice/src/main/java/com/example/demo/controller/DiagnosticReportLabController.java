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

import com.example.demo.model.DiagnosticReportLab;
import com.example.demo.repo.DiagnosticReportLabRepository;

@RestController
@RequestMapping("/api/v1/diagnostic-report-lab")
public class DiagnosticReportLabController {  
    private final DiagnosticReportLabRepository diagnosticReportLabRepository;

    @Autowired
    public DiagnosticReportLabController(DiagnosticReportLabRepository diagnosticReportLabRepository) {
        this.diagnosticReportLabRepository = diagnosticReportLabRepository;
    }

    @PostMapping
    public ResponseEntity<DiagnosticReportLab> create(@Valid @RequestBody DiagnosticReportLab encounter) {
    	DiagnosticReportLab savedEncounter = diagnosticReportLabRepository.save(encounter);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
            .buildAndExpand(savedEncounter.getId()).toUri();

        return ResponseEntity.created(location).body(savedEncounter);
    }

    @PutMapping("/{id}")
    public ResponseEntity<DiagnosticReportLab> update(@PathVariable Integer id, @Valid @RequestBody DiagnosticReportLab encounter) {
        Optional<DiagnosticReportLab> optionalEncounter = diagnosticReportLabRepository.findById(id);
        if (!optionalEncounter.isPresent()) {
            return ResponseEntity.unprocessableEntity().build();
        }

        encounter.setId(optionalEncounter.get().getId());
        diagnosticReportLabRepository.save(encounter);

        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<DiagnosticReportLab> delete(@PathVariable Integer id) {
        Optional<DiagnosticReportLab> optionalEncounter = diagnosticReportLabRepository.findById(id);
        if (!optionalEncounter.isPresent()) {
            return ResponseEntity.unprocessableEntity().build();
        }

        diagnosticReportLabRepository.delete(optionalEncounter.get());

        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<DiagnosticReportLab> getById(@PathVariable Integer id) {
        Optional<DiagnosticReportLab> optionalEncounter = diagnosticReportLabRepository.findById(id);
        if (!optionalEncounter.isPresent()) {
            return ResponseEntity.unprocessableEntity().build();
        }

        return ResponseEntity.ok(optionalEncounter.get());
    }

    @GetMapping
    public ResponseEntity<Page<DiagnosticReportLab>> getAll(Pageable pageable) {
        return ResponseEntity.ok(diagnosticReportLabRepository.findAll(pageable));
    }
}
