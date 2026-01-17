package com.isoftstone.assetsmanagement.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.isoftstone.assetsmanagement.entity.DepreciationRecord;
import com.isoftstone.assetsmanagement.mapper.DepreciationRecordMapper;
import com.isoftstone.assetsmanagement.service.DepreciationRecordService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.time.LocalDate;
import java.util.List;

@Service
public class DepreciationRecordServiceImpl implements DepreciationRecordService {

    @Resource
    private DepreciationRecordMapper depreciationRecordMapper;

    @Override
    public List<DepreciationRecord> findAllDepreciationRecords() {
        return depreciationRecordMapper.selectList(null);
    }

    @Override
    public DepreciationRecord findDepreciationRecordById(Integer id) {
        return depreciationRecordMapper.selectById(id);
    }

    @Override
    public List<DepreciationRecord> findRecordsByAssetId(Integer assetId) {
        return depreciationRecordMapper.findByAssetId(assetId);
    }

    @Override
    public List<DepreciationRecord> findRecordsByYear(Integer year) {
        return depreciationRecordMapper.findByYear(year);
    }

    @Override
    public List<DepreciationRecord> findRecordsByAssetAndYear(Integer assetId, Integer year) {
        return depreciationRecordMapper.findByAssetAndYear(assetId, year);
    }

    @Override
    public int addDepreciationRecord(DepreciationRecord record) {
        return depreciationRecordMapper.insert(record);
    }

    @Override
    public int updateDepreciationRecord(DepreciationRecord record) {
        return depreciationRecordMapper.updateById(record);
    }

    @Override
    public int deleteDepreciationRecordById(Integer id) {
        return depreciationRecordMapper.deleteById(id);
    }

    @Override
    public int calculateMonthlyDepreciation() {
        return 0;
    }

    @Override
    public List<DepreciationRecord> generateDepreciationReport(Integer year, Integer month) {
        QueryWrapper<DepreciationRecord> queryWrapper = new QueryWrapper<>();
        if (year != null) {
            queryWrapper.eq("fiscal_year", year);
        }
        if (month != null) {
            queryWrapper.eq("depreciation_month", month);
        }
        queryWrapper.orderByAsc("asset_id").orderByAsc("fiscal_year").orderByAsc("depreciation_month");
        return depreciationRecordMapper.selectList(queryWrapper);
    }
}