package com.example.chaiy.popularmovies.helper;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Created by Chaiy on 7/23/2016.
 */
public final class DateUtils {

    public static final String FORMAT_YYYY_MM_DD = "yyyy-MM-dd";
    public static final String FORMAT_DD_MMM_YYYY = "dd-MMM-yyyy";

    private DateUtils() {

    }


    public static String formatDate(String dateInputString, String inputFormat, String outputFormat) {

        String dateOutputString = "";
        SimpleDateFormat formatter = new SimpleDateFormat(inputFormat, Locale.US);
        SimpleDateFormat targetFormat = new SimpleDateFormat(outputFormat, Locale.ENGLISH);
        try {
            Date date = formatter.parse(dateInputString);
            dateOutputString = targetFormat.format(date);
        } catch (ParseException e) {
            e.printStackTrace();
            dateOutputString = dateInputString;
        }

        return dateOutputString;
    }

}
