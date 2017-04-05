package fr.thiiozz.component;

import org.junit.Before;
import org.junit.Test;

import java.util.Map;

import static org.junit.Assert.assertTrue;

/**
 * Created by thiiozz on 02/04/17.
 */
public class WindguruScrapperTest {
    private WindguruScrapper scrapper;

    @Before
    public void setUp(){
            scrapper = new WindguruScrapper();
    }

    @Test
    public void canExtractWGJsonObject(){
        Map<String, String> output = scrapper.scrapSpot("231");

        assertTrue(output.size() == 2);
        assertTrue(output.containsKey("forecast") && !output.get("forecast").isEmpty());
        assertTrue(output.containsKey("sunInfo") && !output.get("sunInfo").isEmpty());
    }
}
