package pl.droidcon.app.reminder;


import android.support.annotation.NonNull;

import javax.inject.Inject;

import pl.droidcon.app.dagger.DroidconInjector;
import pl.droidcon.app.model.api.Session;

public class SessionReminderImpl implements SessionReminder {

    private static final String TAG = SessionReminderImpl.class.getSimpleName();

    @Inject
    ReminderPersistence persistence;

    @Inject
    Reminder reminder;

    public SessionReminderImpl() {
        DroidconInjector.get().inject(this);
    }

    @Override
    public boolean isReminding() {
        return persistence.isReminding();
    }

    @Override
    public void setReminding(boolean reminding) {
        persistence.setReminding(reminding);
    }

    @Override
    public void addSessionToReminding(@NonNull Session session) {
        persistence.addSessionToReminding(session);
        if (isReminding()) {
            reminder.setRemind(session);
        }

    }

    @Override
    public void removeSessionFromReminding(@NonNull Session session) {
        persistence.removeSessionFromReminding(session);
        reminder.removeRemind(session);
    }
}
