package pl.droidcon.app.ui.fragment.agenda;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;
import pl.droidcon.app.R;
import pl.droidcon.app.dagger.DroidconInjector;
import pl.droidcon.app.database.DatabaseManager;
import pl.droidcon.app.model.api.Session;
import pl.droidcon.app.model.common.SessionDay;
import pl.droidcon.app.model.event.NewDataEvent;
import pl.droidcon.app.model.ui.SwipeRefreshColorSchema;
import pl.droidcon.app.rx.DataSubscription;
import pl.droidcon.app.ui.activity.SessionActivity;
import pl.droidcon.app.ui.adapter.AgendaAdapter;
import pl.droidcon.app.ui.decoration.SpacesItemDecoration;
import pl.droidcon.app.ui.view.RecyclerItemClickListener;
import pl.droidcon.app.wrapper.SnackbarWrapper;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action0;
import rx.functions.Action1;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;


public class AgendaFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener, RecyclerItemClickListener.OnItemClickListener {

    private static final String TAG = AgendaFragment.class.getSimpleName();
    private static final String SESSION_DAY_KEY = "sessionDay";

    @Bind(R.id.agenda_view)
    RecyclerView agendaList;

    @Bind(R.id.agenda_fragment_swipe_refresh_layout)
    SwipeRefreshLayout swipeRefreshLayout;

    @Inject
    SnackbarWrapper snackbarWrapper;
    @Inject
    SwipeRefreshColorSchema swipeRefreshColorSchema;
    @Inject
    DataSubscription dataSubscription;
    @Inject
    DatabaseManager databaseManager;

    private SessionDay sessionDay;

    private Subscription newDataEventSubscription;
    private CompositeSubscription sessionCompositeSubscription;
    private AgendaAdapter agendaAdapter;

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
        GridLayoutManager mLayoutManager = new GridLayoutManager(view.getContext(), 2);
        mLayoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {
                //return 2 for single item as single item occupies all width
                return agendaAdapter.getSessionByPosition(position).singleItem ? 2 : 1;
            }
        });
        agendaList.setLayoutManager(mLayoutManager);
        agendaList.addItemDecoration(new SpacesItemDecoration(view.getContext().getResources().getDimension(R.dimen.list_element_margin)));
        agendaList.addOnItemTouchListener(new RecyclerItemClickListener(getContext(), this));
        bindNewDataEvent();
        sessionCompositeSubscription = new CompositeSubscription();
        getSessions();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        sessionCompositeSubscription.clear();
        dataSubscription.unbind(newDataEventSubscription);
    }

    public void showErrorSnackBar() {
        if (getView() != null) {
            swipeRefreshLayout.setRefreshing(false);
            snackbarWrapper.showSnackbar(getView(), R.string.loading_error);
        }
    }

    @Override
    public void onRefresh() {
        dataSubscription.refresh();
        bindNewDataEvent();
    }

    private void getSessions() {
        Subscription sessionSubscription = databaseManager.sessions(sessionDay)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<List<Session>>() {
                    @Override
                    public void onCompleted() {
                        Log.d(TAG, "onCompleted");
                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                        showErrorSnackBar();
                        swipeRefreshLayout.setRefreshing(false);
                    }

                    @Override
                    public void onNext(List<Session> sessions) {
                        swipeRefreshLayout.setRefreshing(false);
                        agendaAdapter = new AgendaAdapter(sessions);
                        agendaList.setAdapter(agendaAdapter);
                    }
                });
        if (sessionCompositeSubscription != null) {
            sessionCompositeSubscription.add(sessionSubscription);
        }
    }

    private void bindNewDataEvent() {
        newDataEventSubscription = dataSubscription.bindNewDataEvent(new Action1<NewDataEvent>() {
            @Override
            public void call(NewDataEvent newDataEvent) {
                Log.d(TAG, "newDataEvent=" + newDataEvent);
                getSessions();
            }
        }, new Action0() {
            @Override
            public void call() {
                handleError();
            }
        });
    }

    private void handleError() {
        Log.e(TAG, "handling error isRefreshing=" + swipeRefreshLayout.isRefreshing());
        if (swipeRefreshLayout.isRefreshing()) {
            showErrorSnackBar();
        }
        getSessions();
    }

    @Override
    public void onItemClick(View view, int position) {
        Session session = agendaAdapter.getSessionByPosition(position);
        SessionActivity.start(getContext(), session);
    }
}
