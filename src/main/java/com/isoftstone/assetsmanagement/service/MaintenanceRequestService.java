package com.isoftstone.assetsmanagement.service;

import com.isoftstone.assetsmanagement.entity.MaintenanceRequest;

import java.util.List;

public interface MaintenanceRequestService {
    List<MaintenanceRequest> findAllRequests();
    MaintenanceRequest findRequestById(Integer id);
    MaintenanceRequest findRequestByCode(String requestCode);
    List<MaintenanceRequest> findRequestsByAssetId(Integer assetId);
    List<MaintenanceRequest> findRequestsByRequester(Integer userId);
    List<MaintenanceRequest> findRequestsByStatus(String status);
    List<MaintenanceRequest> findRequestsByApprover(Integer approverId);
    List<MaintenanceRequest> findPendingRequests();
    int addRequest(MaintenanceRequest request);
    int updateRequest(MaintenanceRequest request);
    int deleteRequestById(Integer id);
    int approveRequest(Integer requestId, Integer approverId);
    int completeRequest(Integer requestId, Double cost);
    int cancelRequest(Integer requestId);
}