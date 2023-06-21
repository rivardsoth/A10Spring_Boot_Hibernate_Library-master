package com.example.a10spring_boot_hibernate_library.services;

import com.example.a10spring_boot_hibernate_library.entities.Library;
import com.example.a10spring_boot_hibernate_library.repository.LibraryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class LibraryService {

    private final LibraryRepository libraryRepository;

    @Autowired
    public LibraryService(LibraryRepository libraryRepository) {
        this.libraryRepository = libraryRepository;
    }

    public List<Library> getAllBooks() {
        return libraryRepository.findAll();
    }

    public Optional<Library> getBookByEanIsbn13(long eanIsbn13) {
        return libraryRepository.findById(eanIsbn13);
    }

    public Library createBook(Library book) {return libraryRepository.save(book);
    }

    public void updateBook(Library book) {libraryRepository.save(book);
    }


    public List<Library> searchBooksByPublishersAndDescription(String searchTerm, List<String> publishers) {
        return libraryRepository.findByPublisherInAndDescriptionContaining(publishers, searchTerm);
    }

    //methode de filtre
    public List<Library> chercherLesLivreAvecPublisher(String publisher) {
        return libraryRepository.findByPublisherIgnoreCase(publisher);
    }

    public List<Library> chercherLesLivreAvecFirstname(String name) {
        return libraryRepository.findByFirstNameIgnoreCase(name);
    }

    public List<Library> chercherLesLivresinferieurEgalA(double price) {
        return libraryRepository.findByPriceLessThanEqual(price);
    }

    public List<Library> chercherLaDateDePublicationSuperieurA(String date) {
        return libraryRepository.findByPublishDateGreaterThanEqual(Date.valueOf(date));
    }

}
