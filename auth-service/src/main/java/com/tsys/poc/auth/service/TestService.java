package com.tsys.poc.auth.service;

import org.springframework.stereotype.Service;

import com.tsys.poc.auth.model.User;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class TestService {

	private final AuthService authService;
	
	public String save(String username) {
		User user =authService.getCurrentUser();
        if(user.getUsername().equals(username))
        	return user.getUsername();
        else
        		return null;
    }
}
