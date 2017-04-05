package fr.thiiozz.utils;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.util.TimeZone;

/**
 * Created by thiiozz on 04/04/17.
 */
public class DateUtils {
    public static LocalDateTime getLocalDateTimeFromTimeStamp(Integer timeStampToConvert){
        return  LocalDateTime.ofInstant(Instant.ofEpochSecond(timeStampToConvert), TimeZone.getDefault().toZoneId());
    }

    public static Integer getNHoursInSeconds(int numberOfHoursToAdd) {
        return 3600 * numberOfHoursToAdd;
    }

    private static Integer LocalDateTimeToTimeStamp(LocalDateTime dateTimeToConvert){
        Instant instant = Instant.now();
        ZoneId systemZone = ZoneId.systemDefault();
        ZoneOffset currentOffsetForMyZone = systemZone.getRules().getOffset(instant);

        return Long.valueOf(dateTimeToConvert.toInstant(currentOffsetForMyZone).getEpochSecond()).intValue();
    }

    public static Integer calculateTimeStampWithTime(Integer initTimeStamp, String timeString) {
        LocalDateTime dateTime = getLocalDateTimeFromTimeStamp(initTimeStamp);
        LocalDateTime transformedDateTime = LocalDateTime.of(dateTime.getYear(), dateTime.getMonth(), dateTime.getDayOfMonth(),
                getHourFromString(timeString), getMinutesFromString(timeString), 00);

        return LocalDateTimeToTimeStamp(transformedDateTime);
    }

    private static Integer getHourFromString(String timeString){
        return getPieceOfTimeStringAsInteger(timeString, 0);
    }


    private static Integer getMinutesFromString(String timeString){
        return getPieceOfTimeStringAsInteger(timeString, 1);
    }

    private static Integer getPieceOfTimeStringAsInteger(String timeString, int pieceIndex) {
        Integer output = 00;

        String[] splitedTimeString = timeString.split(":");

        if(splitedTimeString.length >= 2){
                output = Integer.valueOf(splitedTimeString[pieceIndex].trim());
        }

        return  output;
    }


}
