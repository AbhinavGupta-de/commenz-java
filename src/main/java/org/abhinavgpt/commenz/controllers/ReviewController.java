package org.abhinavgpt.commenz.controllers;

import org.abhinavgpt.commenz.exceptions.InvalidURLException;
import org.abhinavgpt.commenz.exceptions.URLNotSupportedException;
import org.abhinavgpt.commenz.services.orchestrator.ReviewOrchestrator;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/review")
public class ReviewController {
    private final ReviewOrchestrator reviewOrchestrator;

    public ReviewController(final ReviewOrchestrator reviewOrchestrator) {
        this.reviewOrchestrator = reviewOrchestrator;
    }

    @GetMapping("/summarize")
    public ResponseEntity<String> summarize(@RequestParam String url) throws InvalidURLException, URLNotSupportedException {
        return ResponseEntity.ok(reviewOrchestrator.getSummarizedReviews(url).toString());
    }
}