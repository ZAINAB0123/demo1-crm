package ru.zainab.userservice.repository;

import ru.zainab.userservice.entity.User;

import java.util.List;
import java.util.Optional;

public interface UserRepository {
    List<User> findAll();

    User save(User user);

    Optional<User> findById(Long id);

    void delete(User user);

    Optional<User> findByName(String name);

    Optional<User> findByEmail(String email);

}
