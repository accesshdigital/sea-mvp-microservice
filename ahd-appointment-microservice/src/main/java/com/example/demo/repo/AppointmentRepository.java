package com.example.demo.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.model.Appointment;

	public interface AppointmentRepository extends JpaRepository<Appointment, Integer>{  

}
