package com.isoftstone.assetsmanagement.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("asset_allocation_history")
public class AssetAllocationHistory {
    @TableId("id")
    private Integer id;
    private Integer assetId;
    private Integer fromDepartmentId;
    private Integer toDepartmentId;
    private Integer fromEmployeeId;
    private Integer toEmployeeId;
    private String allocationDate;
    private String allocationType;
    private String reason;
    private Integer allocatedBy;
    private String createdTime;
    private String createdBy;
    private String remark;
}