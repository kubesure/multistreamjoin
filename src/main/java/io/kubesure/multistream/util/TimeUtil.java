package io.kubesure.multistream.util;

import java.util.Locale;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormatter;
import org.joda.time.format.ISODateTimeFormat;

public class TimeUtil {

    public static final DateTimeFormatter isoFormatter = ISODateTimeFormat.dateTime()
    .withLocale(Locale.getDefault());
    
    public static String ISOString(long time){
        return new DateTime(time).toString(isoFormatter);
    }
    
}