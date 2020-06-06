package com.invokertech.hystrix.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.github.javafaker.Faker;
import com.invokertech.hystrix.model.Test;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

@RestController
@RequestMapping(value= "/api")
public class Restcontroller {

	static Faker faker= new Faker();

	@GetMapping(value= "/test")
	@HystrixCommand(fallbackMethod= "defaultResponse")
	public ResponseEntity<Test> getProduct() {

		Test t = new Test();
		t.setId(1001);
		t.setTest1(faker.name().firstName());
		t.setTest2(faker.name().lastName());
		t.setTest3(faker.address().city());

		// Throwing an error for illustrating that the microservice is down and the fallback method will be called for sending a dummy response.
		if(t.getId() == 1001) {
			throw new RuntimeException();
		}

		return new ResponseEntity<Test>(t, HttpStatus.OK);
	}

	// When we define a fallback-method, the fallback-method must match the same parameters of the method where you define the Hystrix Command
	// using the hystrix-command annotation.
	public ResponseEntity<Test> defaultResponse() {
		System.out.println("You are seeing this fallback response because the underlying microservice is down or has thrown an error!");

		Test fallbackItem = new Test();
		fallbackItem.setTest1("Dummy value");
		fallbackItem.setTest2("Dummy Name");
		fallbackItem.setTest3("Dummy test");

		return new ResponseEntity<Test>(fallbackItem, HttpStatus.INTERNAL_SERVER_ERROR);
	}

}