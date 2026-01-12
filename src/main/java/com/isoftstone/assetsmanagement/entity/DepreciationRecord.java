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
@TableName("depreciation_records")
public class DepreciationRecord {
    @TableId("id")
    private Integer id;
    private Integer assetId;
    private Integer fiscalYear;
    private Integer depreciationMonth;
    private BigDecimal depreciationAmount;
    private BigDecimal accumulatedDepreciation;
    private BigDecimal remainingValue;
    private String calculatedDate;
    private String calculatedBy;
    private String createdTime;
    private String remark;
}