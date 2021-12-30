package com.tsys.poc.userservice.model;

import com.tsys.poc.userservice.entity.User;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserInfo {
    private User user;
    private User updatedUser;

}
