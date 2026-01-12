package com.isoftstone.assetsmanagement.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.isoftstone.assetsmanagement.entity.AssetCategory;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface AssetCategoryMapper extends BaseMapper<AssetCategory> {
    @Select("SELECT * FROM asset_categories WHERE category_code = #{categoryCode}")
    AssetCategory findByCode(String categoryCode);

    @Select("SELECT * FROM asset_categories WHERE status = 'ACTIVE'")
    List<AssetCategory> findAllActiveCategories();

    @Select("SELECT * FROM asset_categories WHERE depreciation_method = #{method}")
    List<AssetCategory> findByDepreciationMethod(String method);
}