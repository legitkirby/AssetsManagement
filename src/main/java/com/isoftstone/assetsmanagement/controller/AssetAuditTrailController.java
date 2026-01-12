package com.isoftstone.assetsmanagement.controller;

import com.isoftstone.assetsmanagement.entity.AssetAuditTrail;
import com.isoftstone.assetsmanagement.service.AssetAuditTrailService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/api/audit-trails")
public class AssetAuditTrailController {

    @Resource
    private AssetAuditTrailService auditTrailService;

    @GetMapping("/all")
    public List<AssetAuditTrail> findAllAuditTrails() {
        return auditTrailService.findAllAuditTrails();
    }

    @GetMapping("/{id}")
    public AssetAuditTrail findAuditTrailById(@PathVariable Integer id) {
        return auditTrailService.findAuditTrailById(id);
    }

    @GetMapping("/asset/{assetId}")
    public List<AssetAuditTrail> findTrailsByAssetId(@PathVariable Integer assetId) {
        return auditTrailService.findTrailsByAssetId(assetId);
    }

    @GetMapping("/user/{userId}")
    public List<AssetAuditTrail> findTrailsByChangedBy(@PathVariable Integer userId) {
        return auditTrailService.findTrailsByChangedBy(userId);
    }

    @GetMapping("/asset/{assetId}/field/{fieldName}")
    public List<AssetAuditTrail> findTrailsByField(@PathVariable Integer assetId,
                                                   @PathVariable String fieldName) {
        return auditTrailService.findTrailsByField(assetId, fieldName);
    }

    @GetMapping("/recent/{limit}")
    public List<AssetAuditTrail> findRecentTrails(@PathVariable Integer limit) {
        return auditTrailService.findRecentTrails(limit);
    }

    @PostMapping("/add")
    public int addAuditTrail(@RequestBody AssetAuditTrail auditTrail) {
        return auditTrailService.addAuditTrail(auditTrail);
    }
}