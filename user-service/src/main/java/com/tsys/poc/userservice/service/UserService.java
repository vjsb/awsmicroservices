package com.tsys.poc.userservice.service;

import com.tsys.poc.userservice.entity.User;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public interface UserService {

    public User add(User user);
    
    public User modify(User user);

    public Optional<User> getById(String id);

    public Iterable<User> getAll();

    public void delete(User user);

    public void deleteById(String id);

    public User updateUser(User user);

}
