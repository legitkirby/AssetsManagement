package com.isoftstone.assetsmanagement.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.isoftstone.assetsmanagement.entity.AssetCategory;
import com.isoftstone.assetsmanagement.mapper.AssetCategoryMapper;
import com.isoftstone.assetsmanagement.service.AssetCategoryService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class AssetCategoryServiceImpl implements AssetCategoryService {

    @Resource
    private AssetCategoryMapper assetCategoryMapper;

    @Override
    public List<AssetCategory> findAllCategories() {
        return assetCategoryMapper.selectList(null);
    }

    @Override
    public AssetCategory findCategoryById(Integer id) {
        return assetCategoryMapper.selectById(id);
    }

    @Override
    public AssetCategory findCategoryByCode(String categoryCode) {
        return assetCategoryMapper.findByCode(categoryCode);
    }

    @Override
    public List<AssetCategory> findActiveCategories() {
        return assetCategoryMapper.findAllActiveCategories();
    }

    @Override
    public int addCategory(AssetCategory category) {
        return assetCategoryMapper.insert(category);
    }

    @Override
    public int updateCategory(AssetCategory category) {
        return assetCategoryMapper.updateById(category);
    }

    @Override
    public int deleteCategoryById(Integer id) {
        return assetCategoryMapper.deleteById(id);
    }

    @Override
    public List<AssetCategory> findCategoriesByDepreciationMethod(String method) {
        return assetCategoryMapper.findByDepreciationMethod(method);
    }
}