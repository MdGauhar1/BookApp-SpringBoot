package com.example.bookfrontend;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/books")
@CrossOrigin(origins = "http://localhost:4200")
public class BookController {

    @Autowired
    private BookRepository bookRepository;

    @GetMapping
    public List<Book> getAll() {
        return bookRepository.findAll();
    }

    @PostMapping
    public Book add(@Valid @RequestBody Book book) {
        return bookRepository.save(book);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable String id) {
        bookRepository.deleteById(id);
    }

    @GetMapping("/by-title")
    public ResponseEntity<List<Book>> getBooksByTitle(@RequestParam String title) {
        return ResponseEntity.ok(bookRepository.findByTitle(title));
    }

    @GetMapping("/by-author")
    public ResponseEntity<List<Book>> getBooksByAuthor(@RequestParam String author) {
        return ResponseEntity.ok(bookRepository.findByAuthor(author));
    }

    // ✅ Only keep this upload method
    @PostMapping(value = "/upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<String> uploadBook(
            @RequestParam("title") String title,
            @RequestParam("author") String author,
            @RequestParam("file") MultipartFile file,
            @RequestParam("image") MultipartFile image) throws IOException {

        if (title == null || title.isBlank() || author == null || author.isBlank()) {
            return ResponseEntity.badRequest().body("Title and Author must not be empty");
        }

        Book book = new Book();
        book.setTitle(title);
        book.setAuthor(author);
        book.setFileData(file.getBytes());
        book.setImageData(image.getBytes());

        bookRepository.save(book);
        return ResponseEntity.ok("Book uploaded successfully");
    }

    @GetMapping("/download/{id}")
    public ResponseEntity<byte[]> downloadBook(@PathVariable String id) {
        Book book = bookRepository.findById(id).orElseThrow();

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + book.getTitle() + ".pdf\"")
                .contentType(MediaType.APPLICATION_PDF)
                .body(book.getFileData());
    }
}
