package fr.thiiozz.component;

import fr.thiiozz.model.Forecast;
import fr.thiiozz.utils.DateUtils;
import org.json.JSONObject;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by thiiozz on 04/04/17.
 */
@Component
public class ForecastConstructor {
    public static final int numberOfElementsInForecast = 60;

    private static final String PERIOD_KEY = "PERPW";
    private static final String WIND_SPEED_KEY = "WINDSPD";
    private static final String SWELL_DIR_KEY = "SWDIR1";
    private static final String TEMP_KEY = "TMPE";
    private static final String WIND_DIR_KEY = "WINDDIR";
    private static final String WIND_GUST_DIR_KEY = "GUST";
    private static final String SWELL_HEIGHT_KEY = "SWELL1";
    private static final String INIT_STAMP_KEY = "initstamp";
    private static final String SUNSET_KEY = "sunset";
    private static final String SUNRISE_KEY = "sunrise";

    private List<Forecast> forecast;
    private JSONObject rawJsonObject;

    private Integer lastCalculatedTimeStamp;

    public List<Forecast> constructForecast(JSONObject json) {
        forecast = new ArrayList<>();
        rawJsonObject = json;

        fillForecastList();

        return forecast;
    }

    private void fillForecastList() {
        for(int i = 0; i < numberOfElementsInForecast; i++){
            Forecast fcst = new Forecast();

            Double period = rawJsonObject.getJSONArray(PERIOD_KEY).getDouble(i);
            Double windSpeed = rawJsonObject.getJSONArray(WIND_SPEED_KEY).getDouble(i);
            Double windGustSpeed = rawJsonObject.getJSONArray(WIND_GUST_DIR_KEY).getDouble(i);
            Integer swellDir = rawJsonObject.getJSONArray(SWELL_DIR_KEY).getInt(i);
            Double temp = rawJsonObject.getJSONArray(TEMP_KEY).getDouble(i);
            Integer windDir = rawJsonObject.getJSONArray(WIND_DIR_KEY).getInt(i);
            Double swellHeight = rawJsonObject.getJSONArray(SWELL_HEIGHT_KEY).getDouble(i);

            fcst.setSwellHeight(swellHeight);
            fcst.setSwellDir(swellDir);
            fcst.setSwellPeriod(period);
            fcst.setWindSpeed(windSpeed);
            fcst.setWindGustSpeed(windSpeed);
            fcst.setWindDir(windDir);
            fcst.setTemp(temp);
            fcst.setInitTimeStamp(calculateTimeStampForIndex(i));
            fcst.setSunsetTimeStamp(DateUtils.calculateTimeStampWithTime(fcst.getInitTimeStamp(), rawJsonObject.getString(SUNSET_KEY)));
            fcst.setSunriseTimeStamp(DateUtils.calculateTimeStampWithTime(fcst.getInitTimeStamp(), rawJsonObject.getString(SUNRISE_KEY)));

            forecast.add(fcst);
        }
    }

    private Integer calculateTimeStampForIndex(int currentIndex) {
        if(!isItsFirstDateTime(currentIndex)){
            calculateNextTimeStamp();
        }else{
            lastCalculatedTimeStamp = rawJsonObject.getInt(INIT_STAMP_KEY);
        }

        return lastCalculatedTimeStamp;
    }

    private boolean isItsFirstDateTime(int currentIndex) {
        return currentIndex == 0;
    }

    private void calculateNextTimeStamp() {
        lastCalculatedTimeStamp += DateUtils.getNHoursInSeconds(3);

        LocalDateTime dateOfForecast = DateUtils.getLocalDateTimeFromTimeStamp(lastCalculatedTimeStamp);

        if(isDateTimePassWindguruDayLimit(dateOfForecast)){
            lastCalculatedTimeStamp += DateUtils.getNHoursInSeconds(6);
        }
    }

    private boolean isDateTimePassWindguruDayLimit(LocalDateTime dateOfForecast) {
        return dateOfForecast.getHour() > 20;
    }
}
