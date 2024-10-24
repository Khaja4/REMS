package com.example.realestate.repository;

import com.example.realestate.model.Favorite;
import com.example.realestate.model.Property;
import com.example.realestate.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FavoriteRepository extends JpaRepository<Favorite, Long> {
    List<Favorite> findByUser(User user);
    boolean existsByUserAndProperty(User user, Property property);
    void deleteByUserAndProperty(User user, Property property);
}