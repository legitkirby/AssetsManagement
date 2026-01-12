package com.isoftstone.assetsmanagement.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.isoftstone.assetsmanagement.entity.Asset;
import com.isoftstone.assetsmanagement.entity.AssetAllocationHistory;
import com.isoftstone.assetsmanagement.mapper.AssetMapper;
import com.isoftstone.assetsmanagement.mapper.AssetAllocationHistoryMapper;
import com.isoftstone.assetsmanagement.service.AssetService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
public class AssetServiceImpl implements AssetService {

    @Resource
    private AssetMapper assetMapper;

    @Resource
    private AssetAllocationHistoryMapper allocationHistoryMapper;

    @Override
    public List<Asset> findAllAssets() {
        return assetMapper.selectList(null);
    }

    @Override
    public Asset findAssetById(Integer id) {
        return assetMapper.selectById(id);
    }

    @Override
    public Asset findAssetByCode(String assetCode) {
        return assetMapper.findByCode(assetCode);
    }

    @Override
    public List<Asset> findAssetsByDepartment(Integer departmentId) {
        return assetMapper.findAssetsByDepartment(departmentId);
    }

    @Override
    public List<Asset> findAssetsByUser(Integer userId) {
        return assetMapper.findAssetsByUser(userId);
    }

    @Override
    public List<Asset> findAssetsByStatus(String status) {
        return assetMapper.findAssetsByStatus(status);
    }

    @Override
    public List<Asset> findAssetsByCategory(Integer categoryId) {
        return assetMapper.findAssetsByCategory(categoryId);
    }

    @Override
    public List<Asset> searchAssets(String keyword) {
        QueryWrapper<Asset> queryWrapper = new QueryWrapper<>();
        queryWrapper.like("asset_code", keyword)
                .or().like("asset_name", keyword)
                .or().like("serial_number", keyword);
        return assetMapper.selectList(queryWrapper);
    }

    @Override
    public int addAsset(Asset asset) {
        // Set initial status if not provided
        if (asset.getStatus() == null) {
            asset.setStatus("AVAILABLE");
        }
        return assetMapper.insert(asset);
    }

    @Override
    public int updateAsset(Asset asset) {
        return assetMapper.updateById(asset);
    }

    @Override
    public int deleteAssetById(Integer id) {
        return assetMapper.deleteById(id);
    }

    @Override
    public int changeAssetStatus(Integer id, String status) {
        Asset asset = assetMapper.selectById(id);
        if (asset != null) {
            asset.setStatus(status);
            return assetMapper.updateById(asset);
        }
        return 0;
    }

    @Override
    public int allocateAsset(Integer assetId, Integer departmentId, Integer employeeId, String reason) {
        Asset asset = assetMapper.selectById(assetId);
        if (asset != null) {
            asset.setDepartmentId(departmentId);
            asset.setAssignedTo(employeeId);
            asset.setStatus("IN_USE");

            // Create allocation history
            AssetAllocationHistory allocation = new AssetAllocationHistory();
            allocation.setAssetId(assetId);
            allocation.setToDepartmentId(departmentId);
            allocation.setToEmployeeId(employeeId);
            allocation.setAllocationType("TRANSFER");
            allocation.setReason(reason);

            allocationHistoryMapper.insert(allocation);
            return assetMapper.updateById(asset);
        }
        return 0;
    }

    @Override
    public int disposeAsset(Integer assetId, String reason, String method) {
        Asset asset = assetMapper.selectById(assetId);
        if (asset != null) {
            asset.setStatus("DISPOSED");
            asset.setDisposalReason(reason);
            asset.setDisposalMethod(method);
            asset.setDisposalDate(LocalDate.now().toString());
            return assetMapper.updateById(asset);
        }
        return 0;
    }

    @Override
    public List<Asset> findAssetsForDepreciation() {
        // Find assets that are in use and not fully depreciated
        QueryWrapper<Asset> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("status", "IN_USE")
                .isNull("depreciation_end_date")
                .or().gt("depreciation_end_date", LocalDate.now().toString());
        return assetMapper.selectList(queryWrapper);
    }

    @Override
    public int calculateDepreciation(Integer assetId) {
        // Simplified depreciation calculation
        // In real implementation, you would calculate based on depreciation method
        Asset asset = assetMapper.selectById(assetId);
        if (asset != null && asset.getCurrentValue() != null && asset.getPurchaseCost() != null) {
            // Simple straight-line depreciation example
            BigDecimal monthlyDepreciation = asset.getPurchaseCost()
                    .divide(BigDecimal.valueOf(60), 2, BigDecimal.ROUND_HALF_UP); // 5 years = 60 months

            BigDecimal newValue = asset.getCurrentValue().subtract(monthlyDepreciation);
            if (newValue.compareTo(BigDecimal.ZERO) < 0) {
                newValue = BigDecimal.ZERO;
            }

            asset.setCurrentValue(newValue);
            return assetMapper.updateById(asset);
        }
        return 0;
    }
}