package fr.thiiozz.component;

import fr.thiiozz.model.Forecast;
import fr.thiiozz.utils.ResourcesUtils;
import org.json.JSONObject;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Spy;
import org.mockito.runners.MockitoJUnitRunner;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertTrue;

/**
 * Created by thiiozz on 04/04/17.
 */
@RunWith(MockitoJUnitRunner.class)
public class ForecastConstructorTest {
    @Spy
    private JsonConsolidator jsonConsolidator;

    @Spy
    private ForecastConstructor forecastConstructor;

    @Before
    public void setUp() throws IOException, URISyntaxException {
        ResourcesUtils resourcesUtils = new ResourcesUtils();

        Map<String, String> mock = new HashMap<>();
        mock.put("forecast", resourcesUtils.getResourceFileAsString("/data/scrappedWindguruSample.txt"));
        mock.put("sunInfo", resourcesUtils.getResourceFileAsString("/data/windguruScrapSampleSun.txt"));

        jsonConsolidator.setData(mock);
    }

    @Test
    public void canConstructCompleteForecast(){
        JSONObject json = jsonConsolidator.consolidateData();
        List<Forecast> forecast = forecastConstructor.constructForecast(json);

        assertTrue(forecast.size() == ForecastConstructor.numberOfElementsInForecast);
    }
}
