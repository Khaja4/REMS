package com.example.realestate.controller;

import com.example.realestate.model.Inquiry;
import com.example.realestate.model.Property;
import com.example.realestate.model.User;
import com.example.realestate.service.InquiryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/inquiries")
public class InquiryController {

    @Autowired
    private InquiryService inquiryService;

    @PostMapping
    @PreAuthorize("hasRole('BUYER')")
    public ResponseEntity<Inquiry> createInquiry(@AuthenticationPrincipal User user,
                                                 @RequestParam Long propertyId,
                                                 @RequestParam String message) {
        Property property = new Property();
        property.setId(propertyId);
        Inquiry inquiry = inquiryService.createInquiry(user, property, message);
        return ResponseEntity.ok(inquiry);
    }

    @GetMapping("/property/{propertyId}")
    @PreAuthorize("hasAnyRole('SELLER', 'AGENT', 'ADMIN')")
    public ResponseEntity<List<Inquiry>> getInquiriesForProperty(@PathVariable Long propertyId) {
        Property property = new Property();
        property.setId(propertyId);
        List<Inquiry> inquiries = inquiryService.getInquiriesForProperty(property);
        return ResponseEntity.ok(inquiries);
    }

    @GetMapping("/buyer")
    @PreAuthorize("hasRole('BUYER')")
    public ResponseEntity<List<Inquiry>> getInquiriesForBuyer(@AuthenticationPrincipal User user) {
        List<Inquiry> inquiries = inquiryService.getInquiriesForBuyer(user);
        return ResponseEntity.ok(inquiries);
    }
}