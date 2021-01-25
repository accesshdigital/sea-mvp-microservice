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

import com.example.demo.model.ImagingStudy;
import com.example.demo.repo.ImagingStudyRepository;

@RestController
@RequestMapping("/api/v1/imaging-study")
public class ImagingStudyController {  
    private final ImagingStudyRepository imagingStudyRepository;

    @Autowired
    public ImagingStudyController(ImagingStudyRepository imagingStudyRepository) {
        this.imagingStudyRepository = imagingStudyRepository;
    }

    @PostMapping
    public ResponseEntity<ImagingStudy> create(@Valid @RequestBody ImagingStudy imagingStudy) {
    	ImagingStudy savedImagingStudy = imagingStudyRepository.save(imagingStudy);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
            .buildAndExpand(savedImagingStudy.getId()).toUri();

        return ResponseEntity.created(location).body(savedImagingStudy);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ImagingStudy> update(@PathVariable Integer id, @Valid @RequestBody ImagingStudy imagingStudy) {
        Optional<ImagingStudy> optionalImagingStudy = imagingStudyRepository.findById(id);
        if (!optionalImagingStudy.isPresent()) {
            return ResponseEntity.unprocessableEntity().build();
        }

        imagingStudy.setId(optionalImagingStudy.get().getId());
        imagingStudyRepository.save(imagingStudy);

        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ImagingStudy> delete(@PathVariable Integer id) {
        Optional<ImagingStudy> optionalImagingStudy = imagingStudyRepository.findById(id);
        if (!optionalImagingStudy.isPresent()) {
            return ResponseEntity.unprocessableEntity().build();
        }

        imagingStudyRepository.delete(optionalImagingStudy.get());

        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ImagingStudy> getById(@PathVariable Integer id) {
        Optional<ImagingStudy> optionalImagingStudy = imagingStudyRepository.findById(id);
        if (!optionalImagingStudy.isPresent()) {
            return ResponseEntity.unprocessableEntity().build();
        }

        return ResponseEntity.ok(optionalImagingStudy.get());
    }

    @GetMapping
    public ResponseEntity<Page<ImagingStudy>> getAll(Pageable pageable) {
        return ResponseEntity.ok(imagingStudyRepository.findAll(pageable));
    }
}
