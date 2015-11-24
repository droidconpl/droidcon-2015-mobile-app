package pl.droidcon.app.helper;


import android.support.annotation.NonNull;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import java.util.Locale;

public final class DateTimePrinter {

    private DateTimePrinter() {

    }

    private static final String LOCAL_TIME_PRINT_FORMAT = "HH:mm";
    private static final DateTimeFormatter LOCAL_TIME_FORMATTER =
            DateTimeFormat.forPattern(LOCAL_TIME_PRINT_FORMAT).withLocale(Locale.ENGLISH);

    private static final String DATE_PATTERN = "EE HH:mm";
    private static final DateTimeFormatter DATE_TIME_FORMATTER =
            DateTimeFormat.forPattern(DATE_PATTERN).withLocale(Locale.ENGLISH);

    private static final String DAY_PATTERN = "EE dd.MM";
    private static final DateTimeFormatter DAY_FORMATTER =
            DateTimeFormat.forPattern(DAY_PATTERN).withLocale(Locale.ENGLISH);

    @NonNull
    public static String toPrintableString(@NonNull DateTime dateTime) {
        return LOCAL_TIME_FORMATTER.print(dateTime);
    }

    @NonNull
    public static String toPrintableStringWithDay(@NonNull DateTime dateTime) {
        return dateTime.toString(DATE_TIME_FORMATTER);
    }

    @NonNull
    public static String toPrintableDay(@NonNull DateTime dateTime){
        return dateTime.toString(DAY_FORMATTER);
    }

}
