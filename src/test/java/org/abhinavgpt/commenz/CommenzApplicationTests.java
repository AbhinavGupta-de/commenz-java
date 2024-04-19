package org.abhinavgpt.commenz;

import org.abhinavgpt.commenz.exceptions.InvalidURLException;
import org.abhinavgpt.commenz.services.scrapping.Scrapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class CommenzApplicationTests {

    @Autowired
    Scrapper scrapper;

    @Test
    void testScrap() {
        String url = "https://www.amazon.in/kalsdg;oiuajkjassdf/product-reviews/B0B5S2ZJ8H/ref=cm_cr_arp_d_viewopt_srt?ie=UTF8";
        List<String> reviews = null;
        try {
            reviews = scrapper.scrap(url);
        } catch (InvalidURLException e) {
            throw new RuntimeException(e);
        }
        System.out.println(reviews);
        assertEquals(10, reviews.size());
    }
}
