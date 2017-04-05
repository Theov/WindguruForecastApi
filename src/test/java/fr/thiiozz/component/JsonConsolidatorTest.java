package fr.thiiozz.component;

import fr.thiiozz.utils.ResourcesUtils;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.*;
import org.mockito.runners.MockitoJUnitRunner;
import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by thiiozz on 02/04/17.
 */
@RunWith(MockitoJUnitRunner.class)
public class JsonConsolidatorTest {
    @Spy
    private JsonConsolidator jsonConsolidator;

    @Before
    public void setUp() throws IOException, URISyntaxException {
        ResourcesUtils resourcesUtils = new ResourcesUtils();

        Map<String, String> mock = new HashMap<>();
        mock.put("forecast", resourcesUtils.getResourceFileAsString("/data/scrappedWindguruSample.txt"));
        mock.put("sunInfo", resourcesUtils.getResourceFileAsString("/data/windguruScrapSampleSun.txt"));

        jsonConsolidator.setData(mock);
    }

    @Test
    public void canConsolidateData(){
        assertTrue(jsonConsolidator.consolidateData().names().length() == 12);
    }
}
