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

import com.example.demo.model.DocumentReference;
import com.example.demo.repo.DocumentReferenceRepository;

@RestController
@RequestMapping("/api/v1/document-reference")
public class DocumentReferenceController {  
    private final DocumentReferenceRepository documentReferenceRepository;

    @Autowired
    public DocumentReferenceController(DocumentReferenceRepository documentReferenceRepository) {
        this.documentReferenceRepository = documentReferenceRepository;
    }

    @PostMapping
    public ResponseEntity<DocumentReference> create(@Valid @RequestBody DocumentReference encounter) {
    	DocumentReference savedEncounter = documentReferenceRepository.save(encounter);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
            .buildAndExpand(savedEncounter.getId()).toUri();

        return ResponseEntity.created(location).body(savedEncounter);
    }

    @PutMapping("/{id}")
    public ResponseEntity<DocumentReference> update(@PathVariable Integer id, @Valid @RequestBody DocumentReference encounter) {
        Optional<DocumentReference> optionalEncounter = documentReferenceRepository.findById(id);
        if (!optionalEncounter.isPresent()) {
            return ResponseEntity.unprocessableEntity().build();
        }

        encounter.setId(optionalEncounter.get().getId());
        documentReferenceRepository.save(encounter);

        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<DocumentReference> delete(@PathVariable Integer id) {
        Optional<DocumentReference> optionalEncounter = documentReferenceRepository.findById(id);
        if (!optionalEncounter.isPresent()) {
            return ResponseEntity.unprocessableEntity().build();
        }

        documentReferenceRepository.delete(optionalEncounter.get());

        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<DocumentReference> getById(@PathVariable Integer id) {
        Optional<DocumentReference> optionalEncounter = documentReferenceRepository.findById(id);
        if (!optionalEncounter.isPresent()) {
            return ResponseEntity.unprocessableEntity().build();
        }

        return ResponseEntity.ok(optionalEncounter.get());
    }

    @GetMapping
    public ResponseEntity<Page<DocumentReference>> getAll(Pageable pageable) {
        return ResponseEntity.ok(documentReferenceRepository.findAll(pageable));
    }
}
