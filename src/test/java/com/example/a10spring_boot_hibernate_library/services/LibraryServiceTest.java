package com.example.a10spring_boot_hibernate_library.services;

import com.example.a10spring_boot_hibernate_library.entities.Client;
import com.example.a10spring_boot_hibernate_library.entities.Library;
import com.example.a10spring_boot_hibernate_library.repository.LibraryRepository;
import com.example.a10spring_boot_hibernate_library.services.LibraryService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
public class LibraryServiceTest {

    @Mock
    private LibraryRepository libraryRepository;

    @InjectMocks
    private LibraryService libraryService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        libraryService = new LibraryService(libraryRepository);
    }

    @Test
    public void testGetAllBooks() {
        List<Library> expectedBooks = new ArrayList<>();
        // Add sample books to the expected list

        when(libraryRepository.findAll()).thenReturn(expectedBooks);

        List<Library> actualBooks = libraryService.getAllBooks();

        assertEquals(expectedBooks, actualBooks);
        verify(libraryRepository, times(1)).findAll();
    }

    @Test
    void testGetGetAllBooksQuandBdVide() {
        // Create a list of dummy clients
        List<Library> livres = new ArrayList<>();

        // Mock the behavior of the clientRepository
        when(libraryRepository.findAll()).thenReturn(livres);

        // Call the method under test
        List<Library> result = libraryService.getAllBooks();

        // Verify the result
        assertTrue(result.isEmpty());
        verify(libraryRepository, times(1)).findAll();
    }

    @Test
    public void testGetBookByEanIsbn13() {
        long eanIsbn13 = 1234567890123L;
        Library expectedBook = new Library();
        // Set properties of the expected book

        when(libraryRepository.findById(eanIsbn13)).thenReturn(Optional.of(expectedBook));

        Optional<Library> actualBook = libraryService.getBookByEanIsbn13(eanIsbn13);

        assertEquals(expectedBook, actualBook.orElse(null));
        verify(libraryRepository, times(1)).findById(eanIsbn13);
    }

    @Test
    public void testGetBookByEanIsbn13AvecLivreNonExistant() {
        long eanIsbn13 = 1234567890123L;

        when(libraryRepository.findById(eanIsbn13)).thenReturn(Optional.empty());

        Optional<Library> actualBook = libraryService.getBookByEanIsbn13(eanIsbn13);

        assertFalse(actualBook.isPresent());
        verify(libraryRepository, times(1)).findById(eanIsbn13);
    }

    @Test
    public void testCreateBook() {
        Library book = new Library();
        // Set properties of the book to be created

        when(libraryRepository.save(book)).thenReturn(book);

        Library createdBook = libraryService.createBook(book);

        assertEquals(book, createdBook);
        verify(libraryRepository, times(1)).save(book);
    }

    @Test
    public void testUpdateBook() {
        Library book = new Library();
        // Set properties of the book to be updated

        libraryService.updateBook(book);

        verify(libraryRepository, times(1)).save(book);
    }

    @Test
    public void testSearchBooksByPublishersAndDescription() {
        String searchTerm = "Spring";
        List<String> publishers = List.of("Publisher1", "Publisher2");

        List<Library> expectedBooks = new ArrayList<>();
        // Add sample books to the expected list

        when(libraryRepository.findByPublisherInAndDescriptionContaining(publishers, searchTerm)).thenReturn(expectedBooks);

        List<Library> actualBooks = libraryService.searchBooksByPublishersAndDescription(searchTerm, publishers);

        assertEquals(expectedBooks, actualBooks);
        verify(libraryRepository, times(1)).findByPublisherInAndDescriptionContaining(publishers, searchTerm);
    }


}
