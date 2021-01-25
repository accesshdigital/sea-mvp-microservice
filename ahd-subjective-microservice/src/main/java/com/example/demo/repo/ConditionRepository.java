package com.example.demo.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.model.Condition;


	public interface ConditionRepository extends JpaRepository<Condition, Integer>{  

}
