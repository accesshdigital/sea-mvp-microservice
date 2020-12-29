package com.example.demo.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.model.Encounter;

	public interface EncounterRepository extends JpaRepository<Encounter, Integer>{  

}
