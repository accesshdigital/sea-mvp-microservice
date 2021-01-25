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

import com.example.demo.model.Media;
import com.example.demo.repo.MediaRepository;

@RestController
@RequestMapping("/api/v1/media")
public class MediaController {  
    private final MediaRepository mediaRepository;

    @Autowired
    public MediaController(MediaRepository mediaRepository) {
        this.mediaRepository = mediaRepository;
    }

    @PostMapping
    public ResponseEntity<Media> create(@Valid @RequestBody Media encounter) {
        Media savedEncounter = mediaRepository.save(encounter);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
            .buildAndExpand(savedEncounter.getId()).toUri();

        return ResponseEntity.created(location).body(savedEncounter);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Media> update(@PathVariable Integer id, @Valid @RequestBody Media media) {
        Optional<Media> optionalMedia = mediaRepository.findById(id);
        if (!optionalMedia.isPresent()) {
            return ResponseEntity.unprocessableEntity().build();
        }

        media.setId(optionalMedia.get().getId());
        mediaRepository.save(media);

        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Media> delete(@PathVariable Integer id) {
        Optional<Media> optionalMedia = mediaRepository.findById(id);
        if (!optionalMedia.isPresent()) {
            return ResponseEntity.unprocessableEntity().build();
        }

        mediaRepository.delete(optionalMedia.get());

        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Media> getById(@PathVariable Integer id) {
        Optional<Media> optionalMedia = mediaRepository.findById(id);
        if (!optionalMedia.isPresent()) {
            return ResponseEntity.unprocessableEntity().build();
        }

        return ResponseEntity.ok(optionalMedia.get());
    }

    @GetMapping
    public ResponseEntity<Page<Media>> getAll(Pageable pageable) {
        return ResponseEntity.ok(mediaRepository.findAll(pageable));
    }
}
