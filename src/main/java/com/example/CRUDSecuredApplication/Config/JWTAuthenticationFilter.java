package com.example.CRUDSecuredApplication.Config;

import java.io.IOException;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import io.micrometer.common.lang.NonNull;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class JWTAuthenticationFilter extends OncePerRequestFilter{ 
	// OncePerRequestFilter means that this filter will be forces each time client request a request

	private JwtService jwtService;
	private UserDetailsService userDetailsService;
	
	@Override
	protected void doFilterInternal( 
			@NonNull HttpServletRequest request, 
			@NonNull HttpServletResponse response, 
			@NonNull FilterChain filterChain) // other filters
			throws ServletException, IOException {
		
		final String authHeader = request.getHeader("Authorization");
		final String jwt;
		final String userEmail;
		
		if(authHeader == null || !authHeader.startsWith("Bearer ")) {
			filterChain.doFilter(request, response);
			return;
		}
		jwt = authHeader.substring(7); //after bearer the token
		userEmail = jwtService.extractUsername(jwt); //extract userEmail from JWT Token I need a class
		
		if(userEmail != null && SecurityContextHolder.getContext().getAuthentication() == null) { //there is no already authentication
			UserDetails userDetails = this.userDetailsService.loadUserByUsername(userEmail);//from db
			if(jwtService.isTokenValid(jwt, userDetails)) { //if user is valid
				//here we need to update our security context 
				UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
						userDetails,null,userDetails.getAuthorities()
						);
				authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
				
				SecurityContextHolder.getContext().setAuthentication(authToken);
			}
		}
		filterChain.doFilter(request, response); //always after this if condition
	}

}












