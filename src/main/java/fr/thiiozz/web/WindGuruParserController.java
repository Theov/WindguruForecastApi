package fr.thiiozz.web;

import fr.thiiozz.model.Forecast;
import fr.thiiozz.service.WindguruParserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by thiiozz on 30/03/17.
 */
@RestController
@RequestMapping
public class WindGuruParserController {
    @Autowired
    private WindguruParserService windguruParserService;

    @RequestMapping("/forecast/spot/{spotId}")
    public List<Forecast> spotForecastFor(@PathVariable String spotId){
        return windguruParserService.scrapPost(spotId);
    }

    @RequestMapping("/")
    public String index(){
        return "It works!";
    }
}
