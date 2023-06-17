package com.example.a10spring_boot_hibernate_library.controllers.library;

import com.example.a10spring_boot_hibernate_library.entities.Library;
import com.example.a10spring_boot_hibernate_library.services.LibraryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class LibraryController {

    private LibraryService libraryService;

    @Autowired
    public LibraryController(LibraryService libraryService) {
        this.libraryService = libraryService;
    }

    @GetMapping("/")
    public String index() {
        return "index";
    }

    @GetMapping("/all-books")
    public String getAllBooks(Model model) {
        List<Library> books = libraryService.getAllBooks();
        model.addAttribute("books", books);
        return "all-books";
    }
}
