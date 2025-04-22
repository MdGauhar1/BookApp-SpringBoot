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
        // Fetch the book by its ID from the database
        Book book = bookRepository.findById(bookId).orElseThrow(() -> new RuntimeException("Book not found"));

        // Set the book to the review (establish the relationship)
        review.setBook(book);

        // Save the review
        reviewRepository.save(review);

        // Return a success response
        return ResponseEntity.ok("Review added successfully");
    }

    // Get reviews for a specific book
    @GetMapping("/{bookId}")
    public ResponseEntity<List<Review>> getReviews(@PathVariable String bookId) {
        // Retrieve all reviews for the specified book
        List<Review> reviews = reviewRepository.findByBookId(bookId);

        // Return the reviews in the response
        return ResponseEntity.ok(reviews);
    }
}
