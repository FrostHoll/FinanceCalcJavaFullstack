package com.frostholl.financeCalcBackend.user;

import com.frostholl.financeCalcBackend.userRole.Role;
import com.frostholl.financeCalcBackend.userRole.UserRoles;
import com.frostholl.financeCalcBackend.userRole.UserRolesService;
import jakarta.persistence.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;
import java.util.Set;

@Entity
@Table(name = "user_profile")
public class User implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Integer id;

    private String login;

    private String password;

    private String user_name;

    private String user_surname;

    private boolean active;

    public User() {
    }

    public User(Integer id,
                String login,
                String password,
                String user_name,
                String user_surname,
                boolean active) {
        this.id = id;
        this.login = login;
        this.password = password;
        this.user_name = user_name;
        this.user_surname = user_surname;
        this.active = active;
    }

    public User(String login,
                String password,
                String user_name,
                String user_surname,
                boolean active) {
        this.login = login;
        this.password = password;
        this.user_name = user_name;
        this.user_surname = user_surname;
        this.active = active;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singleton(Role.USER);
    }

    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return login;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return active;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getUser_surname() {
        return user_surname;
    }

    public void setUser_surname(String user_surname) {
        this.user_surname = user_surname;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", login='" + login + '\'' +
                '}';
    }
}
