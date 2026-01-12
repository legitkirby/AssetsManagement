package com.isoftstone.assetsmanagement.service;

import com.isoftstone.assetsmanagement.entity.AssetCategory;

import java.util.List;

public interface AssetCategoryService {
    List<AssetCategory> findAllCategories();
    AssetCategory findCategoryById(Integer id);
    AssetCategory findCategoryByCode(String categoryCode);
    List<AssetCategory> findActiveCategories();
    int addCategory(AssetCategory category);
    int updateCategory(AssetCategory category);
    int deleteCategoryById(Integer id);
    List<AssetCategory> findCategoriesByDepreciationMethod(String method);
}