package com.isoftstone.assetsmanagement.controller;

import com.isoftstone.assetsmanagement.entity.MaintenanceRequest;
import com.isoftstone.assetsmanagement.service.MaintenanceRequestService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/api/maintenance")
public class MaintenanceController {

    @Resource
    private MaintenanceRequestService maintenanceService;

    @GetMapping("/all")
    public List<MaintenanceRequest> findAllRequests() {
        return maintenanceService.findAllRequests();
    }

    @GetMapping("/{id}")
    public MaintenanceRequest findRequestById(@PathVariable Integer id) {
        return maintenanceService.findRequestById(id);
    }

    @GetMapping("/code/{code}")
    public MaintenanceRequest findRequestByCode(@PathVariable String code) {
        return maintenanceService.findRequestByCode(code);
    }

    @GetMapping("/asset/{assetId}")
    public List<MaintenanceRequest> findRequestsByAssetId(@PathVariable Integer assetId) {
        return maintenanceService.findRequestsByAssetId(assetId);
    }

    @GetMapping("/requester/{userId}")
    public List<MaintenanceRequest> findRequestsByRequester(@PathVariable Integer userId) {
        return maintenanceService.findRequestsByRequester(userId);
    }

    @GetMapping("/status/{status}")
    public List<MaintenanceRequest> findRequestsByStatus(@PathVariable String status) {
        return maintenanceService.findRequestsByStatus(status);
    }

    @GetMapping("/approver/{approverId}")
    public List<MaintenanceRequest> findRequestsByApprover(@PathVariable Integer approverId) {
        return maintenanceService.findRequestsByApprover(approverId);
    }

    @GetMapping("/pending")
    public List<MaintenanceRequest> findPendingRequests() {
        return maintenanceService.findPendingRequests();
    }

    @PostMapping("/add")
    public int addRequest(@RequestBody MaintenanceRequest request) {
        return maintenanceService.addRequest(request);
    }

    @PutMapping("/update")
    public int updateRequest(@RequestBody MaintenanceRequest request) {
        return maintenanceService.updateRequest(request);
    }

    @DeleteMapping("/delete/{id}")
    public int deleteRequestById(@PathVariable Integer id) {
        return maintenanceService.deleteRequestById(id);
    }

    @PostMapping("/approve/{requestId}")
    public int approveRequest(@PathVariable Integer requestId, @RequestParam Integer approverId) {
        return maintenanceService.approveRequest(requestId, approverId);
    }

    @PostMapping("/complete/{requestId}")
    public int completeRequest(@PathVariable Integer requestId, @RequestParam Double cost) {
        return maintenanceService.completeRequest(requestId, cost);
    }

    @PostMapping("/cancel/{requestId}")
    public int cancelRequest(@PathVariable Integer requestId) {
        return maintenanceService.cancelRequest(requestId);
    }
}