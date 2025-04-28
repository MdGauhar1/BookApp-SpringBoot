package com.example.bookfrontend;
import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;



@Service
public class BookSearchService {

    private final RestTemplate restTemplate;

    @Autowired
    public BookSearchService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public JsonNode searchBooks(String query) {
        String url = "https://openlibrary.org/search.json?q=" + query;
        return restTemplate.getForObject(url, JsonNode.class);
    }
}

