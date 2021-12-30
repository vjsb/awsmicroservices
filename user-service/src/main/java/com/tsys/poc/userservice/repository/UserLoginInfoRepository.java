package com.tsys.poc.userservice.repository;

import com.tsys.poc.userservice.entity.UserLoginInfo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserLoginInfoRepository extends JpaRepository<UserLoginInfo, String> {
}
