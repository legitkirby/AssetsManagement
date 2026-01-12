package com.isoftstone.assetsmanagement.service;

import com.isoftstone.assetsmanagement.entity.AssetAuditTrail;

import java.util.List;

public interface AssetAuditTrailService {
    List<AssetAuditTrail> findAllAuditTrails();
    AssetAuditTrail findAuditTrailById(Integer id);
    List<AssetAuditTrail> findTrailsByAssetId(Integer assetId);
    List<AssetAuditTrail> findTrailsByChangedBy(Integer userId);
    List<AssetAuditTrail> findTrailsByField(Integer assetId, String fieldName);
    List<AssetAuditTrail> findRecentTrails(Integer limit);
    int addAuditTrail(AssetAuditTrail auditTrail);
}