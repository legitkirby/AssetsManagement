package com.isoftstone.assetsmanagement.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.isoftstone.assetsmanagement.entity.SystemLog;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface SystemLogMapper extends BaseMapper<SystemLog> {
    @Select("SELECT * FROM system_logs WHERE user_id = #{userId} ORDER BY log_time DESC")
    List<SystemLog> findByUserId(Integer userId);

    @Select("SELECT * FROM system_logs WHERE action_type = #{actionType}")
    List<SystemLog> findByActionType(String actionType);

    @Select("SELECT * FROM system_logs WHERE table_name = #{tableName}")
    List<SystemLog> findByTableName(String tableName);

    @Select("SELECT * FROM system_logs WHERE record_id = #{recordId} AND table_name = #{tableName}")
    List<SystemLog> findByRecord(Integer recordId, String tableName);
}