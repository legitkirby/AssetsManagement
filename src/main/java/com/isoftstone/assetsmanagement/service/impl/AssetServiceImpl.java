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
import java.time.LocalDateTime;
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
        QueryWrapper<Asset> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("is_deleted", false);
        return assetMapper.selectList(queryWrapper);
    }

    @Override
    public Asset findAssetById(Integer id) {
        QueryWrapper<Asset> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("id", id).eq("is_deleted", false);
        return assetMapper.selectOne(queryWrapper);
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
        queryWrapper.eq("is_deleted", false)
                .and(wrapper -> wrapper
                        .like("asset_code", keyword)
                        .or().like("asset_name", keyword)
                        .or().like("serial_number", keyword)
                );
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
        //  SOFT DELETE: Mark as deleted instead of actually deleting
        Asset asset = assetMapper.selectById(id);
        if (asset != null) {
            asset.setIsDeleted(true);
            return assetMapper.updateById(asset);
        }
        return 0;
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
            asset.setUpdatedTime(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
            asset.setUpdatedBy("1");

            Integer allocatedByUserId = 1;

            AssetAllocationHistory allocation = new AssetAllocationHistory();
            allocation.setAssetId(assetId);
            allocation.setToDepartmentId(departmentId);
            allocation.setToEmployeeId(employeeId);
            allocation.setAllocationType("TRANSFER");
            allocation.setReason(reason);
            allocation.setAllocatedBy(allocatedByUserId); // ✅ ADD THIS LINE

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
        QueryWrapper<Asset> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("is_deleted", false)
                .eq("status", "IN_USE")
                .and(wrapper -> wrapper
                        .isNull("depreciation_end_date")
                        .or().gt("depreciation_end_date", LocalDate.now().toString())
                );
        return assetMapper.selectList(queryWrapper);
    }

    @Override
    public int calculateDepreciation(Integer assetId) {
        Asset asset = assetMapper.selectById(assetId);
        if (asset != null && asset.getCurrentValue() != null && asset.getPurchaseCost() != null) {
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
    @Override
    public int countAssets() {
        return assetMapper.countAllAssets();
    }
    @Override
    public int countAssetsInUse() {
        return assetMapper.countAssetsInUse();
    }

    @Override
    public int countAssetsInMaintenance() {
        return assetMapper.countAssetsInMaintenance();
    }


    @Override
    public int countAssetsByStatus(String status) {
        return assetMapper.countAssetsByStatus(status);
    }

    @Override
    public Double getTotalAssetValue() {
        return assetMapper.getTotalAssetValue();
    }

}