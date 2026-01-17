package com.isoftstone.assetsmanagement.controller;

import com.isoftstone.assetsmanagement.entity.Asset;
import com.isoftstone.assetsmanagement.entity.MaintenanceRequest;
import com.isoftstone.assetsmanagement.service.AssetService;
import com.isoftstone.assetsmanagement.service.MaintenanceRequestService;
import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

@RestController
@RequestMapping("/api/reports")
@CrossOrigin
public class ReportController {

    @Resource
    private AssetService assetService;

    @Resource
    private MaintenanceRequestService maintenanceRequestService;

    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    /**
     * Generate CSV report
     */
    @PostMapping("/generate")
    public void generateReport(
            @RequestParam String type,
            @RequestParam(defaultValue = "CSV") String format,
            @RequestParam(required = false) String startDate,
            @RequestParam(required = false) String endDate,
            HttpServletResponse response) throws IOException {

        response.setContentType("text/csv; charset=UTF-8");
        response.setCharacterEncoding("UTF-8");

        String fileName = String.format("%s-report-%s.csv",
                type.toLowerCase().replace("_", "-"),
                LocalDate.now().format(DATE_FORMATTER));

        response.setHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\"");

        PrintWriter writer = response.getWriter();

        try {
            switch (type.toUpperCase()) {
                case "ASSETS_SUMMARY":
                    generateAssetsSummary(writer);
                    break;

                case "MAINTENANCE_REPORT":
                    generateMaintenanceReport(writer);
                    break;

                case "ASSETS_BY_STATUS":
                    generateAssetsByStatus(writer);
                    break;

                default:
                    writer.println("Report Type,Generated Date");
                    writer.println(type + "," + LocalDate.now());
                    break;
            }
        } finally {
            writer.flush();
            writer.close();
        }
    }

    /**
     * Get preview data
     */
    @PostMapping("/preview")
    public Map<String, Object> previewReport(@RequestBody Map<String, Object> request) {
        String type = (String) request.get("type");

        Map<String, Object> response = new HashMap<>();
        List<Map<String, Object>> records = new ArrayList<>();

        try {
            switch (type.toUpperCase()) {
                case "ASSETS_SUMMARY":
                    records = getAssetsSummaryPreview();
                    break;

                case "MAINTENANCE_REPORT":
                    records = getMaintenanceReportPreview();
                    break;

                case "ASSETS_BY_STATUS":
                    records = getAssetsByStatusPreview();
                    break;

                default:
                    // Return empty preview
                    break;
            }

            response.put("success", true);
            response.put("records", records);
            response.put("total", records.size());
            response.put("message", "Preview loaded successfully");

        } catch (Exception e) {
            response.put("success", false);
            response.put("message", "Error: " + e.getMessage());
            response.put("records", Collections.emptyList());
            response.put("total", 0);
        }

        return response;
    }

    /**
     * Get available report types
     */
    @GetMapping("/types")
    public List<Map<String, String>> getReportTypes() {
        List<Map<String, String>> types = new ArrayList<>();

        String[][] reportTypes = {
                {"ASSETS_SUMMARY", "Assets Summary"},
                {"ASSETS_BY_STATUS", "Assets by Status"},
                {"MAINTENANCE_REPORT", "Maintenance Report"},
                {"ALLOCATION_HISTORY", "Allocation History"},
                {"DEPRECIATION_REPORT", "Depreciation Report"}
        };

        for (String[] type : reportTypes) {
            Map<String, String> typeMap = new HashMap<>();
            typeMap.put("value", type[0]);
            typeMap.put("label", type[1]);
            types.add(typeMap);
        }

        return types;
    }

    // ===== CSV GENERATORS =====

    private void generateAssetsSummary(PrintWriter writer) {
        writer.println("Asset Code,Asset Name,Category ID,Status,Purchase Cost,Current Value,Purchase Date");

        List<Asset> assets = assetService.findAllAssets();
        for (Asset asset : assets) {
            writer.println(String.format("%s,%s,%d,%s,%.2f,%.2f,%s",
                    asset.getAssetCode(),
                    asset.getAssetName(),
                    asset.getCategoryId(),
                    asset.getStatus(),
                    asset.getPurchaseCost(),
                    asset.getCurrentValue(),
                    asset.getPurchaseDate()));
        }
    }

    private void generateMaintenanceReport(PrintWriter writer) {
        writer.println("Request Code,Asset ID,Issue Description,Priority,Status,Scheduled Date,Cost");

        List<MaintenanceRequest> requests = maintenanceRequestService.findAllRequests();
        for (MaintenanceRequest req : requests) {
            writer.println(String.format("%s,%d,%s,%s,%s,%s,%.2f",
                    req.getRequestCode(),
                    req.getAssetId(),
                    req.getIssueDescription(),
                    req.getPriority(),
                    req.getStatus(),
                    req.getMaintenanceScheduledDate(),
                    req.getMaintenanceCost() != null ? req.getMaintenanceCost() : 0.0));
        }
    }

    private void generateAssetsByStatus(PrintWriter writer) {
        writer.println("Status,Asset Count");

        // Group assets by status
        Map<String, Integer> statusCount = new HashMap<>();
        List<Asset> assets = assetService.findAllAssets();

        for (Asset asset : assets) {
            String status = asset.getStatus();
            statusCount.put(status, statusCount.getOrDefault(status, 0) + 1);
        }

        for (Map.Entry<String, Integer> entry : statusCount.entrySet()) {
            writer.println(entry.getKey() + "," + entry.getValue());
        }
    }

    // ===== PREVIEW GENERATORS =====

    private List<Map<String, Object>> getAssetsSummaryPreview() {
        List<Map<String, Object>> preview = new ArrayList<>();
        List<Asset> assets = assetService.findAllAssets();

        int limit = Math.min(10, assets.size());
        for (int i = 0; i < limit; i++) {
            Asset asset = assets.get(i);
            Map<String, Object> row = new HashMap<>();
            row.put("assetCode", asset.getAssetCode());
            row.put("assetName", asset.getAssetName());
            row.put("categoryId", asset.getCategoryId());
            row.put("status", asset.getStatus());
            row.put("purchaseCost", asset.getPurchaseCost());
            row.put("currentValue", asset.getCurrentValue());
            preview.add(row);
        }

        return preview;
    }

    private List<Map<String, Object>> getMaintenanceReportPreview() {
        List<Map<String, Object>> preview = new ArrayList<>();
        List<MaintenanceRequest> requests = maintenanceRequestService.findAllRequests();

        int limit = Math.min(10, requests.size());
        for (int i = 0; i < limit; i++) {
            MaintenanceRequest req = requests.get(i);
            Map<String, Object> row = new HashMap<>();
            row.put("requestCode", req.getRequestCode());
            row.put("assetId", req.getAssetId());
            row.put("issueDescription", req.getIssueDescription());
            row.put("priority", req.getPriority());
            row.put("status", req.getStatus());
            row.put("maintenanceCost", req.getMaintenanceCost());
            preview.add(row);
        }

        return preview;
    }

    private List<Map<String, Object>> getAssetsByStatusPreview() {
        List<Map<String, Object>> preview = new ArrayList<>();

        // Group assets by status for preview
        Map<String, Integer> statusCount = new HashMap<>();
        List<Asset> assets = assetService.findAllAssets();

        for (Asset asset : assets) {
            String status = asset.getStatus();
            statusCount.put(status, statusCount.getOrDefault(status, 0) + 1);
        }

        for (Map.Entry<String, Integer> entry : statusCount.entrySet()) {
            Map<String, Object> row = new HashMap<>();
            row.put("status", entry.getKey());
            row.put("assetCount", entry.getValue());
            preview.add(row);
        }

        return preview;
    }
}