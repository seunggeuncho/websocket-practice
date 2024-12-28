package com.practice.socket.User.service;

import com.practice.socket.User.model.User;
import com.practice.socket.User.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public User saveUser(String username, String password){
        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        return userRepository.save(user);
    }

    public User authenticate(String username, String password){
        return userRepository.findByUsername(username)
                .filter(user -> user.getPassword().equals(password))
                .orElse(null);

    }
}
