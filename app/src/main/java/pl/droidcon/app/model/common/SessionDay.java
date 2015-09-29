package pl.droidcon.app.model.common;


import android.support.annotation.StringRes;

import org.joda.time.DateTime;

import pl.droidcon.app.R;

public enum SessionDay {

    DAY_ONE(R.string.day_one, DateTime.parse("2015-12-04")),
    DAY_TWO(R.string.day_two, DateTime.parse("2015-12-05"));

    @StringRes
    public int humanReadableDateStringId;

    public DateTime when;

    SessionDay(@StringRes int humanReadableDateStringId, DateTime when) {
        this.humanReadableDateStringId = humanReadableDateStringId;
        this.when = when;
    }
}
