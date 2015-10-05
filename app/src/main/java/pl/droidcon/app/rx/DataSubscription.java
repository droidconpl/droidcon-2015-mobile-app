package pl.droidcon.app.rx;


import android.util.Log;

import javax.inject.Inject;

import pl.droidcon.app.dagger.DroidconInjector;
import pl.droidcon.app.database.DatabaseManager;
import pl.droidcon.app.http.RestService;
import pl.droidcon.app.model.api.AgendaResponse;
import pl.droidcon.app.model.api.SpeakerResponse;
import pl.droidcon.app.model.event.NewDataEvent;
import rx.Observable;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action0;
import rx.functions.Action1;
import rx.functions.Func2;
import rx.schedulers.Schedulers;
import rx.subjects.BehaviorSubject;

public class DataSubscription {

    private static final String TAG = DataSubscription.class.getSimpleName();

    @Inject
    BinderUtil binderUtil;
    @Inject
    RestService restService;
    @Inject
    DatabaseManager databaseManager;

    private BehaviorSubject<NewDataEvent> newDataEventSubject = BehaviorSubject.create();

    public DataSubscription() {
        DroidconInjector.get().inject(this);
    }

    public void refresh() {
        newDataEventSubject = BehaviorSubject.create();
        fetchData();
    }


    public void fetchData() {
        Observable.zip(restService.getAgenda(), restService.getSpeakers(),
                new Func2<AgendaResponse, SpeakerResponse, NewDataEvent>() {
                    @Override
                    public NewDataEvent call(AgendaResponse agendaResponse, SpeakerResponse speakerResponse) {
                        Log.d(TAG, "Downloading and saving start.....");
                        databaseManager.saveData(agendaResponse, speakerResponse);
                        Log.d(TAG, "Downloading and saving end.....");
                        return new NewDataEvent();
                    }
                })
                .observeOn(Schedulers.io())
                .subscribeOn(AndroidSchedulers.mainThread())
                .subscribe(newDataEventSubject);
    }


    public Subscription bindNewDataEvent(Action1<NewDataEvent> onNext, Action0 onError) {
        return binderUtil.bindProperty(newDataEventSubject, onNext, onError);
    }

    public void unbind(Subscription subscription) {
        if (subscription != null) {
            binderUtil.unbind(subscription);
        }
    }
}
