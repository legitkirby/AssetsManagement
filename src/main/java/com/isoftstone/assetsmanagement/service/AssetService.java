package com.isoftstone.assetsmanagement.service;

import com.isoftstone.assetsmanagement.entity.Asset;

import java.util.List;

public interface AssetService {
    List<Asset> findAllAssets();
    Asset findAssetById(Integer id);
    Asset findAssetByCode(String assetCode);
    List<Asset> findAssetsByDepartment(Integer departmentId);
    List<Asset> findAssetsByUser(Integer userId);
    List<Asset> findAssetsByStatus(String status);
    List<Asset> findAssetsByCategory(Integer categoryId);
    List<Asset> searchAssets(String keyword);
    int addAsset(Asset asset);
    int updateAsset(Asset asset);
    int deleteAssetById(Integer id);
    int changeAssetStatus(Integer id, String status);
    int allocateAsset(Integer assetId, Integer departmentId, Integer employeeId, String reason);
    int disposeAsset(Integer assetId, String reason, String method);
    List<Asset> findAssetsForDepreciation();
    int calculateDepreciation(Integer assetId);
    int countAssets();
    int countAssetsInUse();
    int countAssetsInMaintenance();
    int countAssetsByStatus(String status);

    Double getTotalAssetValue();

}