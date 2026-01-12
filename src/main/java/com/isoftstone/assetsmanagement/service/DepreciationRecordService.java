package com.isoftstone.assetsmanagement.service;

import com.isoftstone.assetsmanagement.entity.DepreciationRecord;

import java.util.List;

public interface DepreciationRecordService {
    List<DepreciationRecord> findAllDepreciationRecords();
    DepreciationRecord findDepreciationRecordById(Integer id);
    List<DepreciationRecord> findRecordsByAssetId(Integer assetId);
    List<DepreciationRecord> findRecordsByYear(Integer year);
    List<DepreciationRecord> findRecordsByAssetAndYear(Integer assetId, Integer year);
    int addDepreciationRecord(DepreciationRecord record);
    int updateDepreciationRecord(DepreciationRecord record);
    int deleteDepreciationRecordById(Integer id);
    int calculateMonthlyDepreciation();
    List<DepreciationRecord> generateDepreciationReport(Integer year, Integer month);
}