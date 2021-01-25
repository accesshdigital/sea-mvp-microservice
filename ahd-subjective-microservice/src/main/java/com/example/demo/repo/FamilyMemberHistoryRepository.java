package com.example.demo.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.model.FamilyMemberHistory;



	public interface FamilyMemberHistoryRepository extends JpaRepository<FamilyMemberHistory, Integer>{  

}
