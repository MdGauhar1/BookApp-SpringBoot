package com.example.bookfrontend;


import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/books")
@CrossOrigin(origins = "http://localhost:4200")
public class BookController {
    @Autowired
    private BookRepository repo;

    @GetMapping
    public List<Book> getAll() { return repo.findAll(); }

    @PostMapping
    public Book add(@Valid @RequestBody Book book) { return repo.save(book); }

    @DeleteMapping("/{id}") public void delete(@PathVariable Long id) { repo.deleteById(id); }
}

