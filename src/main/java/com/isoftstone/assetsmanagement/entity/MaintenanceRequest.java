package com.isoftstone.assetsmanagement.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("maintenance_requests")
public class MaintenanceRequest {
    @TableId("id")
    private Integer id;
    private String requestCode;
    private Integer assetId;
    private Integer requestedBy;
    private String requestDate;
    private String issueDescription;
    private String priority;
    private String status;
    private Integer approvedBy;
    private String approvalDate;
    private String maintenanceScheduledDate;
    private String maintenanceCompletedDate;
    private BigDecimal maintenanceCost;
    private String createdTime;
    private String updatedTime;
    private String createdBy;
    private String updatedBy;
    private String remark;
}