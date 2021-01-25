package com.example.demo.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.model.Immunization;

	public interface ImmunizationRepository extends JpaRepository<Immunization, Integer>{  

}
