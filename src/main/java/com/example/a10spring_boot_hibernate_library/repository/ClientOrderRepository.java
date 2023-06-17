package com.example.a10spring_boot_hibernate_library.repository;

import com.example.a10spring_boot_hibernate_library.entities.ClientOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ClientOrderRepository extends JpaRepository<ClientOrder, Integer> {
    // You can add custom query methods here if needed
}
