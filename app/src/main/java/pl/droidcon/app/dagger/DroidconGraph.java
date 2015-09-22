package pl.droidcon.app.dagger;


import pl.droidcon.app.rx.AgendaFragmentSubscription;
import pl.droidcon.app.ui.activity.MainActivity;

interface DroidconGraph {
    void inject(MainActivity mainActivity);

    void inject(AgendaFragmentSubscription agendaFragmentSubscription);
}
