package com.example.demo.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.model.ServiceRequest;

	public interface ServiceRequestRepository extends JpaRepository<ServiceRequest, Integer>{  

}
