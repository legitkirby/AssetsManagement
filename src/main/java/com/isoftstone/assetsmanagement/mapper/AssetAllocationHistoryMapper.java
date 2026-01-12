package com.isoftstone.assetsmanagement.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.isoftstone.assetsmanagement.entity.AssetAllocationHistory;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface AssetAllocationHistoryMapper extends BaseMapper<AssetAllocationHistory> {
    @Select("SELECT * FROM asset_allocation_history WHERE asset_id = #{assetId} ORDER BY allocation_date DESC")
    List<AssetAllocationHistory> findByAssetId(Integer assetId);

    @Select("SELECT * FROM asset_allocation_history WHERE to_employee_id = #{employeeId}")
    List<AssetAllocationHistory> findByEmployeeId(Integer employeeId);

    @Select("SELECT * FROM asset_allocation_history WHERE to_department_id = #{departmentId}")
    List<AssetAllocationHistory> findByDepartmentId(Integer departmentId);
}