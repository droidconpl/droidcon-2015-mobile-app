package pl.droidcon.app.ui.fragment.agenda;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;
import pl.droidcon.app.R;
import pl.droidcon.app.dagger.DroidconInjector;
import pl.droidcon.app.model.api.AgendaAndSpeakersResponse;
import pl.droidcon.app.model.common.SessionDay;
import pl.droidcon.app.model.ui.SwipeRefreshColorSchema;
import pl.droidcon.app.rx.AgendaFragmentSubscription;
import pl.droidcon.app.ui.adapter.AgendaAdapter;
import pl.droidcon.app.ui.decoration.SpacesItemDecoration;
import pl.droidcon.app.wrapper.SnackbarWrapper;
import rx.functions.Action0;
import rx.functions.Action1;


public class AgendaFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener {

    private static final String SESSION_DAY_KEY = "sessionDay";

    @Bind(R.id.agenda_view)
    RecyclerView agendaList;

    @Bind(R.id.agenda_fragment_swipe_refresh_layout)
    SwipeRefreshLayout swipeRefreshLayout;

    @Inject
    SnackbarWrapper snackbarWrapper;
    @Inject
    SwipeRefreshColorSchema swipeRefreshColorSchema;

    private AgendaFragmentSubscription agendaFragmentSubscription;
    private SessionDay sessionDay;

    public static AgendaFragment newInstance(SessionDay sessionDay) {
        Bundle args = new Bundle();
        args.putSerializable(SESSION_DAY_KEY, sessionDay);
        AgendaFragment fragment = new AgendaFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle arguments = getArguments();
        sessionDay = (SessionDay) arguments.getSerializable(SESSION_DAY_KEY);
        agendaFragmentSubscription = new AgendaFragmentSubscription();
        DroidconInjector.get().inject(this);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.agenda_fragment, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);
        swipeRefreshLayout.setOnRefreshListener(this);
        swipeRefreshLayout.setColorSchemeColors(swipeRefreshColorSchema.getColors());
        agendaList.setHasFixedSize(true);
        LinearLayoutManager mLayoutManager = new GridLayoutManager(view.getContext(), 2);
        agendaList.setLayoutManager(mLayoutManager);
        agendaList.addItemDecoration(new SpacesItemDecoration(view.getContext().getResources().getDimension(R.dimen.list_element_margin)));
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        agendaFragmentSubscription.subscribe(sessionDay);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        agendaFragmentSubscription.unSubscribe();
    }

    @Override
    public void onResume() {
        super.onResume();
        setBinder();
    }

    @Override
    public void onPause() {
        super.onPause();
        agendaFragmentSubscription.bind(null, null);
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
    }

    public void update(List<AgendaAndSpeakersResponse.AgendaAndSpeakers> agendaResponse) {
        AgendaAdapter mAdapter = new AgendaAdapter(agendaResponse);
        agendaList.setAdapter(mAdapter);
        swipeRefreshLayout.setRefreshing(false);
    }

    public void onError() {
        if (getView() != null) {
            swipeRefreshLayout.setRefreshing(false);
            snackbarWrapper.showSnackbar(getView(), R.string.loading_error);
        }
    }

    @Override
    public void onRefresh() {
        swipeRefreshLayout.setRefreshing(true);
        agendaFragmentSubscription.refresh(sessionDay);
        setBinder();
    }


    private void setBinder() {
        agendaFragmentSubscription.bind(new Action1<List<AgendaAndSpeakersResponse.AgendaAndSpeakers>>() {
            @Override
            public void call(List<AgendaAndSpeakersResponse.AgendaAndSpeakers> agendaResponse) {
                update(agendaResponse);
            }
        }, new Action0() {
            @Override
            public void call() {
                onError();
            }
        });
    }
}
