package com.edusalguero.rexoubador.application.datatransformer;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

public class DateConverter {

    public static String getFormatedDateOrEmptyString(Date date) {

        if (date != null) {
            TimeZone tz = TimeZone.getTimeZone("UTC");
            DateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm'Z'");
            df.setTimeZone(tz);
            return df.format(date);
        }
        return "";
    }
}
