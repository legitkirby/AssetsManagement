package com.isoftstone.assetsmanagement.controller;

import com.isoftstone.assetsmanagement.entity.AssetCategory;
import com.isoftstone.assetsmanagement.service.AssetCategoryService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/api/categories")
public class AssetCategoryController {

    @Resource
    private AssetCategoryService assetCategoryService;

    @GetMapping("/all")
    public List<AssetCategory> findAllCategories() {
        return assetCategoryService.findAllCategories();
    }

    @GetMapping("/{id}")
    public AssetCategory findCategoryById(@PathVariable Integer id) {
        return assetCategoryService.findCategoryById(id);
    }

    @GetMapping("/code/{code}")
    public AssetCategory findCategoryByCode(@PathVariable String code) {
        return assetCategoryService.findCategoryByCode(code);
    }

    @GetMapping("/active")
    public List<AssetCategory> findActiveCategories() {
        return assetCategoryService.findActiveCategories();
    }

    @PostMapping("/add")
    public int addCategory(@RequestBody AssetCategory category) {
        return assetCategoryService.addCategory(category);
    }

    @PutMapping("/update")
    public int updateCategory(@RequestBody AssetCategory category) {
        return assetCategoryService.updateCategory(category);
    }

    @DeleteMapping("/delete/{id}")
    public int deleteCategoryById(@PathVariable Integer id) {
        return assetCategoryService.deleteCategoryById(id);
    }

    @GetMapping("/depreciation/{method}")
    public List<AssetCategory> findCategoriesByDepreciationMethod(@PathVariable String method) {
        return assetCategoryService.findCategoriesByDepreciationMethod(method);
    }
}