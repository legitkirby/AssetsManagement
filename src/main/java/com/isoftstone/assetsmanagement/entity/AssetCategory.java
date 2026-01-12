package com.isoftstone.assetsmanagement.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("asset_categories")
public class AssetCategory {
    @TableId("id")
    private Integer id;
    private String categoryCode;
    private String categoryName;
    private String description;
    private String depreciationMethod;
    private Integer depreciationPeriodYears;
    private String status;
    private String createdTime;
    private String updatedTime;
    private String createdBy;
    private String updatedBy;
    private String remark;
}