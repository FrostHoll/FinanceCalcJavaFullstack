package com.frostholl.financeCalcBackend.user;

import jakarta.persistence.*;

@Entity
@Table(name = "user_profile")
public class User {
    @Id
    @Column(name = "user_id")
    private Integer id;

    private String login;

    private String password;

    private String user_name;

    private String user_surname;

    public User() {
    }

    public User(Integer id,
                String login,
                String password,
                String user_name,
                String user_surname) {
        this.id = id;
        this.login = login;
        this.password = password;
        this.user_name = user_name;
        this.user_surname = user_surname;
    }

    public User(String login,
                String password,
                String user_name,
                String user_surname) {
        this.login = login;
        this.password = password;
        this.user_name = user_name;
        this.user_surname = user_surname;
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

    public String getPassword() {
        return password;
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

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", login='" + login + '\'' +
                ", password='" + password + '\'' +
                ", user_name='" + user_name + '\'' +
                ", user_surname='" + user_surname + '\'' +
                '}';
    }
}
