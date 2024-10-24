package com.example.realestate.service;

import com.example.realestate.model.Inquiry;
import com.example.realestate.model.Property;
import com.example.realestate.model.User;
import com.example.realestate.repository.InquiryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InquiryService {

    @Autowired
    private InquiryRepository inquiryRepository;

    public Inquiry createInquiry(User buyer, Property property, String message) {
        Inquiry inquiry = new Inquiry();
        inquiry.setBuyer(buyer);
        inquiry.setProperty(property);
        inquiry.setMessage(message);
        
        return inquiryRepository.save(inquiry);
    }

    public List<Inquiry> getInquiriesForProperty(Property property) {
        return inquiryRepository.findByProperty(property);
    }

    public List<Inquiry> getInquiriesForBuyer(User buyer) {
        return inquiryRepository.findByBuyer(buyer);
    }
}