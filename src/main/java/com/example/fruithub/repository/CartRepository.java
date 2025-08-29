package com.example.fruithub.repository;

import com.example.fruithub.entity.Cart;
import com.example.fruithub.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.UUID;

public interface CartRepository extends JpaRepository<Cart, UUID> {
    @Query("SELECT COALESCE(SUM(c.quantity), 0) FROM Cart c WHERE c.user = :user")
    Integer getTotalItemCountByUser(@Param("user") User user);
}
