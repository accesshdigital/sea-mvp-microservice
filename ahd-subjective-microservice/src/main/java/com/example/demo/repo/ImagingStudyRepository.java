package com.example.demo.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.model.ImagingStudy;


	public interface ImagingStudyRepository extends JpaRepository<ImagingStudy, Integer>{  

}
