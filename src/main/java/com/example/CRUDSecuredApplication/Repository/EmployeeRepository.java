package com.example.CRUDSecuredApplication.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.CRUDSecuredApplication.Entity.Employee;

public interface EmployeeRepository extends JpaRepository<Employee,Integer>{
	public Optional<Employee> findByName(String name);
}
