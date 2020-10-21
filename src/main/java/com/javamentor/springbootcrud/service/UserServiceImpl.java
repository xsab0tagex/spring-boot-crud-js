package com.javamentor.springbootcrud.service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.transaction.Transactional;

import com.javamentor.springbootcrud.entity.Role;
import com.javamentor.springbootcrud.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javamentor.springbootcrud.entity.User;
import com.javamentor.springbootcrud.repository.UserRepository;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public User getUserById(Long id) {
        return userRepository.findById(id);
    }

    @Override
    public boolean saveUser(User user) {
        Set<Role> roles = new HashSet<>();
        roles.add(roleRepository.getById(1L));
        user.setRoles(roles);
        try {
            userRepository.save(user);
            return true;
        } catch (Exception ex) {
            return false;
        }
    }

    @Override
    public boolean updateUser(User user) {
        try {
            userRepository.updateUser(user);
            return true;
        } catch (Exception ex) {
            return false;
        }
    }

    @Override
    public void deleteUserById(Long id) {
        userRepository.deleteById(id);
    }

    @Override
    public User getUserByName(String username) {
        return userRepository.getUserByName(username);
    }

}
