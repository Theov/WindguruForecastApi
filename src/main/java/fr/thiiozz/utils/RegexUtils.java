package fr.thiiozz.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by thiiozz on 30/03/17.
 */
public class RegexUtils {
    public static String extractFromRegex(String stringToFormat, String patternToApply) {
        Pattern p = Pattern.compile(patternToApply);
        Matcher m = p.matcher(stringToFormat);

        String s = "";

        while (m.find()) {
            s = m.group(0);

        }

        return s;
    }
}
