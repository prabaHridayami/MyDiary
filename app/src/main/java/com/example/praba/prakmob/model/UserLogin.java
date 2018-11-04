package com.example.praba.prakmob.model;

public class UserLogin {
    public static String getUsername() {
        return username;
    }

    public static void setUsername(String username) {
        UserLogin.username = username;
    }

    public static String getPassword() {
        return password;
    }

    public static void setPassword(String password) {
        UserLogin.password = password;
    }

    public static String username;
    public static String password;
}
