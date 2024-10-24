package com.example.realestate.controller;

import com.example.realestate.model.Property;
import com.example.realestate.service.SearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/api/search")
public class SearchController {

    @Autowired
    private SearchService searchService;

    @GetMapping("/properties")
    public ResponseEntity<List<Property>> searchProperties(
            @RequestParam(required = false) String keyword,
            @RequestParam(required = false) BigDecimal minPrice,
            @RequestParam(required = false) BigDecimal maxPrice,
            @RequestParam(required = false) Double minArea,
            @RequestParam(required = false) Double maxArea,
            @RequestParam(required = false) Property.PropertyType type) {
        List<Property> properties = searchService.searchProperties(keyword, minPrice, maxPrice, minArea, maxArea, type);
        return ResponseEntity.ok(properties);
    }
}