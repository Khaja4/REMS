package com.example.realestate.controller;

import com.example.realestate.model.Property;
import com.example.realestate.model.User;
import com.example.realestate.service.AdminDashboardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin")
@PreAuthorize("hasRole('ADMIN')")
public class AdminDashboardController {

    @Autowired
    private AdminDashboardService adminDashboardService;

    @GetMapping("/users")
    public ResponseEntity<List<User>> getAllUsers() {
        return ResponseEntity.ok(adminDashboardService.getAllUsers());
    }

    @GetMapping("/properties")
    public ResponseEntity<List<Property>> getAllProperties() {
        return ResponseEntity.ok(adminDashboardService.getAllProperties());
    }

    @PostMapping("/properties/{propertyId}/approve")
    public ResponseEntity<Void> approveProperty(@PathVariable Long propertyId) {
        adminDashboardService.approveProperty(propertyId);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/properties/{propertyId}")
    public ResponseEntity<Void> removeProperty(@PathVariable Long propertyId) {
        adminDashboardService.removeProperty(propertyId);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/users/{userId}")
    public ResponseEntity<Void> removeUser(@PathVariable Long userId) {
        adminDashboardService.removeUser(userId);
        return ResponseEntity.ok().build();
    }
}