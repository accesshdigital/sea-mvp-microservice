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

import com.example.demo.model.FamilyMemberHistory;
import com.example.demo.repo.FamilyMemberHistoryRepository;

@RestController
@RequestMapping("/api/v1/family-member-history")
public class FamilyMemberHistoryController {  
    private final FamilyMemberHistoryRepository familyMemberHistoryRepository;

    @Autowired
    public FamilyMemberHistoryController(FamilyMemberHistoryRepository familyMemberHistoryRepository) {
        this.familyMemberHistoryRepository = familyMemberHistoryRepository;
    }

    @PostMapping
    public ResponseEntity<FamilyMemberHistory> create(@Valid @RequestBody FamilyMemberHistory familyMemberHistory) {
    	FamilyMemberHistory savedFamilyMemberHistory = familyMemberHistoryRepository.save(familyMemberHistory);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
            .buildAndExpand(savedFamilyMemberHistory.getId()).toUri();

        return ResponseEntity.created(location).body(savedFamilyMemberHistory);
    }

    @PutMapping("/{id}")
    public ResponseEntity<FamilyMemberHistory> update(@PathVariable Integer id, @Valid @RequestBody FamilyMemberHistory familyMemberHistory) {
        Optional<FamilyMemberHistory> optionalFamilyMemberHistory = familyMemberHistoryRepository.findById(id);
        if (!optionalFamilyMemberHistory.isPresent()) {
            return ResponseEntity.unprocessableEntity().build();
        }

        familyMemberHistory.setId(optionalFamilyMemberHistory.get().getId());
        familyMemberHistoryRepository.save(familyMemberHistory);

        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<FamilyMemberHistory> delete(@PathVariable Integer id) {
        Optional<FamilyMemberHistory> optionalFamilyMemberHistory = familyMemberHistoryRepository.findById(id);
        if (!optionalFamilyMemberHistory.isPresent()) {
            return ResponseEntity.unprocessableEntity().build();
        }

        familyMemberHistoryRepository.delete(optionalFamilyMemberHistory.get());

        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<FamilyMemberHistory> getById(@PathVariable Integer id) {
        Optional<FamilyMemberHistory> optionalFamilyMemberHistory = familyMemberHistoryRepository.findById(id);
        if (!optionalFamilyMemberHistory.isPresent()) {
            return ResponseEntity.unprocessableEntity().build();
        }

        return ResponseEntity.ok(optionalFamilyMemberHistory.get());
    }

    @GetMapping
    public ResponseEntity<Page<FamilyMemberHistory>> getAll(Pageable pageable) {
        return ResponseEntity.ok(familyMemberHistoryRepository.findAll(pageable));
    }
}
