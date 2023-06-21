package com.pixels.parquediversiones.domain.repository;

import com.pixels.parquediversiones.domain.UserAccount;

import java.util.List;
import java.util.Optional;

public interface UserRepository {
    List<UserAccount> getAll();
    Optional<UserAccount> getUser(int userId);
    Optional<UserAccount> getByEmail(String email);
    boolean existsUser(String email);
    UserAccount save(UserAccount userAccount);
    void delete(int userId);
}
