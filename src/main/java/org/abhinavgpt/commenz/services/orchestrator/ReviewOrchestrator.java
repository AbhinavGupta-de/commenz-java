package org.abhinavgpt.commenz.services.orchestrator;

import org.abhinavgpt.commenz.exceptions.InvalidURLException;
import org.abhinavgpt.commenz.services.reviews.ReviewService;
import org.abhinavgpt.commenz.services.summary.SummaryService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReviewOrchestrator {
    private final ReviewService reviewService;
    private final SummaryService summaryService;

    public ReviewOrchestrator(final ReviewService reviewService, SummaryService summaryService) {
        this.reviewService = reviewService;
        this.summaryService = summaryService;
    }

    public List<String> getSummarizedReviews(String url) throws InvalidURLException {
        List<String> reviews = reviewService.getReviews(url);
        return reviews.stream().map(summaryService::getSummary).toList();
    }
}