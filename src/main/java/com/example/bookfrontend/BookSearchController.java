package com.example.bookfrontend;


import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/books")
@CrossOrigin(origins = "http://localhost:4200")  // Allow Angular frontend
public class BookSearchController {

    private final BookSearchService bookSearchService;

    @Autowired
    public BookSearchController(BookSearchService bookSearchService) {
        this.bookSearchService = bookSearchService;
    }

    @GetMapping("/search")
    public ResponseEntity<JsonNode> searchBooks(@RequestParam("q") String query) {
        JsonNode booksJson = bookSearchService.searchBooks(query);
        return ResponseEntity.ok(booksJson); // Return as JsonNode
    }
}

