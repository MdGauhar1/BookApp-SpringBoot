package com.example.bookfrontend;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/reviews")
@CrossOrigin(origins = "http://localhost:4200")
public class ReviewController {

    @Autowired
    private ReviewRepository reviewRepository;

    @Autowired
    private BookRepository bookRepository;

    // Add a review for a book
    @PostMapping("/{bookId}")
    public ResponseEntity<String> addReview(@PathVariable String bookId, @RequestBody Review review) {
        Book book = bookRepository.findById(bookId).orElseThrow(() -> new RuntimeException("Book not found"));

        review.setBook(book);

        reviewRepository.save(review);

        return ResponseEntity.ok("Review added successfully");
    }

    @GetMapping("/{bookId}")
    public ResponseEntity<List<Review>> getReviews(@PathVariable String bookId) {
        List<Review> reviews = reviewRepository.findByBookId(bookId);

        return ResponseEntity.ok(reviews);
    }
}
