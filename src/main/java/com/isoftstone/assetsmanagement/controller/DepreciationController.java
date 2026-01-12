package com.isoftstone.assetsmanagement.controller;

import com.isoftstone.assetsmanagement.entity.DepreciationRecord;
import com.isoftstone.assetsmanagement.service.DepreciationRecordService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/api/depreciation")
public class DepreciationController {

    @Resource
    private DepreciationRecordService depreciationService;

    @GetMapping("/all")
    public List<DepreciationRecord> findAllDepreciationRecords() {
        return depreciationService.findAllDepreciationRecords();
    }

    @GetMapping("/{id}")
    public DepreciationRecord findDepreciationRecordById(@PathVariable Integer id) {
        return depreciationService.findDepreciationRecordById(id);
    }

    @GetMapping("/asset/{assetId}")
    public List<DepreciationRecord> findRecordsByAssetId(@PathVariable Integer assetId) {
        return depreciationService.findRecordsByAssetId(assetId);
    }

    @GetMapping("/year/{year}")
    public List<DepreciationRecord> findRecordsByYear(@PathVariable Integer year) {
        return depreciationService.findRecordsByYear(year);
    }

    @GetMapping("/asset/{assetId}/year/{year}")
    public List<DepreciationRecord> findRecordsByAssetAndYear(@PathVariable Integer assetId, @PathVariable Integer year) {
        return depreciationService.findRecordsByAssetAndYear(assetId, year);
    }

    @PostMapping("/add")
    public int addDepreciationRecord(@RequestBody DepreciationRecord record) {
        return depreciationService.addDepreciationRecord(record);
    }

    @PutMapping("/update")
    public int updateDepreciationRecord(@RequestBody DepreciationRecord record) {
        return depreciationService.updateDepreciationRecord(record);
    }

    @DeleteMapping("/delete/{id}")
    public int deleteDepreciationRecordById(@PathVariable Integer id) {
        return depreciationService.deleteDepreciationRecordById(id);
    }

    @PostMapping("/calculate/monthly")
    public int calculateMonthlyDepreciation() {
        return depreciationService.calculateMonthlyDepreciation();
    }

    @GetMapping("/report")
    public List<DepreciationRecord> generateDepreciationReport(@RequestParam(required = false) Integer year,
                                                               @RequestParam(required = false) Integer month) {
        return depreciationService.generateDepreciationReport(year, month);
    }
}