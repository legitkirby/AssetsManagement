package com.isoftstone.assetsmanagement.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.isoftstone.assetsmanagement.entity.Asset;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface AssetMapper extends BaseMapper<Asset> {
    @Select("SELECT * FROM assets WHERE is_deleted = 0 AND asset_code = #{assetCode}")
    Asset findByCode(String assetCode);

    @Select("SELECT * FROM assets WHERE is_deleted = 0 AND department_id = #{departmentId}")
    List<Asset> findAssetsByDepartment(Integer departmentId);

    @Select("SELECT * FROM assets WHERE is_deleted = 0 AND assigned_to = #{userId}")
    List<Asset> findAssetsByUser(Integer userId);

    @Select("SELECT * FROM assets WHERE is_deleted = 0 AND status = #{status}")
    List<Asset> findAssetsByStatus(String status);

    @Select("SELECT * FROM assets WHERE is_deleted = 0 AND category_id = #{categoryId}")
    List<Asset> findAssetsByCategory(Integer categoryId);

    @Select("SELECT * FROM assets WHERE is_deleted = 0 AND department_id = #{departmentId} AND status = 'IN_USE'")
    List<Asset> findInUseAssetsByDepartment(Integer departmentId);

    @Select("SELECT COUNT(*) FROM assets WHERE is_deleted = 0")
    int countAllAssets();

    @Select("SELECT COUNT(*) FROM assets WHERE is_deleted = 0 AND status = 'IN_USE'")
    int countAssetsInUse();

    @Select("SELECT COUNT(*) FROM assets WHERE is_deleted = 0 AND status = 'UNDER_MAINTENANCE'")
    int countAssetsInMaintenance();

    @Select("SELECT IFNULL(SUM(current_value), 0) FROM assets WHERE is_deleted = 0")
    Double getTotalAssetValue();

    @Select("SELECT COUNT(*) FROM assets WHERE is_deleted = 0 AND status = #{status}")
    int countAssetsByStatus(String status);
}