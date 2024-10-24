package com.example.realestate.controller;

import com.example.realestate.model.Property;
import com.example.realestate.service.PropertyComparisonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/compare")
public class PropertyComparisonController {

    @Autowired
    private PropertyComparisonService propertyComparisonService;

    @PostMapping
    @PreAuthorize("hasRole('BUYER')")
    public ResponseEntity<List<Property>> compareProperties(@RequestBody List<Long> propertyIds) {
        List<Property> comparedProperties = propertyComparisonService.compareProperties(propertyIds);
        return ResponseEntity.ok(comparedProperties);
    }
}