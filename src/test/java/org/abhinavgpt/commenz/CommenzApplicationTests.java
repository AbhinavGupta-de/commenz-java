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
        String url = "https://www.amazon.in/ASUS-Vivobook-i5-12500H-Laptop-X1502ZA-EJ541WS/dp/B0C7H6SSC8/ref=sr_1_6?dib=eyJ2IjoiMSJ9.QcYNCkMm5x4aEi9vaSnBerDgu4hjly3FkdbRQlX5hqoCCAJPiztHw2JmHoUiMqb578MGZh9bzmoStqvP40o-ixDVzSTNHeZcM3Z5pFFZ2jcRnVHI6Xd7PrasCkwOIILv0qbjsRsccvUuNYg-yng94qSVwa865ElP3KK4gGJJqh_BVmeedjBR50ztRWdhaq6VHFI9tj3Kx-zSedmdm5z33tzNX-DNXdADEAIZgrE5_cI.wuKVVRd_B7EGur1bXWuk19tHshu8-BoRrHUBHo9trcc&dib_tag=se&keywords=asus+vivobook&qid=1713541426&sr=8-6";
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
