package org.abhinavgpt.commenz.services.scrapping;

public final class ScrapperDispatcher {
    public static Scrapper getScrapper(String url) {
        if (url.contains("amazon")) {
            return new AmazonScrapper();
        }
        return null;
    }
}
