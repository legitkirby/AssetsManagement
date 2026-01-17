package com.isoftstone.assetsmanagement.controller;

import com.isoftstone.assetsmanagement.entity.User;
import com.isoftstone.assetsmanagement.service.UserService;
import org.springframework.web.bind.annotation.*;
import com.isoftstone.assetsmanagement.dto.LoginRequest;
import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Resource
    private UserService userService;

    @GetMapping("/all")
    public List<User> findAllUsers() {
        return userService.findAllUsers();
    }

    @GetMapping("/{id}")
    public User findUserById(@PathVariable Integer id) {
        return userService.findUserById(id);
    }

    @GetMapping("/username/{username}")
    public User findUserByUsername(@PathVariable String username) {
        return userService.findUserByUsername(username);
    }

    @GetMapping("/department/{departmentId}")
    public List<User> findUsersByDepartment(@PathVariable Integer departmentId) {
        return userService.findUsersByDepartment(departmentId);
    }

    @GetMapping("/role/{role}")
    public List<User> findUsersByRole(@PathVariable String role) {
        return userService.findUsersByRole(role);
    }

    @PostMapping("/add")
    public int addUser(@RequestBody User user) {
        return userService.addUser(user);
    }

    @PutMapping("/update")
    public int updateUser(@RequestBody User user) {
        return userService.updateUser(user);
    }

    @DeleteMapping("/delete/{id}")
    public int deleteUserById(@PathVariable Integer id) {
        return userService.deleteUserById(id);
    }

    @PutMapping("/status/{id}")
    public int changeUserStatus(@PathVariable Integer id, @RequestParam String status) {
        return userService.changeUserStatus(id, status);
    }

    @GetMapping("/search")
    public List<User> searchUsers(@RequestParam String keyword) {
        return userService.searchUsers(keyword);
    }
    @PostMapping("/login")
    public User login(@RequestBody LoginRequest loginRequest) {
        return userService.login(loginRequest);
    }
    @GetMapping("/test/{username}")
    public User test(@PathVariable String username) {
        User user = userService.findUserByUsername(username);
        System.out.println(user);
        return user;
    }

}