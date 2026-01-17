package com.isoftstone.assetsmanagement.service;

import com.isoftstone.assetsmanagement.entity.User;
import com.isoftstone.assetsmanagement.dto.LoginRequest;
import java.util.List;

public interface UserService {
    List<User> findAllUsers();
    User findUserById(Integer id);
    User findUserByUsername(String username);
    List<User> findUsersByDepartment(Integer departmentId);
    List<User> findUsersByRole(String role);
    int addUser(User user);
    int updateUser(User user);
    int deleteUserById(Integer id);
    int changeUserStatus(Integer id, String status);
    List<User> searchUsers(String keyword);
    User login(LoginRequest loginRequest);
}