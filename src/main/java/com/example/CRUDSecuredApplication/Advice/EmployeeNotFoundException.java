package com.example.CRUDSecuredApplication.Advice;

public class EmployeeNotFoundException extends Exception{

	private static final long serialVersionUID = 1L;

	public EmployeeNotFoundException(String message) {
		super(message);
		}

	
}
