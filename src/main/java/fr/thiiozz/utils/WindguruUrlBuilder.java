package fr.thiiozz.utils;

/**
 * Created by thiiozz on 30/03/17.
 */
public class WindguruUrlBuilder {
    private static final String BASE_URL = "https://www.windguru.cz/";

    public static String constructUrlForSpot(String spotId){
        return BASE_URL.concat(spotId);
    }
}
