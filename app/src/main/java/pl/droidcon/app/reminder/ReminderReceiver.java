package pl.droidcon.app.reminder;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.NotificationCompat;
import android.util.Log;

import pl.droidcon.app.R;
import pl.droidcon.app.dagger.DroidconInjector;
import pl.droidcon.app.model.api.Session;
import pl.droidcon.app.model.common.SessionNotification;
import pl.droidcon.app.ui.activity.SessionActivity;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;


public class ReminderReceiver extends BroadcastReceiver {

    private static final String TAG = ReminderReceiver.class.getSimpleName();

    private static final String SESSION_KEY = "session";

    public static Intent createReceiverIntent(Context context, Session session) {
        Intent intent = new Intent(context, ReminderReceiver.class);
        intent.putExtra(SESSION_KEY, session);
        return intent;
    }

    @Override
    public void onReceive(final Context context, Intent intent) {
        Log.d(TAG, "received");
        final Session session = (Session) intent.getExtras().get(SESSION_KEY);
        if (session == null) {
            Log.e(TAG, "Session received null");
            return;
        }

        DroidconInjector.get().databaseManager()
                .removeFromNotification(SessionNotification.of(session))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<Boolean>() {
                    @Override
                    public void call(Boolean aBoolean) {
                        Log.d(TAG, "Removed remind notification for session " + session.title);
                        if (aBoolean) {
                            showNotification(context, session);
                        }
                    }
                });
    }

    private void showNotification(Context context, Session session) {
        Intent sessionIntent = SessionActivity.getSessionIntent(context, session);
        PendingIntent pendingIntent = PendingIntent.getActivity(context, session.id, sessionIntent, PendingIntent.FLAG_UPDATE_CURRENT);

        NotificationCompat.Builder builder = new NotificationCompat.Builder(context);

        Notification notification = builder.setSmallIcon(R.mipmap.ic_launcher)
                .setContentTitle(context.getString(R.string.app_name))
                .setContentText(context.getString(R.string.received_session_notification, session.title))
                .setContentIntent(pendingIntent)
                .setDefaults(Notification.DEFAULT_ALL)
                .setAutoCancel(true)
                .build();

        NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(session.id, notification);
    }
}
