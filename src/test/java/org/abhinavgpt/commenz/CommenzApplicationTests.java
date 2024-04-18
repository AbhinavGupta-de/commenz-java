package org.abhinavgpt.commenz;

import org.abhinavgpt.commenz.services.scrapping.AmazonScrapper;
import org.abhinavgpt.commenz.services.scrapping.Scrapper;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class CommenzApplicationTests {

    @InjectMocks
    Scrapper scrapper = new AmazonScrapper();

//     Test the newly made cleaning url function
    @Test
    void testCleaningURL() {
        String url = "https://www.amazon.in/Apple-iPhone-11-64GB-Black/dp//ref=sr_1_1?dchild=1&keywords=iphone+11&qid=1616820734&sr=8-1";
        String cleanedURL = scrapper.cleaningURL(url);
        assertEquals("https://www.amazon.in/product-reviews/B07XVMJF2D", cleanedURL);

    }

//     Test the newly made scrap function
    @Test
    void testScrap() {
        String url = "https://www.amazon.in/kalsdg;oiuajkjassdf/product-reviews/B0B5S2ZJ8H/ref=cm_cr_arp_d_viewopt_srt?ie=UTF8";
        var reviews = scrapper.scrap(url);
        System.out.println(reviews);
        assertEquals(10, reviews.size());
    }
}
