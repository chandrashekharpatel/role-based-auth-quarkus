package com.demo.auth.security;

import jakarta.enterprise.context.ApplicationScoped;
import java.util.HashMap;
import java.util.Map;

@ApplicationScoped
public class UserService {

    private Map<String, User> users = new HashMap<>();

    public UserService() {
        // Add some dummy users
        users.put("bob", new User("bob", "pas123"));
    }

    public boolean isUserValid(String username) {
        return users.containsKey(username);
    }
    public boolean isValidCredentials(String username, String password) {
        // Check if the user exists
        if (!isUserValid(username)) {
            return false; // User does not exist
        }
    
        // Check if the provided password matches the user's password
        User user = users.get(username);
        return user.getPassword().equals(password);
    }
    
}
