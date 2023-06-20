package com.pixels.parquediversiones.domain.service;

import com.pixels.parquediversiones.domain.UserAccount;
import com.pixels.parquediversiones.domain.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public List<UserAccount> getAll() {
        return userRepository.getAll();
    }

    public Optional<UserAccount> getById(int userId) {
        return userRepository.getUser(userId);
    }

    public Optional<UserAccount> getByEmail(String email) {
        return userRepository.getByEmail(email);
    }

    public UserAccount save(UserAccount userAccount) {
        return userRepository.save(userAccount);
    }

    public boolean delete(int userId) {
        if(getById(userId).isPresent()) {
            userRepository.delete(userId);
            return true;
        } else {
            return false;
        }
    }
}
