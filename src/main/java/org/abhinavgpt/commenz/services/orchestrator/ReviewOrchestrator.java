package org.abhinavgpt.commenz.services.orchestrator;

import org.abhinavgpt.commenz.exceptions.InvalidURLException;
import org.abhinavgpt.commenz.exceptions.URLNotSupportedException;
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

    public String getSummarizedReviews(String url) throws InvalidURLException, URLNotSupportedException {
        List<String> reviews = reviewService.getReviews(url);
        return summaryService.getSummary(reviews);
    }
}