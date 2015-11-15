package pl.droidcon.app.reminder;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import pl.droidcon.app.dagger.DroidconInjector;


public class ReminderBootReceiver extends BroadcastReceiver {

    private static final String TAG = ReminderBootReceiver.class.getSimpleName();

    @Override
    public void onReceive(Context context, Intent intent) {
        Log.d(TAG, "Received boot");
        DroidconInjector.get().sessionReminder().restoreReminders();
    }
}
