package com.example.a10spring_boot_hibernate_library.repository;

import com.example.a10spring_boot_hibernate_library.entities.ShoppingCart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ShoppingCartRepository extends JpaRepository<ShoppingCart, Integer> {
    // You can add custom query methods here if needed
}

