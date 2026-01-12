package com.isoftstone.assetsmanagement.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.isoftstone.assetsmanagement.entity.SystemLog;
import com.isoftstone.assetsmanagement.mapper.SystemLogMapper;
import com.isoftstone.assetsmanagement.service.SystemLogService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class SystemLogServiceImpl implements SystemLogService {

    @Resource
    private SystemLogMapper systemLogMapper;

    @Override
    public List<SystemLog> findAllLogs() {
        return systemLogMapper.selectList(null);
    }

    @Override
    public SystemLog findLogById(Integer id) {
        return systemLogMapper.selectById(id);
    }

    @Override
    public List<SystemLog> findLogsByUserId(Integer userId) {
        return systemLogMapper.findByUserId(userId);
    }

    @Override
    public List<SystemLog> findLogsByActionType(String actionType) {
        return systemLogMapper.findByActionType(actionType);
    }

    @Override
    public List<SystemLog> findLogsByTableName(String tableName) {
        return systemLogMapper.findByTableName(tableName);
    }

    @Override
    public List<SystemLog> findRecentLogs(Integer limit) {
        QueryWrapper<SystemLog> queryWrapper = new QueryWrapper<>();
        queryWrapper.orderByDesc("log_time").last("LIMIT " + limit);
        return systemLogMapper.selectList(queryWrapper);
    }

    @Override
    public int addLog(SystemLog log) {
        return systemLogMapper.insert(log);
    }

    @Override
    public int deleteOldLogs(Integer days) {
        QueryWrapper<SystemLog> queryWrapper = new QueryWrapper<>();
        LocalDateTime cutoffDate = LocalDateTime.now().minusDays(days);
        queryWrapper.lt("log_time", cutoffDate.toString());
        return systemLogMapper.delete(queryWrapper);
    }

    @Override
    public void logAction(Integer userId, String actionType, String tableName, Integer recordId, String description, String ipAddress) {
        SystemLog log = new SystemLog();
        log.setUserId(userId);
        log.setActionType(actionType);
        log.setTableName(tableName);
        log.setRecordId(recordId);
        log.setActionDescription(description);
        log.setIpAddress(ipAddress);
        systemLogMapper.insert(log);
    }
}