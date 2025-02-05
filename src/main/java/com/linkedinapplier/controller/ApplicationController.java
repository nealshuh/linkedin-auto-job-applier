package com.jobautomation.controller;

import com.jobautomation.service.SeleniumService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/api/applications")
@RequiredArgsConstructor
public class ApplicationController {
    
    private final SeleniumService seleniumService;

    @PostMapping("/process")
    public ResponseEntity<String> processApplication(@RequestBody String applicationUrl) {
        try {
            seleniumService.processApplicationForm(applicationUrl);
            return ResponseEntity.ok("Application processed successfully");
        } catch (Exception e) {
            log.error("Failed to process application", e);
            return ResponseEntity.internalServerError().body("Failed to process application: " + e.getMessage());
        }
    }

    @GetMapping("/health")
    public ResponseEntity<String> healthCheck() {
        return ResponseEntity.ok("Service is running");
    }
}