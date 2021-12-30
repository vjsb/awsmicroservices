package com.tsys.poc.auth.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tsys.poc.auth.service.TestService;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/api/validate")
@AllArgsConstructor
public class TestController {

	TestService testService;
	@PostMapping("/token")
	public ResponseEntity<String> signup(@RequestBody String username) {
		String result =testService.save(username);
		return new ResponseEntity<>(result, HttpStatus.OK);
	}
}
