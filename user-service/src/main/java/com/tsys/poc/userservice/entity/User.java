package com.tsys.poc.userservice.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Objects;

@Entity
@Table(name = "USER_TB")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
    @Id
    private String username;
    private String email;
    private int age;
    private String address;
    private String password;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User)) return false;
        User user = (User) o;
        return getAge() == user.getAge() &&
                getUsername().equals(user.getUsername()) &&
                getEmail().equals(user.getEmail()) &&
                Objects.equals(getAddress(), user.getAddress()) &&
                getPassword().equals(user.getPassword());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getUsername(), getEmail(), getAge(), getAddress(), getPassword());
    }
}
