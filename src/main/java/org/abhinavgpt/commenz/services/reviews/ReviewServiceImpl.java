package org.abhinavgpt.commenz.services.reviews;

import org.abhinavgpt.commenz.exceptions.InvalidURLException;
import org.abhinavgpt.commenz.exceptions.URLNotSupportedException;
import org.abhinavgpt.commenz.services.scrapping.Scrapper;
import org.abhinavgpt.commenz.services.scrapping.ScrapperDispatcher;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
final class ReviewServiceImpl implements ReviewService {

    @Override
    public List<String> getReviews(final String url) throws InvalidURLException, URLNotSupportedException {
        Scrapper scrapper = ScrapperDispatcher.getScrapper(url);
        return scrapper.scrap(url);
    }
}