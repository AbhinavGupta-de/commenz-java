package org.abhinavgpt.commenz.services.scrapping;

import org.abhinavgpt.commenz.exceptions.URLNotSupportedException;

public final class ScrapperDispatcher {
    public static Scrapper getScrapper(String url) throws URLNotSupportedException {
        if (url.contains("amazon")) {
            return new AmazonScrapper();
        }
        throw new URLNotSupportedException("URL not supported");
    }
}
