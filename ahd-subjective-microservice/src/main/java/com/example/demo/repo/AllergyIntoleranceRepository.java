package com.example.demo.repo;




import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.model.AllergyIntolerance;

public interface AllergyIntoleranceRepository extends JpaRepository<AllergyIntolerance, Integer>{  
}
