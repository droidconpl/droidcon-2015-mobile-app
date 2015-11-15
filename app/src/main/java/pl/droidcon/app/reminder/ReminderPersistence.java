package pl.droidcon.app.reminder;

import android.support.annotation.NonNull;

import pl.droidcon.app.model.api.Session;

public interface ReminderPersistence {
    boolean isReminding();

    void setReminding(boolean reminding);

    void addSessionToReminding(@NonNull Session session);

    void removeSessionFromReminding(@NonNull Session session);
}
