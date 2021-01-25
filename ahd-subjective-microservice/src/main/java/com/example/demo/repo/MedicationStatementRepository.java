package com.example.demo.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.model.MedicationStatement;


	public interface MedicationStatementRepository extends JpaRepository<MedicationStatement, Integer>{  

}
