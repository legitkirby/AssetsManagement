package com.isoftstone.assetsmanagement.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("asset_audit_trail")
public class AssetAuditTrail {
    @TableId("id")
    private Integer id;
    private Integer assetId;
    private String changedField;
    private String oldValue;
    private String newValue;
    private Integer changedBy;
    private String changeTime;
    private String changeReason;
}