package com.isoftstone.assetsmanagement.service;

import com.isoftstone.assetsmanagement.entity.SystemLog;

import java.util.List;

public interface SystemLogService {
    List<SystemLog> findAllLogs();
    SystemLog findLogById(Integer id);
    List<SystemLog> findLogsByUserId(Integer userId);
    List<SystemLog> findLogsByActionType(String actionType);
    List<SystemLog> findLogsByTableName(String tableName);
    List<SystemLog> findRecentLogs(Integer limit);
    int addLog(SystemLog log);
    int deleteOldLogs(Integer days);
    void logAction(Integer userId, String actionType, String tableName, Integer recordId, String description, String ipAddress);
}