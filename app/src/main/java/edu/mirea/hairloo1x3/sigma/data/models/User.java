package edu.mirea.hairloo1x3.sigma.data.models;

public class User {
    public String login, password, email;
    public User() {

    }

    public User(String email, String login, String password) {
        this.email = email;
        this.login = login;
        this.password = password;
    }
}
