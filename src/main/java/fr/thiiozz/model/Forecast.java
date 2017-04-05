package fr.thiiozz.model;

import java.io.Serializable;

/**
 * Created by thiiozz on 04/04/17.
 */
public class Forecast implements Serializable{
    private Double swellHeight;
    private Integer swellDir;
    private Double swellPeriod;
    private Double windSpeed;
    private Integer windDir;
    private Double temp;
    private Integer initTimeStamp;
    private Integer sunsetTimeStamp;
    private Integer sunriseTimeStamp;
    private Double windGustSpeed;

    public Double getWindGustSpeed() {
        return windGustSpeed;
    }

    public Double getSwellHeight() {
        return swellHeight;
    }

    public Integer getSunsetTimeStamp() {
        return sunsetTimeStamp;
    }

    public Integer getSunriseTimeStamp() {
        return sunriseTimeStamp;
    }

    public Integer getSwellDir() {
        return swellDir;
    }

    public Double getSwellPeriod() {
        return swellPeriod;
    }

    public Double getWindSpeed() {
        return windSpeed;
    }

    public Integer getWindDir() {
        return windDir;
    }

    public Double getTemp() {
        return temp;
    }

    public Integer getInitTimeStamp() {
        return initTimeStamp;
    }

    public void setSwellHeight(Double swellHeight) {
        this.swellHeight = swellHeight;
    }

    public void setSwellDir(Integer swellDir) {
        this.swellDir = swellDir;
    }

    public void setSwellPeriod(Double swellPeriod) {
        this.swellPeriod = swellPeriod;
    }

    public void setWindSpeed(Double windSpeed) {
        this.windSpeed = windSpeed;
    }

    public void setWindDir(Integer windDir) {
        this.windDir = windDir;
    }

    public void setTemp(Double temp) {
        this.temp = temp;
    }

    public void setInitTimeStamp(Integer initTimeStamp) {
        this.initTimeStamp = initTimeStamp;
    }

    public void setSunsetTimeStamp(Integer sunsetTimeStamp) {
        this.sunsetTimeStamp = sunsetTimeStamp;
    }

    public void setSunriseTimeStamp(Integer sunriseTimeStamp) {
        this.sunriseTimeStamp = sunriseTimeStamp;
    }

    public void setWindGustSpeed(Double windGustSpeed) {
        this.windGustSpeed = windGustSpeed;
    }
}
