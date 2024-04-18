package org.abhinavgpt.commenz.controllers;

import org.abhinavgpt.commenz.exceptions.InvalidURLException;
import org.abhinavgpt.commenz.services.orchestrator.ReviewOrchestrator;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/review")
public class ReviewController
{
	private final ReviewOrchestrator reviewOrchestrator;

	@GetMapping("/summarize")
	public ResponseEntity<String> summarize(@RequestParam String url) throws InvalidURLException {
		return ResponseEntity.ok(reviewOrchestrator.getSummarizedReviews(url).toString());
	}

	public ReviewController(final ReviewOrchestrator reviewOrchestrator)
	{
		this.reviewOrchestrator = reviewOrchestrator;
	}
}