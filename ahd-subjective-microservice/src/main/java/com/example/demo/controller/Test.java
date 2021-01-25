package com.example.demo.controller;

import java.util.ArrayList;
import java.util.List;

import com.example.demo.model.Address;

public class Test {

	public static void main(String[] args) {
		
		Address address = new Address();
		address.setText("gg");
		Address address2 = new Address();
		address2.setState("Ved");
		

		List<Address> list=new ArrayList<>();  
    	list.add(address);
    	list.add(address2);
    	System.out.println(list);

	}

}
