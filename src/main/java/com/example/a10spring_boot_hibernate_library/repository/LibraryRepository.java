package com.example.a10spring_boot_hibernate_library.repository;

import com.example.a10spring_boot_hibernate_library.entities.Library;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.util.List;

@Repository
public interface LibraryRepository extends JpaRepository <Library, Long> {
    // You can add custom query methods here if needed
    List<Library> findByPublisherInAndDescriptionContaining(List<String> publishers, String searchTerm);

    public List<Library> findByPublisherIgnoreCase(String publisher);

    public List<Library> findByFirstNameIgnoreCase(String name);

    public List<Library> findByPriceLessThanEqual(double price);

    public List<Library> findByPublishDateGreaterThanEqual(Date date);


}

