package com.example.demo.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.model.MedicationRequest;

	public interface MedicationRequestRepository extends JpaRepository<MedicationRequest, Integer>{  

}
