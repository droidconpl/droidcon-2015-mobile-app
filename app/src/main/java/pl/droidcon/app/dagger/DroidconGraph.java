package pl.droidcon.app.dagger;


import pl.droidcon.app.database.DatabaseManager;
import pl.droidcon.app.model.common.Slot;
import pl.droidcon.app.reminder.ReminderPersistenceImpl;
import pl.droidcon.app.reminder.SessionReminderImpl;
import pl.droidcon.app.rx.DataSubscription;
import pl.droidcon.app.ui.activity.MainActivity;
import pl.droidcon.app.ui.activity.SessionActivity;
import pl.droidcon.app.ui.activity.SettingsActivity;
import pl.droidcon.app.ui.adapter.AgendaSessionViewHolder;
import pl.droidcon.app.ui.adapter.ScheduleViewHolder;
import pl.droidcon.app.ui.dialog.SessionChooserDialog;
import pl.droidcon.app.ui.fragment.schedule.ScheduleFragment;
import pl.droidcon.app.ui.fragment.schedule.ScheduleMainFragment;
import pl.droidcon.app.ui.fragment.agenda.AgendaFragment;

interface DroidconGraph {
    void inject(MainActivity mainActivity);

    void inject(DataSubscription dataSubscription);

    void inject(AgendaSessionViewHolder agendaSessionViewHolder);

    void inject(AgendaFragment agendaFragment);

    void inject(DatabaseManager databaseManager);

    void inject(ScheduleMainFragment scheduleMainFragment);

    void inject(ScheduleFragment scheduleFragment);

    void inject(Slot slot);

    void inject(SessionActivity sessionActivity);

    void inject(ScheduleViewHolder scheduleViewHolder);

    void inject(SessionChooserDialog sessionChooserDialog);

    void inject(SettingsActivity.PreferencesFragment preferencesFragment);

    void inject(SessionReminderImpl sessionReminder);

    void inject(ReminderPersistenceImpl reminderPersistence);
}
