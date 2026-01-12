package com.isoftstone.assetsmanagement.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.isoftstone.assetsmanagement.entity.AssetAllocationHistory;
import com.isoftstone.assetsmanagement.mapper.AssetAllocationHistoryMapper;
import com.isoftstone.assetsmanagement.service.AssetAllocationHistoryService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class AssetAllocationHistoryServiceImpl implements AssetAllocationHistoryService {

    @Resource
    private AssetAllocationHistoryMapper allocationHistoryMapper;

    @Override
    public List<AssetAllocationHistory> findAllAllocations() {
        return allocationHistoryMapper.selectList(null);
    }

    @Override
    public AssetAllocationHistory findAllocationById(Integer id) {
        return allocationHistoryMapper.selectById(id);
    }

    @Override
    public List<AssetAllocationHistory> findHistoryByAssetId(Integer assetId) {
        return allocationHistoryMapper.findByAssetId(assetId);
    }

    @Override
    public List<AssetAllocationHistory> findHistoryByEmployeeId(Integer employeeId) {
        return allocationHistoryMapper.findByEmployeeId(employeeId);
    }

    @Override
    public List<AssetAllocationHistory> findHistoryByDepartmentId(Integer departmentId) {
        return allocationHistoryMapper.findByDepartmentId(departmentId);
    }

    @Override
    public List<AssetAllocationHistory> findRecentAllocations(Integer limit) {
        QueryWrapper<AssetAllocationHistory> queryWrapper = new QueryWrapper<>();
        queryWrapper.orderByDesc("allocation_date").last("LIMIT " + limit);
        return allocationHistoryMapper.selectList(queryWrapper);
    }

    @Override
    public int addAllocation(AssetAllocationHistory allocation) {
        return allocationHistoryMapper.insert(allocation);
    }

    @Override
    public int deleteAllocationById(Integer id) {
        return allocationHistoryMapper.deleteById(id);
    }
}