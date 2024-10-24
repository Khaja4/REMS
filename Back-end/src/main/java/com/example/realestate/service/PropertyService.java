package com.example.realestate.service;

import com.example.realestate.model.Property;
import com.example.realestate.model.User;
import com.example.realestate.repository.PropertyRepository;
import com.example.realestate.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class PropertyService {

    @Autowired
    private PropertyRepository propertyRepository;

    @Autowired
    private UserRepository userRepository;

    public Property addProperty(Property property) {
        User currentUser = getCurrentUser();
        property.setSeller(currentUser);
        return propertyRepository.save(property);
    }

    public Property getPropertyById(Long id) {
        return propertyRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Property not found"));
    }

    public List<Property> getAllProperties() {
        return propertyRepository.findAll();
    }

    public Property updateProperty(Long id, Property propertyDetails) {
        Property property = getPropertyById(id);
        property.setTitle(propertyDetails.getTitle());
        property.setDescription(propertyDetails.getDescription());
        property.setPrice(propertyDetails.getPrice());
        property.setArea(propertyDetails.getArea());
        property.setType(propertyDetails.getType());
        property.setImages(propertyDetails.getImages());
        return propertyRepository.save(property);
    }

    public void deleteProperty(Long id) {
        Property property = getPropertyById(id);
        propertyRepository.delete(property);
    }

    public List<Property> getPropertiesBySeller(Long sellerId) {
        User seller = userRepository.findById(sellerId)
                .orElseThrow(() -> new RuntimeException("Seller not found"));
        return propertyRepository.findBySeller(seller);
    }

    public void addToFavorites(Long propertyId) {
        User currentUser = getCurrentUser();
        Property property = getPropertyById(propertyId);
        currentUser.getFavoriteProperties().add(property);
        userRepository.save(currentUser);
    }

    public void removeFromFavorites(Long propertyId) {
        User currentUser = getCurrentUser();
        Property property = getPropertyById(propertyId);
        currentUser.getFavoriteProperties().remove(property);
        userRepository.save(currentUser);
    }

    public boolean isOwner(Long propertyId, Long userId) {
        Property property = getPropertyById(propertyId);
        return property.getSeller().getId().equals(userId);
    }

    private User getCurrentUser() {
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Current user not found"));
    }
    
    public List<Property> getFavoriteProperties() {
        // Assuming you have a way to get the current user's ID
        User currentUserId = getCurrentUser(); // Implement this method
        return propertyRepository.findFavoritesByUserId(currentUserId);
    }

}