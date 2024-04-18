package org.abhinavgpt.commenz.controllers;

import org.abhinavgpt.commenz.services.orchestrator.ReviewOrchestrator;
import org.abhinavgpt.commenz.services.reviews.ReviewService;
import org.abhinavgpt.commenz.services.summary.SummaryService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/review")
public class ReviewController
{
	private final ReviewOrchestrator reviewOrchestrator;

	@GetMapping("/summarize")
	public ResponseEntity<String> summarize(@RequestParam String url)
	{
		return ResponseEntity.ok(reviewOrchestrator.getSummarizedReviews(url).toString());
	}

	public ReviewController(final ReviewOrchestrator reviewOrchestrator)
	{
		this.reviewOrchestrator = reviewOrchestrator;
	}
}