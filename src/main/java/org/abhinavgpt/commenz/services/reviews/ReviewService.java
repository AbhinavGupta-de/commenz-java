package org.abhinavgpt.commenz.services.reviews;

import org.abhinavgpt.commenz.exceptions.InvalidURLException;

import java.util.List;

public sealed interface ReviewService permits ReviewServiceImpl {
    List<String> getReviews(String url) throws InvalidURLException;
}