package pl.droidcon.app.ui.dialog;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatDialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.joda.time.DateTime;

import java.util.List;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;
import pl.droidcon.app.R;
import pl.droidcon.app.dagger.DroidconInjector;
import pl.droidcon.app.database.DatabaseManager;
import pl.droidcon.app.helper.DateTimePrinter;
import pl.droidcon.app.model.api.Session;
import pl.droidcon.app.model.db.RealmSchedule;
import pl.droidcon.app.ui.view.SessionList;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;

public class SessionChooserDialog extends AppCompatDialogFragment implements SessionList.SessionClickListener {

    private static final String TAG = SessionChooserDialog.class.getSimpleName();

    private static final String SESSION_DATE_KEY = "session_date";

    public static SessionChooserDialog newInstance(DateTime sessionDate) {
        Bundle args = new Bundle();
        args.putSerializable(SESSION_DATE_KEY, sessionDate);
        SessionChooserDialog fragment = new SessionChooserDialog();
        fragment.setArguments(args);
        return fragment;
    }

    private DateTime sessionDate;
    private CompositeSubscription compositeSubscription;

    @Inject
    DatabaseManager databaseManager;

    @Bind(R.id.session_date)
    TextView sessionDateTextView;
    @Bind(R.id.sessions)
    SessionList sessionList;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(STYLE_NO_TITLE, getTheme());
        DroidconInjector.get().inject(this);
        sessionDate = (DateTime) getArguments().getSerializable(SESSION_DATE_KEY);
        compositeSubscription = new CompositeSubscription();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        compositeSubscription.clear();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.session_chooser_dialog, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        sessionDateTextView.setText(DateTimePrinter.toPrintableStringWithDay(sessionDate));
        getSessions();
    }

    private void getSessions() {
        Subscription subscribe = databaseManager.sessions(sessionDate)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<List<Session>>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(List<Session> sessions) {
                        sessionList.setSessions(sessions, SessionChooserDialog.this);
                    }
                });
        compositeSubscription.add(subscribe);
    }

    @Override
    public void onSessionClicked(Session session) {
        Subscription subscription = databaseManager.addToFavourite(session)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<RealmSchedule>() {
                    @Override
                    public void call(RealmSchedule realmSchedule) {
                        dismiss();
                    }
                });
        compositeSubscription.add(subscription);
    }
}
