package com.tsys.poc.userservice.repository;

import com.tsys.poc.userservice.entity.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, String> {
}
