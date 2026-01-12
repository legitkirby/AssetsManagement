package com.isoftstone.assetsmanagement.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.isoftstone.assetsmanagement.entity.AssetAuditTrail;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface AssetAuditTrailMapper extends BaseMapper<AssetAuditTrail> {
    @Select("SELECT * FROM asset_audit_trail WHERE asset_id = #{assetId} ORDER BY change_time DESC")
    List<AssetAuditTrail> findByAssetId(Integer assetId);

    @Select("SELECT * FROM asset_audit_trail WHERE changed_by = #{userId}")
    List<AssetAuditTrail> findByChangedBy(Integer userId);

    @Select("SELECT * FROM asset_audit_trail WHERE changed_field = #{fieldName} AND asset_id = #{assetId}")
    List<AssetAuditTrail> findByFieldName(Integer assetId, String fieldName);
}