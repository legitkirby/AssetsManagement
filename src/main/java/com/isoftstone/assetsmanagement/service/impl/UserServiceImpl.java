package com.isoftstone.assetsmanagement.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.isoftstone.assetsmanagement.entity.User;
import com.isoftstone.assetsmanagement.mapper.UserMapper;
import com.isoftstone.assetsmanagement.service.UserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Resource
    private UserMapper userMapper;

    @Override
    public List<User> findAllUsers() {
        return userMapper.selectList(null);
    }

    @Override
    public User findUserById(Integer id) {
        return userMapper.selectById(id);
    }

    @Override
    public User findUserByUsername(String username) {
        return userMapper.findByUsername(username);
    }

    @Override
    public List<User> findUsersByDepartment(Integer departmentId) {
        return userMapper.findUsersByDepartment(departmentId);
    }

    @Override
    public List<User> findUsersByRole(String role) {
        return userMapper.findUsersByRole(role);
    }

    @Override
    public int addUser(User user) {
        return userMapper.insert(user);
    }

    @Override
    public int updateUser(User user) {
        return userMapper.updateById(user);
    }

    @Override
    public int deleteUserById(Integer id) {
        return userMapper.deleteById(id);
    }

    @Override
    public int changeUserStatus(Integer id, String status) {
        User user = userMapper.selectById(id);
        if (user != null) {
            user.setStatus(status);
            return userMapper.updateById(user);
        }
        return 0;
    }

    @Override
    public List<User> searchUsers(String keyword) {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.like("username", keyword)
                .or().like("full_name", keyword)
                .or().like("email", keyword);
        return userMapper.selectList(queryWrapper);
    }
}