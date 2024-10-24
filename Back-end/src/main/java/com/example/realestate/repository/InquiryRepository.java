package com.example.realestate.repository;

import com.example.realestate.model.Inquiry;
import com.example.realestate.model.Property;
import com.example.realestate.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface InquiryRepository extends JpaRepository<Inquiry, Long> {
    List<Inquiry> findByProperty(Property property);
    List<Inquiry> findByBuyer(User buyer);
}