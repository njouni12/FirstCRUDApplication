package com.example.CRUDSecuredApplication.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeRequest {
	@NotNull(message="Employee name shouldn't be NULL")
	private String name;
	
	@Min(18)
	@Max(50)
	private int age;
}
