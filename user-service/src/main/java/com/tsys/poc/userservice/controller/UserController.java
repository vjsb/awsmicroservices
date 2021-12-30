package com.tsys.poc.userservice.controller;

import com.tsys.poc.userservice.config.JWTValidator;
import com.tsys.poc.userservice.entity.User;
import com.tsys.poc.userservice.model.Response;
import com.tsys.poc.userservice.model.UserInfo;
import com.tsys.poc.userservice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

@RestController
@RequestMapping("/userinfo")
public class UserController {

	@Autowired
	private UserService userService;

	@Autowired
	private JWTValidator jWTValidator;

	@PostMapping("/create")
	public ResponseEntity<Response> create(@RequestBody User user, HttpServletRequest request) {
		Response returnData = new Response();
		try {
			returnData.setReturnData(userService.add(user));
			return new ResponseEntity<>(returnData, HttpStatus.OK);
		} catch (Exception ex) {
			returnData.setMessage(ex.getMessage());
			return new ResponseEntity<>(returnData, HttpStatus.CONFLICT);
		}

	}

	@GetMapping("/readAll")
	public ResponseEntity<Iterable<User>> readAll(HttpServletRequest request) {
		if (!jWTValidator.validateToken(request,"shubham")) {
			return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
		}
		return new ResponseEntity<>(userService.getAll(), HttpStatus.OK);
	}

	@GetMapping("/read/{username}")
	public ResponseEntity<Response> readByUsername(@PathVariable(name = "username") String username,
			HttpServletRequest request) {
		Response returnData = new Response();
		if (!jWTValidator.validateToken(request,username)) {
			returnData.setMessage("Not a valid user");
			return new ResponseEntity<>(returnData, HttpStatus.UNAUTHORIZED);
		}
		Optional<User> user = userService.getById(username);
		if (user.isPresent()) {
			returnData.setReturnData(user.get());
			return ResponseEntity.status(HttpStatus.OK).body(returnData);
		} else {
			String msg = "Username not exist";
			returnData.setMessage(msg);
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(returnData);
		}
	}

	@PutMapping("/update")
	public ResponseEntity<Response> update(@RequestBody User user, HttpServletRequest request) {
		Response returnData = new Response();
		if (!jWTValidator.validateToken(request,user.getUsername())) {
			returnData.setMessage("Not a valid user");
			return new ResponseEntity<>(returnData, HttpStatus.UNAUTHORIZED);
		}
		try {
			userService.updateUser(user);
			returnData.setMessage("Updated Successfully");
			returnData.setReturnData(user);
			return ResponseEntity.status(HttpStatus.OK).body(returnData);

		} catch (Exception e) {
			returnData.setMessage(e.getMessage());
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(returnData);
		}
	}

	@DeleteMapping("/delete/{username}")
	public ResponseEntity<Response> deleteByUsername(@PathVariable(name = "username") String username,
			HttpServletRequest request) {
		Response returnData = new Response();
		if (!jWTValidator.validateToken(request,username)) {
			returnData.setMessage("Not a valid user");
			return new ResponseEntity<>(returnData, HttpStatus.UNAUTHORIZED);
		}
		try {
			userService.deleteById(username);
			return ResponseEntity.status(HttpStatus.NO_CONTENT).body(returnData);
		} catch (Exception e) {
			returnData.setMessage(e.getMessage());
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(returnData);
		}

	}

}
