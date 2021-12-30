package com.tsys.poc.userservice.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
public class RegisterRequest {
	private String email;
    private String username;
    private String password;
}
