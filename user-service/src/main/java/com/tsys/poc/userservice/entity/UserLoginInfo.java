package com.tsys.poc.userservice.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Objects;

@Entity
@Table(name = "USERLOGININFO_TB")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserLoginInfo {
    @Id
    private String username;
    private String password;
    private boolean isEmailVerified;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof UserLoginInfo)) return false;
        UserLoginInfo that = (UserLoginInfo) o;
        return isEmailVerified() == that.isEmailVerified() &&
                getUsername().equals(that.getUsername()) &&
                getPassword().equals(that.getPassword());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getUsername(), getPassword(), isEmailVerified());
    }
}
