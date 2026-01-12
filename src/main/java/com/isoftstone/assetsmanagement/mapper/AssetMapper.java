package com.isoftstone.assetsmanagement.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.isoftstone.assetsmanagement.entity.Asset;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface AssetMapper extends BaseMapper<Asset> {
    @Select("SELECT * FROM assets WHERE asset_code = #{assetCode}")
    Asset findByCode(String assetCode);

    @Select("SELECT * FROM assets WHERE department_id = #{departmentId}")
    List<Asset> findAssetsByDepartment(Integer departmentId);

    @Select("SELECT * FROM assets WHERE assigned_to = #{userId}")
    List<Asset> findAssetsByUser(Integer userId);

    @Select("SELECT * FROM assets WHERE status = #{status}")
    List<Asset> findAssetsByStatus(String status);

    @Select("SELECT * FROM assets WHERE category_id = #{categoryId}")
    List<Asset> findAssetsByCategory(Integer categoryId);

    @Select("SELECT * FROM assets WHERE department_id = #{departmentId} AND status = 'IN_USE'")
    List<Asset> findInUseAssetsByDepartment(Integer departmentId);
}