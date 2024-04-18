package org.abhinavgpt.commenz.services.scrapping;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
final class AmazonScrapper implements Scrapper {

    String[] userAgents = {"Mozilla/5.0 (Windows; U; Windows NT 6.3) AppleWebKit/533.1.0 (KHTML, like Gecko) Chrome/25.0.891.0 Safari/533.1.0", "Mozilla/5.0 (compatible; MSIE 8.0; Windows NT 5.0; Trident/6.1; .NET CLR 2.9.84083.0)", "Mozilla/5.0 (compatible; MSIE 7.0; Windows NT 6.1; Trident/3.1; .NET CLR 4.0.98053.6)", "Mozilla/5.0 (Windows; U; Windows NT 5.1) AppleWebKit/536.0.1 (KHTML, like Gecko) Chrome/23.0.822.0 Safari/536.0.1", "Mozilla/5.0 (Windows; U; Windows NT 5.0) AppleWebKit/533.2.2 (KHTML, like Gecko) Chrome/39.0.841.0 Safari/533.2.2", "Mozilla/5.0 (compatible; MSIE 8.0; Windows NT 6.2; Trident/7.0; .NET CLR 2.9.76162.7)", "Mozilla/5.0 (Windows; U; Windows NT 5.1) AppleWebKit/534.1.2 (KHTML, like Gecko) Chrome/29.0.804.0 Safari/534.1.2", "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_8_1 rv:6.0; RM) AppleWebKit/536.2.0 (KHTML, like Gecko) Version/6.1.6 Safari/536.2.0", "Mozilla/5.0 (Windows; U; Windows NT 6.0) AppleWebKit/538.2.0 (KHTML, like Gecko) Chrome/32.0.824.0 Safari/538.2.0", "Mozilla/5.0 (Windows; U; Windows NT 6.0) AppleWebKit/531.2.0 (KHTML, like Gecko) Chrome/16.0.836.0 Safari/531.2.0", "Mozilla/5.0 (Windows; U; Windows NT 5.0) AppleWebKit/534.0.2 (KHTML, like Gecko) Chrome/19.0.818.0 Safari/534.0.2", "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_10_5 rv:6.0; LA) AppleWebKit/531.1.2 (KHTML, like Gecko) Version/6.1.0 Safari/531.1.2", "Mozilla/5.0 (Windows; U; Windows NT 5.2) AppleWebKit/536.0.2 (KHTML, like Gecko) Chrome/14.0.840.0 Safari/536.0.2", "Mozilla/5.0 (Windows; U; Windows NT 5.0) AppleWebKit/531.1.1 (KHTML, like Gecko) Chrome/21.0.896.0 Safari/531.1.1", "Mozilla/5.0 (compatible; MSIE 8.0; Windows NT 6.3; Trident/6.1; .NET CLR 4.3.24127.9)", "Mozilla/5.0 (Windows; U; Windows NT 6.3) AppleWebKit/531.0.1 (KHTML, like Gecko) Chrome/15.0.885.0 Safari/531.0.1", "Mozilla/5.0 (Windows; U; Windows NT 5.0) AppleWebKit/533.0.1 (KHTML, like Gecko) Chrome/27.0.892.0 Safari/533.0.1", "Mozilla/5.0 (Windows; U; Windows NT 6.0) AppleWebKit/536.1.0 (KHTML, like Gecko) Chrome/20.0.805.0 Safari/536.1.0", "Mozilla/5.0 (Windows; U; Windows NT 6.0) AppleWebKit/533.0.1 (KHTML, like Gecko) Chrome/17.0.857.0 Safari/533.0.1"};

    @Override
    public String cleaningURL(String url) {

        String[] urlParts = url.split("\\.");
        String region = urlParts[2].split("/")[0];

        String[] urlPartsT = url.split("/");
        String productId = urlPartsT[5];
        return "https://www.amazon." + region + "/product-reviews/" + productId;

    }

    @Override
    public List<String> scrap(String url) {
        url = cleaningURL(url);
        url += "?pageNumber=";
        StringBuilder str = new StringBuilder(url);
        List<String> reviews = new ArrayList<>();
        int i = 1;
        while (true) {
            FirefoxOptions options = new FirefoxOptions();

            options.addPreference("general.useragent.override", userAgents[(int) (Math.random() * userAgents.length)]);
            WebDriver driver = new FirefoxDriver(options);

            try {
                driver.get(str.toString() + i);

                List<WebElement> reviewElements = driver.findElements(By.cssSelector("span[data-hook='review-body']"));
                for (WebElement element : reviewElements) {
                    reviews.add(element.getText());
                }
                if (reviews.size() == 10) break;

//                To prevent infinite loop
                if (i == 5) break;

                WebElement nextButton = driver.findElement(By.cssSelector("li[class='a-last']"));
                if (nextButton == null) break;
                i++;
                driver.quit();
            } catch (Exception e) {
                e.printStackTrace();
                System.out.println("Error in scrapping reviews");
            } finally {
                driver.quit();
            }
        }

        return reviews;
    }
}
