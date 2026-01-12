package com.isoftstone.assetsmanagement.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("users")
public class User {
    @TableId("id")
    private Integer id;
    private String username;
    private String password;
    private String email;
    private String fullName;
    private Integer departmentId;
    private String role;
    private String status;
    private String createdTime;
    private String updatedTime;
    private String createdBy;
    private String updatedBy;
    private String remark;
}