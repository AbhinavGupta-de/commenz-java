package org.abhinavgpt.commenz.controllers;

import org.abhinavgpt.commenz.services.summary.SummaryService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/review")
public class ReviewController {

    private final SummaryService summaryService ;

    public ReviewController(SummaryService summaryService) {
        this.summaryService = summaryService;
    }

    @GetMapping("")
    public ResponseEntity<String> getReviews() {
        try {
            return ResponseEntity.ok(summaryService.greetingMessage());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/summarize")
    public ResponseEntity<String> summarize(@RequestBody String url) {
        try {
            return ResponseEntity.ok(summaryService.getSummary(url));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

}
