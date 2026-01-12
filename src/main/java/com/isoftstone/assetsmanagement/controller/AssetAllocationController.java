package com.isoftstone.assetsmanagement.controller;

import com.isoftstone.assetsmanagement.entity.AssetAllocationHistory;
import com.isoftstone.assetsmanagement.service.AssetAllocationHistoryService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/api/allocations")
@CrossOrigin
public class AssetAllocationController {

    @Resource
    private AssetAllocationHistoryService allocationService;

    @GetMapping("/all")
    public List<AssetAllocationHistory> findAllAllocations() {
        return allocationService.findAllAllocations();
    }

    @GetMapping("/{id}")
    public AssetAllocationHistory findAllocationById(@PathVariable Integer id) {
        return allocationService.findAllocationById(id);
    }

    @GetMapping("/asset/{assetId}")
    public List<AssetAllocationHistory> findHistoryByAssetId(@PathVariable Integer assetId) {
        return allocationService.findHistoryByAssetId(assetId);
    }

    @GetMapping("/employee/{employeeId}")
    public List<AssetAllocationHistory> findHistoryByEmployeeId(@PathVariable Integer employeeId) {
        return allocationService.findHistoryByEmployeeId(employeeId);
    }

    @GetMapping("/department/{departmentId}")
    public List<AssetAllocationHistory> findHistoryByDepartmentId(@PathVariable Integer departmentId) {
        return allocationService.findHistoryByDepartmentId(departmentId);
    }

    @GetMapping("/recent/{limit}")
    public List<AssetAllocationHistory> findRecentAllocations(@PathVariable Integer limit) {
        return allocationService.findRecentAllocations(limit);
    }

    @PostMapping("/add")
    public int addAllocation(@RequestBody AssetAllocationHistory allocation) {
        return allocationService.addAllocation(allocation);
    }

    @DeleteMapping("/delete/{id}")
    public int deleteAllocationById(@PathVariable Integer id) {
        return allocationService.deleteAllocationById(id);
    }
}