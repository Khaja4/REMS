package com.example.realestate.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.realestate.model.Property;
import com.example.realestate.model.User;

public interface PropertyRepository extends JpaRepository<Property, Long>, JpaSpecificationExecutor<Property> {
    List<Property> findBySeller(User seller);

    @Query("SELECT p FROM Property p JOIN p.favoritedByUsers u WHERE u.id = :userId")
    List<Property> findFavoritesByUserId(@Param("userId") Long userId);
}
