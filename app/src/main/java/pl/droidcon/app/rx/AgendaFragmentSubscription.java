package pl.droidcon.app.rx;


import javax.inject.Inject;

import pl.droidcon.app.dagger.DroidconInjector;
import pl.droidcon.app.http.RestService;
import pl.droidcon.app.model.api.AgendaAndSpeakersResponse;
import pl.droidcon.app.model.api.AgendaResponse;
import pl.droidcon.app.model.api.SpeakerResponse;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.functions.Func2;
import rx.schedulers.Schedulers;
import rx.subjects.BehaviorSubject;
import rx.subscriptions.CompositeSubscription;

public class AgendaFragmentSubscription {

    private CompositeSubscription compositeSubscription;

    private BinderUtil binderUtil;

    @Inject
    RestService restService;

    private BehaviorSubject<AgendaAndSpeakersResponse> agendaResponseBehaviorSubject = BehaviorSubject.create();

    public AgendaFragmentSubscription() {
        DroidconInjector.get().inject(this);
        binderUtil = new BinderUtil(this);
    }

    public final void subscribe() {
        unSubscribe();
        compositeSubscription = new CompositeSubscription();
        startSubscription();
    }


    private void startSubscription() {
        //todo: check database here

        Observable<AgendaResponse> agendaResponseObservable = restService.getAgenda();
        Observable<SpeakerResponse> speakerResponseObservable = restService.getSpeakers();

        Observable.zip(agendaResponseObservable, speakerResponseObservable, new Func2<AgendaResponse,
                SpeakerResponse,
                AgendaAndSpeakersResponse>() {

            @Override
            public AgendaAndSpeakersResponse call(AgendaResponse agendaResponse,
                                                  SpeakerResponse speakerResponse) {
                return new AgendaAndSpeakersResponse(agendaResponse, speakerResponse);
            }
        })
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(agendaResponseBehaviorSubject);

    }


    public void unSubscribe() {
        if (compositeSubscription != null) {
            compositeSubscription.clear();
            compositeSubscription = null;
        }
    }

    public void bind(Action1<AgendaAndSpeakersResponse> action1) {
        binderUtil.bindProperty(agendaResponseBehaviorSubject, action1);
    }

}
