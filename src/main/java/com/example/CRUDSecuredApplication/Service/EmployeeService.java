package com.example.CRUDSecuredApplication.Service;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.CRUDSecuredApplication.Advice.EmployeeNotFoundException;
import com.example.CRUDSecuredApplication.Entity.Employee;
import com.example.CRUDSecuredApplication.Repository.EmployeeRepository;
import com.example.CRUDSecuredApplication.dto.EmployeeRequest;

@Service
public class EmployeeService {

	@Autowired
	EmployeeRepository employeeRepository;

	
	public Employee saveEmployee(EmployeeRequest employee) {
		Employee newEmployee = Employee.build(0, employee.getName(), employee.getAge());
		return employeeRepository.save(newEmployee);
	}
	
	public Optional<Employee> getEmployee(int id) throws EmployeeNotFoundException{
		Optional<Employee> emp =  employeeRepository.findById(id);
		if(emp.isPresent()) {
			return employeeRepository.findById(id);
		}else {
			throw new EmployeeNotFoundException("User with id = "+id+" is not Found !");
		}
	}
	
	public String updateEmployee(Employee employee) throws EmployeeNotFoundException {
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
			throw new EmployeeNotFoundException("Employee not found with id = "+employee.getId()+" !");
		}
	}
	
	public String deleteEmployee(int id) throws EmployeeNotFoundException{
		Optional<Employee> emp = employeeRepository.findById(id);
		if(emp.isPresent()) {
			employeeRepository.deleteById(id);
			return "Deleted Successfully ! ";
		}
		else {
			throw new EmployeeNotFoundException( "Employee with id : " +id+ " doesn't exists ! ");
		}
	}
	
	public List<Employee> getAllEmployees(){
		return employeeRepository.findAll();
	}
}
