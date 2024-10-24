package com.example.realestate.service;

import com.example.realestate.model.Property;
import com.example.realestate.model.User;
import com.example.realestate.repository.PropertyRepository;
import com.example.realestate.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminDashboardService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PropertyRepository propertyRepository;

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public List<Property> getAllProperties() {
        return propertyRepository.findAll();
    }

    public void approveProperty(Long propertyId) {
        Property property = propertyRepository.findById(propertyId)
                .orElseThrow(() -> new RuntimeException("Property not found"));
        property.setApproved(true);
        propertyRepository.save(property);
    }

    public void removeProperty(Long propertyId) {
        propertyRepository.deleteById(propertyId);
    }

    public void removeUser(Long userId) {
        userRepository.deleteById(userId);
    }
}