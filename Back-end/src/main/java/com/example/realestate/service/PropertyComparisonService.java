package com.example.realestate.service;

import com.example.realestate.model.Property;
import com.example.realestate.repository.PropertyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PropertyComparisonService {

    @Autowired
    private PropertyRepository propertyRepository;

    public List<Property> compareProperties(List<Long> propertyIds) {
        return propertyRepository.findAllById(propertyIds);
    }
}