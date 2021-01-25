package com.example.demo.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.model.CarePlan;

	public interface CarePlanRepository extends JpaRepository<CarePlan, Integer>{  

}
