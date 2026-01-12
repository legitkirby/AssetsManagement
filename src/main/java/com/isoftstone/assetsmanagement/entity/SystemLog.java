package com.isoftstone.assetsmanagement.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("system_logs")
public class SystemLog {
    @TableId("id")
    private Integer id;
    private String logTime;
    private Integer userId;
    private String actionType;
    private String tableName;
    private Integer recordId;
    private String actionDescription;
    private String ipAddress;
}