package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.example.demo.repository.EmployeeRepository;
import com.example.demo.model.*;

import java.net.URI;
import java.util.*;


@RestController
public class EmployeeController {
	@Autowired
	EmployeeRepository emprep;
	/*@GetMapping-To retreive data
	 * @PostMapping- to insert data
	 * @PutMapping-to update data
	 * @DeleteMapping- to delete the data
	*/
	@GetMapping("/Employees")
	public List<Employee> retrieveAllStudents() {
		List<Employee> elist=emprep.findAll();
		System.out.println("List is "+elist);
		for(Employee e:elist)
		{
			System.out.println("data is "+e.getName());
		}
		
		System.out.println("Coming here");
		return emprep.findAll();
		
	}
	
	@PostMapping("/employeeInsert")
	/*
	 * ResponseEnttity represents the whole httpresponse such as statuscode,
	 * headers or body.
	 */
	public ResponseEntity<Object> createStudent(@RequestBody Employee emp) {
		Employee saveEmployee = emprep.save(emp);
//http://localhost:8080/sstudent/102
		//it is going to change the url on the bases of different response
		URI location = 
			ServletUriComponentsBuilder.fromCurrentRequest().path("/{empid}")
				.buildAndExpand(saveEmployee.getId()).toUri();
//it is changing the uri from current location to location http://localhost:8080/students/1004
		return ResponseEntity.created(location).build();

}
	
}