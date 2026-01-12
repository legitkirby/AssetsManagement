package com.isoftstone.assetsmanagement.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("departments")
public class Department {
    @TableId("id")
    private Integer id;
    private String departmentCode;
    private String departmentName;
    private Integer managerId;
    private Integer parentDepartmentId;
    private String status;
    private String createdTime;
    private String updatedTime;
    private String createdBy;
    private String updatedBy;
    private String remark;
}