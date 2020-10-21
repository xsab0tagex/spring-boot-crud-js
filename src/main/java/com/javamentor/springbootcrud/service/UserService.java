package com.javamentor.springbootcrud.service;

import java.util.List;

import com.javamentor.springbootcrud.entity.User;

public interface UserService {

    List<User> getAllUsers();

    User getUserById(Long id);

    boolean saveUser(User user);

    boolean updateUser(User user);

    void deleteUserById(Long id);

    User getUserByName(String username);

}
