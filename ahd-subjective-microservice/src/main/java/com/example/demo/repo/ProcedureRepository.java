package com.example.demo.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.model.Procedure;

	public interface ProcedureRepository extends JpaRepository<Procedure, Integer>{  

}
