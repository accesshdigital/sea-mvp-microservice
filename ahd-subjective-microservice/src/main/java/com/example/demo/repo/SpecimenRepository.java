package com.example.demo.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.model.Specimen;

	public interface SpecimenRepository extends JpaRepository<Specimen, Integer>{  

}
