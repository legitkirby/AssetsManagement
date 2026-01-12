package com.isoftstone.assetsmanagement.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.isoftstone.assetsmanagement.entity.MaintenanceRequest;
import com.isoftstone.assetsmanagement.mapper.MaintenanceRequestMapper;
import com.isoftstone.assetsmanagement.service.MaintenanceRequestService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
public class MaintenanceRequestServiceImpl implements MaintenanceRequestService {

    @Resource
    private MaintenanceRequestMapper maintenanceRequestMapper;

    @Override
    public List<MaintenanceRequest> findAllRequests() {
        return maintenanceRequestMapper.selectList(null);
    }

    @Override
    public MaintenanceRequest findRequestById(Integer id) {
        return maintenanceRequestMapper.selectById(id);
    }

    @Override
    public MaintenanceRequest findRequestByCode(String requestCode) {
        return maintenanceRequestMapper.findByCode(requestCode);
    }

    @Override
    public List<MaintenanceRequest> findRequestsByAssetId(Integer assetId) {
        return maintenanceRequestMapper.findByAssetId(assetId);
    }

    @Override
    public List<MaintenanceRequest> findRequestsByRequester(Integer userId) {
        return maintenanceRequestMapper.findByRequester(userId);
    }

    @Override
    public List<MaintenanceRequest> findRequestsByStatus(String status) {
        return maintenanceRequestMapper.findByStatus(status);
    }

    @Override
    public List<MaintenanceRequest> findRequestsByApprover(Integer approverId) {
        return maintenanceRequestMapper.findByApprover(approverId);
    }

    @Override
    public List<MaintenanceRequest> findPendingRequests() {
        return maintenanceRequestMapper.findByStatus("PENDING");
    }

    @Override
    public int addRequest(MaintenanceRequest request) {
        return maintenanceRequestMapper.insert(request);
    }

    @Override
    public int updateRequest(MaintenanceRequest request) {
        return maintenanceRequestMapper.updateById(request);
    }

    @Override
    public int deleteRequestById(Integer id) {
        return maintenanceRequestMapper.deleteById(id);
    }

    @Override
    public int approveRequest(Integer requestId, Integer approverId) {
        MaintenanceRequest request = maintenanceRequestMapper.selectById(requestId);
        if (request != null) {
            request.setStatus("APPROVED");
            request.setApprovedBy(approverId);
            request.setApprovalDate(LocalDateTime.now().toString());
            return maintenanceRequestMapper.updateById(request);
        }
        return 0;
    }

    @Override
    public int completeRequest(Integer requestId, Double cost) {
        MaintenanceRequest request = maintenanceRequestMapper.selectById(requestId);
        if (request != null) {
            request.setStatus("COMPLETED");
            request.setMaintenanceCost(java.math.BigDecimal.valueOf(cost));
            request.setMaintenanceCompletedDate(LocalDateTime.now().toString());
            return maintenanceRequestMapper.updateById(request);
        }
        return 0;
    }

    @Override
    public int cancelRequest(Integer requestId) {
        MaintenanceRequest request = maintenanceRequestMapper.selectById(requestId);
        if (request != null) {
            request.setStatus("CANCELLED");
            return maintenanceRequestMapper.updateById(request);
        }
        return 0;
    }
}