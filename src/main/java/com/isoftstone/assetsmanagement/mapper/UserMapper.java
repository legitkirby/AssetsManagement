package com.isoftstone.assetsmanagement.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.isoftstone.assetsmanagement.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface UserMapper extends BaseMapper<User> {
    @Select("SELECT * FROM users WHERE username = #{username}")
    User findByUsername(String username);

    @Select("SELECT * FROM users WHERE department_id = #{departmentId} AND status = 'ACTIVE'")
    List<User> findUsersByDepartment(Integer departmentId);

    @Select("SELECT * FROM users WHERE role = #{role} AND status = 'ACTIVE'")
    List<User> findUsersByRole(String role);
}