package pl.droidcon.app.helper;


import android.support.annotation.NonNull;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

public final class DateTimePrinter {

    private DateTimePrinter() {

    }

    private static final String LOCAL_TIME_PRINT_FORMAT = "HH:mm";

    private static final DateTimeFormatter LOCAL_TIME_FORMATTER =
            DateTimeFormat.forPattern(LOCAL_TIME_PRINT_FORMAT);

    @NonNull
    public static String toPrintableString(@NonNull DateTime dateTime) {
        return LOCAL_TIME_FORMATTER.print(dateTime);
    }
}
