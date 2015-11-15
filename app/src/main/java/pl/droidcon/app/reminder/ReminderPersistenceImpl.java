package pl.droidcon.app.reminder;


import android.content.Context;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;

import pl.droidcon.app.model.api.Session;

public class ReminderPersistenceImpl implements ReminderPersistence {
    private static final String SHARED_PREFERENCES_NAME = "reminder";
    private static final String REMINDING_KEY = "reminding";

    private SharedPreferences sharedPreferences;

    public ReminderPersistenceImpl(Context context) {
        sharedPreferences = context.getSharedPreferences(SHARED_PREFERENCES_NAME, Context.MODE_PRIVATE);
    }

    @Override
    public boolean isReminding() {
        return sharedPreferences.getBoolean(REMINDING_KEY, true);
    }

    @Override
    public void setReminding(boolean reminding) {
        sharedPreferences.edit()
                .putBoolean(REMINDING_KEY, reminding)
                .apply();
    }

    @Override
    public void addSessionToReminding(@NonNull Session session) {

    }

    @Override
    public void removeSessionFromReminding(@NonNull Session session) {

    }
}
