package org.abhinavgpt.commenz.controllers;

import org.abhinavgpt.commenz.services.reviews.ReviewService;
import org.abhinavgpt.commenz.services.summary.SummaryService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/review")
public class ReviewController
{
	private final SummaryService summaryService;
	private final ReviewService reviewService;

	// We don't need this method - do we?
//	@GetMapping("")
//	public ResponseEntity<String> getReviews() // What should this do?
//	{
//		try
//		{
//			return ResponseEntity.ok(summaryService.greetingMessage());
//		} catch (Exception e)
//		{
//			return ResponseEntity.badRequest().body(e.getMessage());
//		}
//	}

	// changed this to Get from Post and @RequestParam from @RequestBody
	@GetMapping("/summarize")
	public ResponseEntity<String> summarize(@RequestParam String url)
	{
		List<String> reviews = reviewService.getReviews(url);
		List<String> summaries = reviews.stream().map(summaryService::getSummary).toList();
		return ResponseEntity.ok(summaries.toString()); // TODO: refine this and see how to deal with an url having multiple reviews and summaries
//		try
//		{
//			return ResponseEntity.ok(summaryService.getSummary(url));
//		} catch (Exception e)
//		{
//			return ResponseEntity.badRequest().body(e.getMessage());
//		}
	}

	public ReviewController(SummaryService summaryService, final ReviewService reviewService)
	{
		this.summaryService = summaryService;
		this.reviewService = reviewService;
	}
}