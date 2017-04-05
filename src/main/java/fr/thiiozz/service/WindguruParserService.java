package fr.thiiozz.service;

import fr.thiiozz.component.ForecastConstructor;
import fr.thiiozz.component.JsonConsolidator;
import fr.thiiozz.component.WindguruScrapper;
import fr.thiiozz.model.Forecast;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by thiiozz on 30/03/17.
 */
@Service
public class WindguruParserService {
    @Autowired
    private WindguruScrapper windguruScrapper;

    @Autowired
    private JsonConsolidator jsonConsolidator;

    @Autowired
    private ForecastConstructor forecastConstructor;

    public List<Forecast> scrapPost(String spotId){
        jsonConsolidator.setData(windguruScrapper.scrapSpot(spotId));
        return forecastConstructor.constructForecast(jsonConsolidator.consolidateData());
    }
}