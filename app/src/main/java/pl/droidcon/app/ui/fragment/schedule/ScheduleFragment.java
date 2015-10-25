package pl.droidcon.app.ui.fragment.schedule;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;
import pl.droidcon.app.R;
import pl.droidcon.app.dagger.DroidconInjector;
import pl.droidcon.app.database.DatabaseManager;
import pl.droidcon.app.factory.SlotFactory;
import pl.droidcon.app.model.common.SessionDay;
import pl.droidcon.app.ui.adapter.ScheduleAdapter;
import pl.droidcon.app.ui.adapter.ScheduleViewHolder;
import pl.droidcon.app.ui.decoration.ScheduleItemDecoration;
import rx.subscriptions.CompositeSubscription;

public class ScheduleFragment extends Fragment implements ScheduleViewHolder.ScheduleClickListener {

    private static final String TAG = ScheduleFragment.class.getSimpleName();

    private static final String SESSION_DAY_KEY = "sessionDay";
    private ScheduleAdapter scheduleAdapter;

    public static ScheduleFragment newInstance(SessionDay sessionDay) {
        Bundle args = new Bundle();
        args.putSerializable(SESSION_DAY_KEY, sessionDay);
        ScheduleFragment fragment = new ScheduleFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Bind(R.id.schedule_list)
    RecyclerView scheduleList;

    @Inject
    DatabaseManager databaseManager;

    private CompositeSubscription compositeSubscription;
    private SessionDay sessionDay;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        sessionDay = (SessionDay) getArguments().getSerializable(SESSION_DAY_KEY);
        DroidconInjector.get().inject(this);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.schedule_fragment, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);
        scheduleList.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new GridLayoutManager(getContext(), 1);
        scheduleList.setLayoutManager(linearLayoutManager);
        scheduleList.addItemDecoration(new ScheduleItemDecoration(view.getContext().getResources().getDimension(R.dimen.list_element_margin)));
        scheduleAdapter = new ScheduleAdapter(SlotFactory.createSlotsForDay(sessionDay), this);
        scheduleList.setAdapter(scheduleAdapter);
    }

    @Override
    public void onResume() {
        super.onResume();
        compositeSubscription = new CompositeSubscription();
    }

    @Override
    public void onPause() {
        super.onPause();
        compositeSubscription.clear();
    }

    @Override
    public void onScheduleClicked(View view, int position) {
        //todo: show selected slot if already set or maybe show proposition about filling with available data?
    }
}
