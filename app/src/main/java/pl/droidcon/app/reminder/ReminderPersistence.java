package pl.droidcon.app.reminder;

import android.support.annotation.NonNull;

import pl.droidcon.app.model.api.Session;
import rx.Subscriber;

public interface ReminderPersistence {
    boolean isReminding();

    void setReminding(boolean reminding);

    void addSessionToReminding(@NonNull Session session);

    void removeSessionFromReminding(@NonNull Session session);

    void sessionsToRemind(final Subscriber<? super Session> topSubscriber);
}
