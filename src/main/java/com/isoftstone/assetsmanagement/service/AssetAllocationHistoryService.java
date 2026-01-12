package com.isoftstone.assetsmanagement.service;

import com.isoftstone.assetsmanagement.entity.AssetAllocationHistory;

import java.util.List;

public interface AssetAllocationHistoryService {
    List<AssetAllocationHistory> findAllAllocations();
    AssetAllocationHistory findAllocationById(Integer id);
    List<AssetAllocationHistory> findHistoryByAssetId(Integer assetId);
    List<AssetAllocationHistory> findHistoryByEmployeeId(Integer employeeId);
    List<AssetAllocationHistory> findHistoryByDepartmentId(Integer departmentId);
    List<AssetAllocationHistory> findRecentAllocations(Integer limit);
    int addAllocation(AssetAllocationHistory allocation);
    int deleteAllocationById(Integer id);
}