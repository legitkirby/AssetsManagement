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
@TableName("assets")
public class Asset {
    @TableId("id")
    private Integer id;
    private String assetCode;
    private String assetName;
    private Integer categoryId;
    private String serialNumber;
    private String purchaseDate;
    private BigDecimal purchaseCost;
    private BigDecimal currentValue;
    private String supplier;
    private String warrantyEndDate;
    private String status;
    private String currentLocation;
    private Integer departmentId;
    private Integer assignedTo;
    private String depreciationStartDate;
    private String depreciationEndDate;
    private String disposalDate;
    private String disposalReason;
    private String disposalMethod;
    private String createdTime;
    private String updatedTime;
    private String createdBy;
    private String updatedBy;
    private String remark;
    private Boolean isDeleted = false;
}