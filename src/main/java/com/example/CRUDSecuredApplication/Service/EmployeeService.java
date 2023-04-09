package com.example.CRUDSecuredApplication.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.CRUDSecuredApplication.Entity.Employee;
import com.example.CRUDSecuredApplication.Repository.EmployeeRepository;

@Service
public class EmployeeService {

	@Autowired
	EmployeeRepository employeeRepository;
	
	public String saveEmployee(Employee employee) {
		Optional<Employee> exists = employeeRepository.findByName(employee.getName());
		if(exists.isPresent()) {
			return "Employee is already Exists";
		}
		else {
			employeeRepository.save(employee);
			return "Employee Added Successfully !";
		}
	}
	
	public Optional<Employee> getEmployee(int id) {
		return employeeRepository.findById(id);
	}
	
	public String updateEmployee(Employee employee) {
		Optional<Employee> emp = employeeRepository.findById(employee.getId());
		if(emp.isPresent()) {
		Employee newEmployee = new Employee();
		newEmployee.setId(employee.getId());
		newEmployee.setAge(employee.getAge());
		newEmployee.setName(employee.getName());
		employeeRepository.save(newEmployee);
		return "Updated Successfully ! ";
		}
		else {
			return "No Employee with this ID ";
		}
	}
	
	public String deleteEmployee(int id) {
		Optional<Employee> emp = employeeRepository.findById(id);
		if(emp.isPresent()) {
			employeeRepository.deleteById(id);
			return "Deleted Successfully ! ";
		}
		else {
			return "Employee with id : " +id+ " doesn't exists ! ";
		}
	}
	
	public List<Employee> getAllEmployees(){
		return employeeRepository.findAll();
	}
}
