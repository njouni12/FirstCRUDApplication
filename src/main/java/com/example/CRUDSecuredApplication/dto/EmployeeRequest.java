package com.example.CRUDSecuredApplication.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeRequest {
	
	@NotNull(message="Employee name shouldn't be NULL") // it accepts empty string 
	@NotBlank // here at least it should have one character
	@Size(min=3,max=20,message="Enter a name between 3 and 20 characters")
	private String name;
	
	@Min(18)
	@Max(50)
	private int age;
}
