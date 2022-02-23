package com.abc.ecom.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.abc.ecom.entity.Customer;
import com.abc.ecom.service.CustomerService;


@RestController
@RequestMapping
public class CustomerController {
	
	@Autowired
	private CustomerService customerService;

	@PostMapping("/save")
	public ResponseEntity<Customer> addCustomer(@RequestBody Customer customer) {

	Customer newCustomer = customerService.saveCustomer(customer);
	ResponseEntity<Customer> responseEntity = new ResponseEntity<>(newCustomer,HttpStatus.CREATED);
	return responseEntity;
	}

	@GetMapping("/all")
	public List<Customer> fetchAllCustomers(){

	List<Customer> customers = customerService.getAllCustomers();

	return customers;

	}

	@GetMapping("/get/{pid}")
	public ResponseEntity<?> fetchCustomerDetails(@PathVariable("pid") int customerId) {

	Customer customer = customerService.getCustomerById(customerId);
	return new ResponseEntity<>(customer, HttpStatus.OK);
	}

	@DeleteMapping("/delete/{pid}")
	public ResponseEntity<?> deleteCustomer(@PathVariable("pid") int customerId) {

	customerService.deleteCustomer(customerId);
	return new ResponseEntity<>("Customer Deleted with id:" + customerId, HttpStatus.OK);
	}

	@PutMapping("/update")
	public ResponseEntity<Customer> modifyCustomer(@RequestBody Customer customer) {

	Customer updatedCustomer = customerService.updateCustomer(customer);
	return new ResponseEntity<>(updatedCustomer, HttpStatus.OK);
	}

}
