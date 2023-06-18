package com.example.a10spring_boot_hibernate_library.controllers.jsonController;

import com.example.a10spring_boot_hibernate_library.entities.Library;
import com.example.a10spring_boot_hibernate_library.services.LibraryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class JsonLibraryController {

    private LibraryService libraryService;

    @Autowired
    public JsonLibraryController(LibraryService libraryService) {
        this.libraryService = libraryService;
    }

    @GetMapping("/jsonLibrary") //http://localhost:8080/jsonlibrary
    public List<Library> getAllBooks() {
        return libraryService.getAllBooks();
    }

    @GetMapping("/jsonLibrary/{EanIsbn13}") //http://localhost:8080/jsonlibrary/EanIsbn13
    public ResponseEntity<?> getClientById(@PathVariable("id") int id) {
        Optional<Library> optionalLibrary = libraryService.getBookByEanIsbn13(id);
        if (optionalLibrary.isPresent()) {
            Library livre =optionalLibrary.get();
            return ResponseEntity.ok(livre);
        } else {
            String errorMessage = "Livre with ID " + id + " does not exist.";
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorMessage);
        }
    }

    //ajouter un livre dans la bd
    @PostMapping("/jsonLibrary")
    public ResponseEntity<Library> ajouterNouveaulivre(@RequestBody Library nouveauLivre) {
        Library savedLivre = libraryService.createBook(nouveauLivre);
        return new ResponseEntity<>(savedLivre, HttpStatus.CREATED);
    }

    //modifier un livre de la bd
    @PostMapping("/jsonLibraryModifie")
    public ResponseEntity<String> majlivre(@RequestBody Library majLivre) {
        if(libraryService.getBookByEanIsbn13(majLivre.getEanIsbn13()).isEmpty()) {
            String errorMessage = "L'eanIsbn13 " + majLivre.getEanIsbn13() + " n'existe pas!";
            return ResponseEntity.badRequest().body(errorMessage);
        }
        else {
            libraryService.updateBook(majLivre);
            String message = "Mise à jour effectué pour l'eanIsbn13 " + majLivre.getEanIsbn13() + "!";
            return ResponseEntity.ok(message);
        }
    }

    /*Pas le temps de comprendre cette syntaxe
    @GetMapping("/jsonLibrarySearch/{searchTerm}/{publishers}")
    public List<Library> searchBooks(@RequestParam("searchTerm") String searchTerm,
                              @RequestParam("publishers") List<String> publishers) {
        return libraryService.searchBooksByPublishersAndDescription(searchTerm, publishers);
    }*/

}
