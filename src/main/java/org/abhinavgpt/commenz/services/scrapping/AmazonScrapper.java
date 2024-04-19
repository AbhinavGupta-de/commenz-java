package org.abhinavgpt.commenz.services.scrapping;

import org.abhinavgpt.commenz.exceptions.InvalidURLException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.concurrent.ThreadLocalRandom;

@Service
final class AmazonScrapper implements Scrapper {

    List<String> userAgents = Arrays.asList("Mozilla/5.0 (Windows; U; Windows NT 6.3) AppleWebKit/533.1.0 (KHTML, like Gecko) Chrome/25.0.891.0 Safari/533.1.0", "Mozilla/5.0 (compatible; MSIE 8.0; Windows NT 5.0; Trident/6.1; .NET CLR 2.9.84083.0)", "Mozilla/5.0 (compatible; MSIE 7.0; Windows NT 6.1; Trident/3.1; .NET CLR 4.0.98053.6)", "Mozilla/5.0 (Windows; U; Windows NT 5.1) AppleWebKit/536.0.1 (KHTML, like Gecko) Chrome/23.0.822.0 Safari/536.0.1", "Mozilla/5.0 (Windows; U; Windows NT 5.0) AppleWebKit/533.2.2 (KHTML, like Gecko) Chrome/39.0.841.0 Safari/533.2.2", "Mozilla/5.0 (compatible; MSIE 8.0; Windows NT 6.2; Trident/7.0; .NET CLR 2.9.76162.7)", "Mozilla/5.0 (Windows; U; Windows NT 5.1) AppleWebKit/534.1.2 (KHTML, like Gecko) Chrome/29.0.804.0 Safari/534.1.2", "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_8_1 rv:6.0; RM) AppleWebKit/536.2.0 (KHTML, like Gecko) Version/6.1.6 Safari/536.2.0", "Mozilla/5.0 (Windows; U; Windows NT 6.0) AppleWebKit/538.2.0 (KHTML, like Gecko) Chrome/32.0.824.0 Safari/538.2.0", "Mozilla/5.0 (Windows; U; Windows NT 6.0) AppleWebKit/531.2.0 (KHTML, like Gecko) Chrome/16.0.836.0 Safari/531.2.0", "Mozilla/5.0 (Windows; U; Windows NT 5.0) AppleWebKit/534.0.2 (KHTML, like Gecko) Chrome/19.0.818.0 Safari/534.0.2", "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_10_5 rv:6.0; LA) AppleWebKit/531.1.2 (KHTML, like Gecko) Version/6.1.0 Safari/531.1.2", "Mozilla/5.0 (Windows; U; Windows NT 5.2) AppleWebKit/536.0.2 (KHTML, like Gecko) Chrome/14.0.840.0 Safari/536.0.2", "Mozilla/5.0 (Windows; U; Windows NT 5.0) AppleWebKit/531.1.1 (KHTML, like Gecko) Chrome/21.0.896.0 Safari/531.1.1", "Mozilla/5.0 (compatible; MSIE 8.0; Windows NT 6.3; Trident/6.1; .NET CLR 4.3.24127.9)", "Mozilla/5.0 (Windows; U; Windows NT 6.3) AppleWebKit/531.0.1 (KHTML, like Gecko) Chrome/15.0.885.0 Safari/531.0.1", "Mozilla/5.0 (Windows; U; Windows NT 5.0) AppleWebKit/533.0.1 (KHTML, like Gecko) Chrome/27.0.892.0 Safari/533.0.1", "Mozilla/5.0 (Windows; U; Windows NT 6.0) AppleWebKit/536.1.0 (KHTML, like Gecko) Chrome/20.0.805.0 Safari/536.1.0", "Mozilla/5.0 (Windows; U; Windows NT 6.0) AppleWebKit/533.0.1 (KHTML, like Gecko) Chrome/17.0.857.0 Safari/533.0.1");

    private String cleanURL(String url) throws InvalidURLException {

        final int PRODUCT_ID_INDEX = 5;
        final int REGION_INDEX = 2;

        try {
            List<String> urlParts = List.of(url.split("\\."));
            String region = urlParts.get(REGION_INDEX);

            urlParts = List.of(url.split("/"));
            String productId = urlParts.get(PRODUCT_ID_INDEX);
            return "https://www.amazon." + region + "/product-reviews/" + productId;
        } catch (Exception e) {
            throw new InvalidURLException("Invalid URL");
        }

    }

    @Override
    public List<String> scrap(String url) throws InvalidURLException {

        final int MAX_REVIEWS = 10;

        url = cleanURL(url);

        StringBuilder str = new StringBuilder(url + "?pageNumber=");

        List<String> reviews = new ArrayList<>();

//        This will also mark the page number we are currently on
        int numberOfIterations = 1;

        while (true) {
            int randomIndex = ThreadLocalRandom.current().nextInt(userAgents.size());
            String randomUserAgent = userAgents.get(randomIndex);

            FirefoxOptions options = new FirefoxOptions();
            options.addPreference("general.useragent.override", randomUserAgent);
            WebDriver driver = null;

            try {
                driver = new FirefoxDriver(options);
                driver.get(str.toString() + numberOfIterations);

                List<WebElement> reviewElements = driver.findElements(By.cssSelector("span[data-hook='review-body']"));
                reviews.addAll(reviewElements.stream().map(WebElement::getText).toList());

                if (reviews.size() == MAX_REVIEWS) break;

                try {
                    driver.findElement(By.cssSelector("li[class='a-last']"));
                } catch (NoSuchElementException e) {
                    break;
                } finally {
                    driver.quit();
                    numberOfIterations++;
                }
            } catch (Exception e) {
                System.out.println("Error in scrapping reviews");
            } finally {
                assert driver != null;
                driver.quit();
            }
        }

        return reviews;
    }
}
