package com.example.CRUDSecuredApplication.Config;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.CRUDSecuredApplication.User.Role;
import com.example.CRUDSecuredApplication.User.UserRepository;

@Service
public class AuthenticationService {

	private UserRepository userRepository;
	
	private PasswordEncoder passwordEncoder;
	
	public AuthenticationResponse register(RegisterRequest request) {
		User user = User.builder()
			.firstname(request.getFirstname())
			.lastname(request.getLastname())
			.email(request.getEmail())
			.password(passwordEncoder.encode(request.getPassword()))
			.role(Role.USER)
			.build();
				
			return null;	
		
		
	}

	public AuthenticationResponse authenticate(AuthenticationRequest request) {
		return null;
	}

}
