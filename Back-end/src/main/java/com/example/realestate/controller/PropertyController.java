package com.example.realestate.controller;

import com.example.realestate.model.Property;
import com.example.realestate.service.PropertyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/properties")
public class PropertyController {

    @Autowired
    private PropertyService propertyService;

    @PostMapping
    @PreAuthorize("hasRole('SELLER') or hasRole('AGENT')")
    public ResponseEntity<Property> addProperty(@RequestBody Property property) {
        Property addedProperty = propertyService.addProperty(property);
        return ResponseEntity.ok(addedProperty);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Property> getProperty(@PathVariable Long id) {
        Property property = propertyService.getPropertyById(id);
        return ResponseEntity.ok(property);
    }

    @GetMapping
    public ResponseEntity<List<Property>> getAllProperties() {
        List<Property> properties = propertyService.getAllProperties();
        return ResponseEntity.ok(properties);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN') or @propertyService.isOwner(#id, authentication.principal.id)")
    public ResponseEntity<Property> updateProperty(@PathVariable Long id, @RequestBody Property property) {
        Property updatedProperty = propertyService.updateProperty(id, property);
        return ResponseEntity.ok(updatedProperty);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN') or @propertyService.isOwner(#id, authentication.principal.id)")
    public ResponseEntity<Void> deleteProperty(@PathVariable Long id) {
        propertyService.deleteProperty(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/seller/{sellerId}")
    @PreAuthorize("hasRole('ADMIN') or #sellerId == authentication.principal.id")
    public ResponseEntity<List<Property>> getPropertiesBySeller(@PathVariable Long sellerId) {
        List<Property> properties = propertyService.getPropertiesBySeller(sellerId);
        return ResponseEntity.ok(properties);
    }

    @PostMapping("/{id}/favorite")
    @PreAuthorize("hasRole('BUYER')")
    public ResponseEntity<Void> addToFavorites(@PathVariable Long id) {
        propertyService.addToFavorites(id);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}/favorite")
    @PreAuthorize("hasRole('BUYER')")
    public ResponseEntity<Void> removeFromFavorites(@PathVariable Long id) {
        propertyService.removeFromFavorites(id);
        return ResponseEntity.noContent().build();
    }
    
    @GetMapping("/favorites")
    @PreAuthorize("hasRole('BUYER')")
    public ResponseEntity<List<Property>> getFavoriteProperties() {
        List<Property> favorites = propertyService.getFavoriteProperties(); // Implement this in your service layer
        return ResponseEntity.ok(favorites);
    }

}