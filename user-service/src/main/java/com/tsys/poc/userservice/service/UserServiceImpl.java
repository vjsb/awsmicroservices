package com.tsys.poc.userservice.service;

import com.tsys.poc.userservice.entity.User;
import com.tsys.poc.userservice.model.RegisterRequest;
import com.tsys.poc.userservice.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import javax.persistence.EntityNotFoundException;
import javax.persistence.PersistenceException;
import java.util.Optional;

@Component
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	RestTemplate restTemplate;

	@Override
	public User add(User user) {

		String theUrl = "http://AUTH-SERVICE/api/auth/signup";

		RegisterRequest request = new RegisterRequest();
		request.setEmail(user.getEmail());
		request.setUsername(user.getUsername());
		request.setPassword(user.getPassword());
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<RegisterRequest> requestEntity = new HttpEntity<>(request, headers);

		ResponseEntity<String> response = restTemplate.exchange(theUrl, HttpMethod.POST, requestEntity, String.class);

		if (response.getStatusCode() != HttpStatus.OK) {
			throw new EntityNotFoundException("User Already Exist");
		}
		return userRepository.save(user);

	}

	@Override
	public Optional<User> getById(String id) {
		return userRepository.findById(id);
	}

	@Override
	public Iterable<User> getAll() {
		return userRepository.findAll();
	}

	@Override
	public void delete(User user) {
		userRepository.delete(user);
	}

	@Override
	public void deleteById(String id) {
		Optional<User> deletedUser = getById(id);
		if (!deletedUser.isPresent()) {
			throw new EntityNotFoundException("username " + id + " does not exist.");
		}
		delete(deletedUser.get());
	}

	@Override
	public User updateUser(User user) {
		Optional<User> oldUser = getById(user.getUsername());
		if (!oldUser.isPresent()) {
			throw new EntityNotFoundException("User with username: " + user.getUsername() + " does not exist.");
		}
		if (oldUser.get().equals(user)) {
			throw new EntityNotFoundException("Information already updated");
		}
		delete(oldUser.get());
		oldUser.get().setAddress(user.getAddress());
		oldUser.get().setAge(user.getAge());
		return modify(oldUser.get());
	}

	@Override
	public User modify(User user) {
		return userRepository.save(user);
	}
}
