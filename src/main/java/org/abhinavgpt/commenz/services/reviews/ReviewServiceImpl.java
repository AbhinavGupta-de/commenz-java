package org.abhinavgpt.commenz.services.reviews;

import org.abhinavgpt.commenz.exceptions.InvalidURLException;
import org.abhinavgpt.commenz.services.scrapping.Scrapper;
import org.abhinavgpt.commenz.services.scrapping.ScrapperDispatcher;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
final class ReviewServiceImpl implements ReviewService {

    @Override
    public List<String> getReviews(final String url) throws InvalidURLException {
        Scrapper scrapper = ScrapperDispatcher.getScrapper(url);
        if (scrapper == null) {
            throw new InvalidURLException("Invalid URL");
        }
        return scrapper.scrap(url);
    }
}