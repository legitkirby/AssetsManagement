package com.isoftstone.assetsmanagement.controller;

import com.isoftstone.assetsmanagement.entity.SystemLog;
import com.isoftstone.assetsmanagement.service.SystemLogService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/api/logs")
public class SystemLogController {

    @Resource
    private SystemLogService systemLogService;

    @GetMapping("/all")
    public List<SystemLog> findAllLogs() {
        return systemLogService.findAllLogs();
    }

    @GetMapping("/{id}")
    public SystemLog findLogById(@PathVariable Integer id) {
        return systemLogService.findLogById(id);
    }

    @GetMapping("/user/{userId}")
    public List<SystemLog> findLogsByUserId(@PathVariable Integer userId) {
        return systemLogService.findLogsByUserId(userId);
    }

    @GetMapping("/action/{actionType}")
    public List<SystemLog> findLogsByActionType(@PathVariable String actionType) {
        return systemLogService.findLogsByActionType(actionType);
    }

    @GetMapping("/table/{tableName}")
    public List<SystemLog> findLogsByTableName(@PathVariable String tableName) {
        return systemLogService.findLogsByTableName(tableName);
    }

    @GetMapping("/recent/{limit}")
    public List<SystemLog> findRecentLogs(@PathVariable Integer limit) {
        return systemLogService.findRecentLogs(limit);
    }

    @PostMapping("/add")
    public int addLog(@RequestBody SystemLog log) {
        return systemLogService.addLog(log);
    }

    @DeleteMapping("/clean/{days}")
    public int deleteOldLogs(@PathVariable Integer days) {
        return systemLogService.deleteOldLogs(days);
    }
}