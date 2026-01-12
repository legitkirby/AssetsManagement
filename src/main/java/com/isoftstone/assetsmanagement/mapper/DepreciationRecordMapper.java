package com.isoftstone.assetsmanagement.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.isoftstone.assetsmanagement.entity.DepreciationRecord;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface DepreciationRecordMapper extends BaseMapper<DepreciationRecord> {
    @Select("SELECT * FROM depreciation_records WHERE asset_id = #{assetId} ORDER BY fiscal_year DESC, depreciation_month DESC")
    List<DepreciationRecord> findByAssetId(Integer assetId);

    @Select("SELECT * FROM depreciation_records WHERE asset_id = #{assetId} AND fiscal_year = #{year}")
    List<DepreciationRecord> findByAssetAndYear(Integer assetId, Integer year);

    @Select("SELECT * FROM depreciation_records WHERE fiscal_year = #{year}")
    List<DepreciationRecord> findByYear(Integer year);
}