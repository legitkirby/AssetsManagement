package com.isoftstone.assetsmanagement.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.isoftstone.assetsmanagement.entity.AssetAuditTrail;
import com.isoftstone.assetsmanagement.mapper.AssetAuditTrailMapper;
import com.isoftstone.assetsmanagement.service.AssetAuditTrailService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class AssetAuditTrailServiceImpl implements AssetAuditTrailService {

    @Resource
    private AssetAuditTrailMapper assetAuditTrailMapper;

    @Override
    public List<AssetAuditTrail> findAllAuditTrails() {
        return assetAuditTrailMapper.selectList(null);
    }

    @Override
    public AssetAuditTrail findAuditTrailById(Integer id) {
        return assetAuditTrailMapper.selectById(id);
    }

    @Override
    public List<AssetAuditTrail> findTrailsByAssetId(Integer assetId) {
        return assetAuditTrailMapper.findByAssetId(assetId);
    }

    @Override
    public List<AssetAuditTrail> findTrailsByChangedBy(Integer userId) {
        return assetAuditTrailMapper.findByChangedBy(userId);
    }

    @Override
    public List<AssetAuditTrail> findTrailsByField(Integer assetId, String fieldName) {
        return assetAuditTrailMapper.findByFieldName(assetId, fieldName);
    }

    @Override
    public List<AssetAuditTrail> findRecentTrails(Integer limit) {
        QueryWrapper<AssetAuditTrail> queryWrapper = new QueryWrapper<>();
        queryWrapper.orderByDesc("change_time").last("LIMIT " + limit);
        return assetAuditTrailMapper.selectList(queryWrapper);
    }

    @Override
    public int addAuditTrail(AssetAuditTrail auditTrail) {
        return assetAuditTrailMapper.insert(auditTrail);
    }
}