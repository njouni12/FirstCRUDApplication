package com.example.CRUDSecuredApplication.Controller;


import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.CRUDSecuredApplication.Advice.EmployeeNotFoundException;
import com.example.CRUDSecuredApplication.Entity.Employee;
import com.example.CRUDSecuredApplication.Service.EmployeeService;
import com.example.CRUDSecuredApplication.dto.EmployeeRequest;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/employees")
public class EmployeeController {
	@Autowired
	EmployeeService employeeService;
	
	@PostMapping
	public Employee addEmployee(@RequestBody @Valid EmployeeRequest employee) {
		return employeeService.saveEmployee(employee);
	}
	
	@GetMapping(path="/{id}")
	public Optional<Employee> getEmployee(@PathVariable int id) throws EmployeeNotFoundException{
		return employeeService.getEmployee(id);
	}
	
	@PutMapping
	public String updateEmployee(@RequestBody Employee emp) throws EmployeeNotFoundException {
		return employeeService.updateEmployee(emp);
	}
	
	@DeleteMapping(path="/{id}")
	public String deleteEmployee(@PathVariable int id) throws EmployeeNotFoundException{
		return employeeService.deleteEmployee(id);
	}
	
	@GetMapping("/all")
	public List<Employee> getAll(){
		
		return employeeService.getAllEmployees();
	}
	
	@GetMapping(path="/all/{field}")
	public List<Employee> getAllBySorting(@PathVariable String field){
		return employeeService.findEmployeesWithSorting(field);
	}
	
	@GetMapping(path="/pagination/{offset}/{size}")
	public Page<Employee> getAllByPagination(@PathVariable int offset,@PathVariable int size){
		return employeeService.findEmployeesWithPagination(offset, size);
	}
	
	@GetMapping(path="/PagWithSort/{offset}/{size}/{sort}")
	public Page<Employee> getAllByBoth(@PathVariable int offset,@PathVariable int size,@PathVariable String sort){
		return employeeService.findEmployeesBySortingAndPagination(offset, size, sort);
	}
}
