package com.school.dashboard.controller;

import com.school.dashboard.dto.UpdateRequest;
import com.school.dashboard.model.DashboardData;
import com.school.dashboard.service.DashboardService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = {"http://localhost:5173", "http://localhost:3000", "http://127.0.0.1:5173"})
public class DashboardController {
    
    @Autowired
    private DashboardService dashboardService;
    
    @GetMapping("/dashboard")
    public ResponseEntity<DashboardData> getDashboardData() {
        try {
            DashboardData data = dashboardService.getDashboardData();
            return ResponseEntity.ok(data);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }
    
    @PostMapping("/dashboard")
    public ResponseEntity<DashboardData> updateDashboardData(@Valid @RequestBody UpdateRequest request) {
        try {
            DashboardData updatedData = dashboardService.updateDashboardData(request);
            return ResponseEntity.ok(updatedData);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }
    
    @GetMapping("/health")
    public ResponseEntity<String> healthCheck() {
        return ResponseEntity.ok("School Dashboard API is running!");
    }
}