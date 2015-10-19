package pl.droidcon.app.dagger;


import pl.droidcon.app.database.DatabaseManager;
import pl.droidcon.app.model.common.Slot;
import pl.droidcon.app.rx.DataSubscription;
import pl.droidcon.app.ui.activity.MainActivity;
import pl.droidcon.app.ui.adapter.SessionViewHolder;
import pl.droidcon.app.ui.fragment.schedule.ScheduleFragment;
import pl.droidcon.app.ui.fragment.schedule.ScheduleMainFragment;
import pl.droidcon.app.ui.fragment.agenda.AgendaFragment;

interface DroidconGraph {
    void inject(MainActivity mainActivity);

    void inject(DataSubscription dataSubscription);

    void inject(SessionViewHolder sessionViewHolder);

    void inject(AgendaFragment agendaFragment);

    void inject(DatabaseManager databaseManager);

    void inject(ScheduleMainFragment scheduleMainFragment);

    void inject(ScheduleFragment scheduleFragment);

    void inject(Slot slot);
}
