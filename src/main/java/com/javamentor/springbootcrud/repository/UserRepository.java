package com.javamentor.springbootcrud.repository;

import com.javamentor.springbootcrud.entity.User;

import java.util.List;

public interface UserRepository {

    List<User> findAll();

    User findById(Long id);

    void save(User user);

    void deleteById(Long id);

    void updateUser(User user);

    User getUserByName(String username);
}