package com.isoftstone.assetsmanagement.controller;

import org.springframework.web.bind.annotation.*;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/settings")
public class SettingsController {

    // In-memory storage for demo (use database in production)
    private static final Map<String, Object> settings = new HashMap<>();

    static {
        // Default settings
        settings.put("companyName", "Asset Management Inc.");
        settings.put("fiscalYearStart", "2024-01-01");
        settings.put("currency", "USD");
        settings.put("depreciationMethod", "STRAIGHT_LINE");
        settings.put("depreciationPeriod", 5);
        settings.put("enableAuditTrail", true);
        settings.put("enableEmailNotifications", false);
    }

    @GetMapping
    public Map<String, Object> getSettings() {
        return settings;
    }

    @PutMapping
    public Map<String, Object> updateSettings(@RequestBody Map<String, Object> newSettings) {
        settings.putAll(newSettings);
        return settings;
    }
}