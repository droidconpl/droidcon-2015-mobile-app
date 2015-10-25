package pl.droidcon.app.model.common;


import android.content.res.Resources;
import android.support.annotation.NonNull;
import android.support.annotation.StringRes;

import org.joda.time.DateTime;

import javax.inject.Inject;

import pl.droidcon.app.R;
import pl.droidcon.app.dagger.DroidconInjector;
import pl.droidcon.app.model.api.Session;

public class Slot {

    public enum Type {
        REGISTRATION(R.string.registration),
        OPENING_1_DAY(R.string.opening_first_day),
        CLOSING_1_DAY(R.string.closing_first_day),
        OPENING_2_DAY(R.string.opening_second_day),
        CLOSING_2_DAY(R.string.closing_second_day),
        SESSION(R.string.session),
        COFFEE_BREAK(R.string.coffee_break),
        BARCAMP(R.string.barcamp),
        LUNCH_BREAK(R.string.lunch_break);

        @StringRes
        private int description;

        Type(@StringRes int description) {
            this.description = description;
        }

        @StringRes
        public int getDescription() {
            return description;
        }
    }

    private String title;
    private DateTime dateTime;
    private Session session;
    private Type slotType;

    public static Slot ofType(Type type, SessionDay sessionDay, int hour, int minutes) {
        Slot slot = new Slot();
        slot.setType(type);
        slot.setTitle(type.getDescription());
        DateTime actualDate = sessionDay.when.withHourOfDay(hour).withMinuteOfHour(minutes);
        slot.setDateTime(actualDate);
        return slot;
    }

    @Inject
    Resources resources;

    private Slot() {
        DroidconInjector.get().inject(this);
    }

    void setDateTime(DateTime dateTime) {
        this.dateTime = dateTime;
    }

    void setType(Type type) {
        this.slotType = type;
    }

    void setTitle(@StringRes int titleStringRes) {
        this.title = resources.getString(titleStringRes);
    }


    public void attachSession(@NonNull Session session){
        this.session = session;
    }

    public DateTime getDateTime() {
        return dateTime;
    }

    public Session getSession() {
        return session;
    }

    public Type getSlotType() {
        return slotType;
    }

    public String getDisplayTitle() {
        if (session != null) {
            return session.title;
        }
        return title;
    }
}
