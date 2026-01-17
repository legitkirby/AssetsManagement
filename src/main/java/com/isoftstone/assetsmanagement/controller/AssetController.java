package com.isoftstone.assetsmanagement.controller;

import com.isoftstone.assetsmanagement.entity.Asset;
import com.isoftstone.assetsmanagement.service.AssetService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/api/assets")
public class AssetController {

    @Resource
    private AssetService assetService;

    @GetMapping("/all")
    public List<Asset> findAllAssets() {
        return assetService.findAllAssets();
    }

    @GetMapping("/{id}")
    public Asset findAssetById(@PathVariable Integer id) {
        return assetService.findAssetById(id);
    }

    @GetMapping("/code/{code}")
    public Asset findAssetByCode(@PathVariable String code) {
        return assetService.findAssetByCode(code);
    }

    @GetMapping("/department/{departmentId}")
    public List<Asset> findAssetsByDepartment(@PathVariable Integer departmentId) {
        return assetService.findAssetsByDepartment(departmentId);
    }

    @GetMapping("/user/{userId}")
    public List<Asset> findAssetsByUser(@PathVariable Integer userId) {
        return assetService.findAssetsByUser(userId);
    }

    @GetMapping("/status/{status}")
    public List<Asset> findAssetsByStatus(@PathVariable String status) {
        return assetService.findAssetsByStatus(status);
    }

    @GetMapping("/category/{categoryId}")
    public List<Asset> findAssetsByCategory(@PathVariable Integer categoryId) {
        return assetService.findAssetsByCategory(categoryId);
    }

    @GetMapping("/search")
    public List<Asset> searchAssets(@RequestParam String keyword) {
        return assetService.searchAssets(keyword);
    }


    @PostMapping("/add")
    public int addAsset(@RequestBody Asset asset) {
        return assetService.addAsset(asset);
    }

    @PutMapping("/update")
    public int updateAsset(@RequestBody Asset asset) {
        return assetService.updateAsset(asset);
    }

    @DeleteMapping("/delete/{id}")
    public int deleteAssetById(@PathVariable Integer id) {
        return assetService.deleteAssetById(id);
    }

    @PutMapping("/status/{id}")
    public int changeAssetStatus(@PathVariable Integer id, @RequestParam String status) {
        return assetService.changeAssetStatus(id, status);
    }

    @PutMapping("/restore/{id}")
    public int restoreAsset(@PathVariable Integer id) {
        Asset asset = assetService.findAssetById(id);
        if (asset != null) {
            asset.setIsDeleted(false);
            asset.setStatus("AVAILABLE"); // Or whatever default status
            return assetService.updateAsset(asset);
        }
        return 0;
    }

    @PostMapping("/allocate")
    public int allocateAsset(@RequestParam Integer assetId,
                             @RequestParam Integer departmentId,
                             @RequestParam Integer employeeId,
                             @RequestParam String reason) {
        return assetService.allocateAsset(assetId, departmentId, employeeId, reason);
    }

    @PostMapping("/dispose")
    public int disposeAsset(@RequestParam Integer assetId,
                            @RequestParam String reason,
                            @RequestParam String method) {
        return assetService.disposeAsset(assetId, reason, method);
    }

    @GetMapping("/depreciation/calculate/{assetId}")
    public int calculateDepreciation(@PathVariable Integer assetId) {
        return assetService.calculateDepreciation(assetId);
    }
    // ===== Dashboard KPI APIs =====

    @GetMapping("/count")
    public int getTotalAssetCount() {
        return assetService.countAssets();
    }

    @GetMapping("/in-use")
    public int getAssetsInUse() {
        return assetService.countAssetsByStatus("IN_USE");
    }

    @GetMapping("/maintenance")
    public int getAssetsUnderMaintenance() {
        return assetService.countAssetsByStatus("MAINTENANCE");
    }

    @GetMapping("/value")
    public double getTotalAssetValue() {
        return assetService.getTotalAssetValue();
    }

}