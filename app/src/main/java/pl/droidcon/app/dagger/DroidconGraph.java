package pl.droidcon.app.dagger;


import pl.droidcon.app.rx.AgendaFragmentSubscription;
import pl.droidcon.app.ui.activity.MainActivity;
import pl.droidcon.app.ui.adapter.SessionViewHolder;
import pl.droidcon.app.ui.fragment.agenda.AgendaFragment;

interface DroidconGraph {
    void inject(MainActivity mainActivity);

    void inject(AgendaFragmentSubscription agendaFragmentSubscription);

    void inject(SessionViewHolder sessionViewHolder);

    void inject(AgendaFragment agendaFragment);
}
