package org.abhinavgpt.commenz.services.reviews;

import org.abhinavgpt.commenz.services.scrapping.Scrapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
final class ReviewServiceImpl implements  ReviewService{

    private final Scrapper scrapper;

    public ReviewServiceImpl(final Scrapper scrapper) {
		this.scrapper = scrapper;
    }

    @Override
    public List<String> getReviews(final String url) {
        return List.of("ReviewServiceImpl.getReviews() called with url: " + url);
    }
}