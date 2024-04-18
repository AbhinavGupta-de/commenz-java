package org.abhinavgpt.commenz;

import org.abhinavgpt.commenz.services.scrapping.Scrapper;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class CommenzApplicationTests {

    @Autowired
    Scrapper scrapper;

    @Test
    void testCleaningURL() {
        String url = "https://www.amazon.in/Apple-iPhone-11-64GB-Black/dp/B07XVMJF2D/ref=sr_1_1?dchild=1&keywords=iphone+11&qid=1616820734&sr=8-1";
        String cleanedURL = scrapper.cleaningURL(url);
        assertEquals("https://www.amazon.in/product-reviews/B07XVMJF2D", cleanedURL);

    }

    @Test
    void testScrap() {
        String url = "https://www.amazon.in/kalsdg;oiuajkjassdf/product-reviews/B0B5S2ZJ8H/ref=cm_cr_arp_d_viewopt_srt?ie=UTF8";
        var reviews = scrapper.scrap(url);
        System.out.println(reviews);
        assertEquals(10, reviews.size());
    }
}
