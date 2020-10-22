package com.javamentor.springbootcrud.web;

import com.javamentor.springbootcrud.entity.Role;
import com.javamentor.springbootcrud.entity.User;
import com.javamentor.springbootcrud.repository.RoleRepository;
import com.javamentor.springbootcrud.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@RestController
public class RestUserController {

    @Autowired
    private UserService userService;

    @Autowired
    private RoleRepository roleRepository;

    @GetMapping(value = "/users")
    public ResponseEntity<List<User>> readAll() {
        final List<User> users = userService.getAllUsers();

        return users != null
                ? new ResponseEntity<>(users, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping(value = "/user/{id}")
    public ResponseEntity<User> read(@PathVariable(name = "id") Long id) {
        final User user = userService.getUserById(id);

        return user != null
                ? new ResponseEntity<>(user, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping(value = "/user/{id}")
    public ResponseEntity<?> delete(@PathVariable(name = "id") Long id) {
        final boolean deleted = userService.deleteUserById(id);

        return deleted
                ? new ResponseEntity<>(HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
    }

    @PutMapping(value = "/update")
    public ResponseEntity<?> update(@RequestBody User user, @RequestParam("roles") String[] roles) {
        assignRoles(user, roles, roleRepository);
        final boolean updated = userService.updateUser(user);
        return updated
                ? new ResponseEntity<>(HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
    }

    @PostMapping(value = "/save")
    public ResponseEntity<?> save(@RequestBody User user, @RequestParam("roles") String[] roles) {
        assignRoles(user, roles, roleRepository);
        final boolean saved = userService.saveUser(user);

        return saved
                ? new ResponseEntity<>(HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
    }

    static void assignRoles(@RequestBody User user, String[] roles, RoleRepository roleRepository) {
        Set<Role> new_roles = new HashSet<>();
        if (Arrays.toString(roles).contains("ROLE_ADMIN")) {
            new_roles.add(roleRepository.getById(2L));
        }
        if (Arrays.toString(roles).contains("ROLE_USER")) {
            new_roles.add(roleRepository.getById(1L));
        }
        user.setRoles(new_roles);
    }

}
