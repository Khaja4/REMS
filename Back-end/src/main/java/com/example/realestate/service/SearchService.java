package com.example.realestate.service;

import com.example.realestate.model.Property;
import com.example.realestate.repository.PropertyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class SearchService {

    @Autowired
    private PropertyRepository propertyRepository;

    public List<Property> searchProperties(String keyword, BigDecimal minPrice, BigDecimal maxPrice, Double minArea, Double maxArea, Property.PropertyType type) {
        Specification<Property> spec = Specification.where(null);

        if (keyword != null && !keyword.isEmpty()) {
            spec = spec.and((root, query, cb) -> cb.or(
                    cb.like(root.get("title"), "%" + keyword + "%"),
                    cb.like(root.get("description"), "%" + keyword + "%")
            ));
        }

        if (minPrice != null) {
            spec = spec.and((root, query, cb) -> cb.greaterThanOrEqualTo(root.get("price"), minPrice));
        }

        if (maxPrice != null) {
            spec = spec.and((root, query, cb) -> cb.lessThanOrEqualTo(root.get("price"), maxPrice));
        }

        if (minArea != null) {
            spec = spec.and((root, query, cb) -> cb.greaterThanOrEqualTo(root.get("area"), minArea));
        }

        if (maxArea != null) {
            spec = spec.and((root, query, cb) -> cb.lessThanOrEqualTo(root.get("area"), maxArea));
        }

        if (type != null) {
            spec = spec.and((root, query, cb) -> cb.equal(root.get("type"), type));
        }

        return propertyRepository.findAll(spec);
    }
}