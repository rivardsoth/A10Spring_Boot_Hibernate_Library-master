package com.example.a10spring_boot_hibernate_library.controllers.jsonContorller;

import com.example.a10spring_boot_hibernate_library.entities.Library;
import com.example.a10spring_boot_hibernate_library.services.LibraryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class JsonLibraryController {

    private LibraryService libraryService;

    @Autowired
    public JsonLibraryController(LibraryService libraryService) {
        this.libraryService = libraryService;
    }

    @GetMapping("/jsonlibrary") //http://localhost:8080/jsonlibrary
    public List<Library> getAllBooks() {
        return libraryService.getAllBooks();
    }

    @GetMapping("/jsonlibrary/{EanIsbn13}") //http://localhost:8080/jsonlibrary/EanIsbn13
    public Library getBooksByEanIsbn13(@PathVariable("EanIsbn13") long EanIsbn13) {
        //Pour ne pas changer la methode deja existant
        List<Library> livrestrouves = libraryService.getBookByEanIsbn13(EanIsbn13).stream().toList();
        Library liv = null;
        for (Library temp: livrestrouves) {
             liv = temp;
        }
        //prendre le 1er livre trouvee parce que eanisbn13 unique
        return liv;
    }

    //ajouter un livre dans la bd
    @PostMapping("/jsonlibrary")
    public ResponseEntity<Library> ajouterNouveaulivre(@RequestBody Library nouveauLivre) {
        Library savedLivre = libraryService.createBook(nouveauLivre);
        return new ResponseEntity<>(savedLivre, HttpStatus.CREATED);
    }

    //modifier un livre de la bd
    @PostMapping("/jsonlibraryModifie")
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
