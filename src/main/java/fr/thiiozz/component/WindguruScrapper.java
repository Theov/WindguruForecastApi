package fr.thiiozz.component;

import fr.thiiozz.utils.RegexUtils;
import fr.thiiozz.utils.WindguruUrlBuilder;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by thiiozz on 02/04/17.
 */
@Component
public class WindguruScrapper {
    private static final String PATTERN_FORECAST = "\\{\"initstamp.*.\\}\\}";
    private static final String PATTERN_SUN_INFO = "sunrise.*.,\"tz\"";

    private Document doc = null;
    private String actualString = "";

    public Map<String, String> scrapSpot(String spotId){
        getDocument(spotId);
        actualString = findDataInScriptTagNodes();
        actualString = extractJsonObject();

        return reduceJsonObjectToUsefulFields();
    }

    private void getDocument(String spotId) {
        try {
            doc = Jsoup.connect(WindguruUrlBuilder.constructUrlForSpot(spotId)).get();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String findDataInScriptTagNodes() {
        String out = "";

        if(doc != null){
            Elements scriptElements = doc.getElementsByTag("script");

            // Here we assume than a line is a json object if it contains both opening and closing bracket
            out = scriptElements.stream()
                    .map(Element::data)
                    .filter(s -> s.contains("wg_fcst_tab_data_1"))
                    .filter(s -> s.contains("{") && s.contains("}"))
                    .reduce((ps,ns) -> ps.concat(" ").concat(ns))
                    .orElse("");
        }

        return out;
    }

    private String extractJsonObject() {
        String out = Arrays.stream(actualString.split(" "))
                .filter(s -> s.contains("{"))
                .reduce(String::concat)
                .orElse("");

        return out;
    }

    private Map<String, String> reduceJsonObjectToUsefulFields(){
        Map<String, String> out = new HashMap<>();

        String forecastString = RegexUtils.extractFromRegex(actualString, PATTERN_FORECAST);
        String sunInfoString = RegexUtils.extractFromRegex(actualString, PATTERN_SUN_INFO);

        if(sunInfoString.length() > 5)
            sunInfoString = sunInfoString.substring(0, sunInfoString.length() - 5);

        out.put("forecast", forecastString);
        out.put("sunInfo", sunInfoString);

        return out;
    }
}
