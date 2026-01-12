package com.isoftstone.assetsmanagement.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.isoftstone.assetsmanagement.entity.MaintenanceRequest;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface MaintenanceRequestMapper extends BaseMapper<MaintenanceRequest> {
    @Select("SELECT * FROM maintenance_requests WHERE request_code = #{requestCode}")
    MaintenanceRequest findByCode(String requestCode);

    @Select("SELECT * FROM maintenance_requests WHERE asset_id = #{assetId}")
    List<MaintenanceRequest> findByAssetId(Integer assetId);

    @Select("SELECT * FROM maintenance_requests WHERE requested_by = #{userId}")
    List<MaintenanceRequest> findByRequester(Integer userId);

    @Select("SELECT * FROM maintenance_requests WHERE status = #{status}")
    List<MaintenanceRequest> findByStatus(String status);

    @Select("SELECT * FROM maintenance_requests WHERE approved_by = #{approverId}")
    List<MaintenanceRequest> findByApprover(Integer approverId);
}