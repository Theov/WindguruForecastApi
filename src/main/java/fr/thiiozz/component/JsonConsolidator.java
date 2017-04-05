package fr.thiiozz.component;

import org.json.JSONObject;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * Created by thiiozz on 02/04/17.
 */
@Component
public class JsonConsolidator {
    private JSONObject jsonObject = null;
    private Map<String, String> forecastBaseData;

    private static final String[] USELESS_OBJECT = {"SWELL2", "WVDIR", "FLHGT", "WVPER", "APCP", "hr_h", "WVHGT", "SWDIR2",
            "TCDC", "img_var_map", "hr_weekday", "FLGHT", "SMERN", "PCPT", "DIRPW", "LCDC", "SLP", "img_param", "HCDC", "hr_d",
            "hours", "SWPER2", "HTSGW", "SWPER1", "MCDC", "RH"};

    public void setData(Map<String, String> dataFromScrapper) {
        forecastBaseData = dataFromScrapper;
    }

    public JSONObject consolidateData() {
        initializeJsonObject();
        mergeData();
        removeUselessFields();

        return jsonObject;
    }

    private void initializeJsonObject() {
        jsonObject = new JSONObject(forecastBaseData.get("forecast"));
    }

    private void mergeData() {
        String[] jsonLine = forecastBaseData.get("sunInfo").split(",");

        for(String e : jsonLine){
            String[] jsonLineElements = e.split("\":\"");

            String key = normalizeJson(jsonLineElements[0]);
            String value = normalizeJson(jsonLineElements[1]);
            jsonObject.put(key, value);
        }
    }

    private void removeUselessFields() {
        for(String e : USELESS_OBJECT){
            jsonObject.remove(e);
        }
    }

    private String normalizeJson(String stringToNormalize) {
        return stringToNormalize.replaceAll("\"", "");
    }
}
