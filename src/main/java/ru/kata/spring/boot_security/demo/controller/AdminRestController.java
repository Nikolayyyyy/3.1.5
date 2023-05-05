package ru.kata.spring.boot_security.demo.controller;



import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.service.RoleService;
import ru.kata.spring.boot_security.demo.service.UserService;
import ru.kata.spring.boot_security.demo.util.Mapper;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/api/admin")
public class AdminRestController  {

    private final UserService userService;

    private final RoleService roleService;

    private final Mapper mapper;

    public AdminRestController(UserService userService, RoleService roleService, Mapper mapper) {
        this.userService = userService;
        this.roleService = roleService;
        this.mapper = mapper;
    }

    @GetMapping("/roles")
    public ResponseEntity<List<Role>> getAllRoles() {
        return ResponseEntity.ok(roleService.getAllRoles());
    }

    @GetMapping("/viewUser")
    public ResponseEntity<User> showUser(Principal principal) {
        return ResponseEntity.ok(userService.findByUsername(principal.getName()));
    }

    @GetMapping("/users")
    public ResponseEntity<List<User>> getUsers() {
        return ResponseEntity.ok(userService.allUsers());
    }


    @PostMapping( "/users")
    public ResponseEntity<HttpStatus> createUser(@RequestBody User user) {
        userService.saveUser(user);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @GetMapping("/users/{id}")
    public ResponseEntity<User> show(@PathVariable("id") Long id) {
        return ResponseEntity.ok(userService.getByUserId(id));
    }

    @PutMapping("/users")
    public ResponseEntity<HttpStatus> update(@RequestBody User user) {
        userService.update(user);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @DeleteMapping("/users/{id}")
    public ResponseEntity<HttpStatus> delete(@PathVariable("id") Long id) {
        userService.deleteUser(id);
        return ResponseEntity.ok(HttpStatus.OK);
    }

}
