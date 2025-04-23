package com.example.bookfrontend;

public class BookDTO {
    private String id;
    private String title;
    private String author;
    private String imageBase64;

    public BookDTO(Book book) {
        this.id = book.getId();
        this.title = book.getTitle();
        this.author = book.getAuthor();
        if (book.getImageData() != null) {
            this.imageBase64 = java.util.Base64.getEncoder().encodeToString(book.getImageData());
        }
    }

    // Getters & Setters
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }
    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }
    public String getAuthor() { return author; }
    public void setAuthor(String author) { this.author = author; }
    public String getImageBase64() { return imageBase64; }
    public void setImageBase64(String imageBase64) { this.imageBase64 = imageBase64; }
}
