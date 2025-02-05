package com.jobautomation.exception;

import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriverException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(TimeoutException.class)
    public ResponseEntity<String> handleTimeout(TimeoutException e) {
        log.error("Operation timed out", e);
        return ResponseEntity.status(504).body("Operation timed out: " + e.getMessage());
    }

    @ExceptionHandler(WebDriverException.class)
    public ResponseEntity<String> handleWebDriver(WebDriverException e) {
        log.error("Selenium WebDriver error", e);
        return ResponseEntity.status(500).body("Browser automation error: " + e.getMessage());
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleGeneral(Exception e) {
        log.error("Unexpected error", e);
        return ResponseEntity.status(500).body("Internal server error: " + e.getMessage());
    }
}