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

import com.example.demo.model.Appointment;
import com.example.demo.repo.AppointmentRepository;
@RestController
@RequestMapping("/api/v1/appointment")
public class AppointmentController {  
    private final AppointmentRepository appointmentRepository;

    @Autowired
    public AppointmentController(AppointmentRepository appointmentRepository) {
        this.appointmentRepository = appointmentRepository;
    }

    @PostMapping
    public ResponseEntity<Appointment> create(@Valid @RequestBody Appointment appointment) {
    	Appointment savedAppointment = appointmentRepository.save(appointment);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
            .buildAndExpand(savedAppointment.getId()).toUri();

        return ResponseEntity.created(location).body(savedAppointment);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Appointment> update(@PathVariable Integer id, @Valid @RequestBody Appointment encounter) {
        Optional<Appointment> optionalEncounter = appointmentRepository.findById(id);
        if (!optionalEncounter.isPresent()) {
            return ResponseEntity.unprocessableEntity().build();
        }

        encounter.setId(optionalEncounter.get().getId());
        appointmentRepository.save(encounter);

        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Appointment> delete(@PathVariable Integer id) {
        Optional<Appointment> optionalAppointment = appointmentRepository.findById(id);
        if (!optionalAppointment.isPresent()) {
            return ResponseEntity.unprocessableEntity().build();
        }

        appointmentRepository.delete(optionalAppointment.get());

        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Appointment> getById(@PathVariable Integer id) {
        Optional<Appointment> optionalAppointment = appointmentRepository.findById(id);
        if (!optionalAppointment.isPresent()) {
            return ResponseEntity.unprocessableEntity().build();
        }
        
        Link selfLink = linkTo(methodOn(AppointmentController.class).getById(optionalAppointment.get().getId())).withSelfRel();
        optionalAppointment.get().add(selfLink);
        
        return ResponseEntity.ok(optionalAppointment.get());
    }

    @GetMapping
    public ResponseEntity<Page<Appointment>> getAll(Pageable pageable) {
        return ResponseEntity.ok(appointmentRepository.findAll(pageable));
    }
}
