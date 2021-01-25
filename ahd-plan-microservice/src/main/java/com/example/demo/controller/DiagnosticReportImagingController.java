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

import com.example.demo.model.DiagnosticReportImaging;
import com.example.demo.repo.DiagnosticReportImagingRepository;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;
@RestController
@RequestMapping("/api/v1/diagnostic-report-imaging")
public class DiagnosticReportImagingController {  
    private final DiagnosticReportImagingRepository diagnosticReportImagingRepository;

    @Autowired
    public DiagnosticReportImagingController(DiagnosticReportImagingRepository diagnosticReportImagingRepository) {
        this.diagnosticReportImagingRepository = diagnosticReportImagingRepository;
    }

    @PostMapping
    public ResponseEntity<DiagnosticReportImaging> create(@Valid @RequestBody DiagnosticReportImaging encounter) {
    	DiagnosticReportImaging savedEncounter = diagnosticReportImagingRepository.save(encounter);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
            .buildAndExpand(savedEncounter.getId()).toUri();

        return ResponseEntity.created(location).body(savedEncounter);
    }

    @PutMapping("/{id}")
    public ResponseEntity<DiagnosticReportImaging> update(@PathVariable Integer id, @Valid @RequestBody DiagnosticReportImaging encounter) {
        Optional<DiagnosticReportImaging> optionalEncounter = diagnosticReportImagingRepository.findById(id);
        if (!optionalEncounter.isPresent()) {
            return ResponseEntity.unprocessableEntity().build();
        }

        encounter.setId(optionalEncounter.get().getId());
        diagnosticReportImagingRepository.save(encounter);

        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<DiagnosticReportImaging> delete(@PathVariable Integer id) {
        Optional<DiagnosticReportImaging> optionalEncounter = diagnosticReportImagingRepository.findById(id);
        if (!optionalEncounter.isPresent()) {
            return ResponseEntity.unprocessableEntity().build();
        }

        diagnosticReportImagingRepository.delete(optionalEncounter.get());

        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<DiagnosticReportImaging> getById(@PathVariable Integer id) {
        Optional<DiagnosticReportImaging> optionalEncounter = diagnosticReportImagingRepository.findById(id);
        if (!optionalEncounter.isPresent()) {
            return ResponseEntity.unprocessableEntity().build();
        }

        return ResponseEntity.ok(optionalEncounter.get());
    }

    @GetMapping
    public ResponseEntity<Page<DiagnosticReportImaging>> getAll(Pageable pageable) {
        return ResponseEntity.ok(diagnosticReportImagingRepository.findAll(pageable));
    }
}
