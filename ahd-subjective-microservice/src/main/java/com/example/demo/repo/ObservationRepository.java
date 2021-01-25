package com.example.demo.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.model.Observation;


	public interface ObservationRepository extends JpaRepository<Observation, Integer>{  

}
