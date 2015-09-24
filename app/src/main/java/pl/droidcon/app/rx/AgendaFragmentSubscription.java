package pl.droidcon.app.rx;


import java.util.Collections;
import java.util.List;

import javax.inject.Inject;

import pl.droidcon.app.dagger.DroidconInjector;
import pl.droidcon.app.http.RestService;
import pl.droidcon.app.model.api.AgendaAndSpeakersResponse;
import pl.droidcon.app.model.api.AgendaResponse;
import pl.droidcon.app.model.api.SpeakerResponse;
import pl.droidcon.app.model.common.SessionDay;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action0;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.functions.Func2;
import rx.schedulers.Schedulers;
import rx.subjects.BehaviorSubject;
import rx.subscriptions.CompositeSubscription;

public class AgendaFragmentSubscription {

    private CompositeSubscription compositeSubscription;

    @Inject
    BinderUtil binderUtil;
    @Inject
    RestService restService;

    private BehaviorSubject<List<AgendaAndSpeakersResponse.AgendaAndSpeakers>> agendaResponseBehaviorSubject = BehaviorSubject.create();

    public AgendaFragmentSubscription() {
        DroidconInjector.get().inject(this);
    }

    public final void refresh(SessionDay sessionDay) {
        agendaResponseBehaviorSubject = BehaviorSubject.create();
        startSubscription(sessionDay);
    }

    public final void subscribe(SessionDay sessionDay) {
        unSubscribe();
        compositeSubscription = new CompositeSubscription();
        startSubscription(sessionDay);
    }


    private void startSubscription(final SessionDay sessionDay) {
        //todo: check database here

        Observable<AgendaResponse> agendaResponseObservable = restService.getAgenda();
        Observable<SpeakerResponse> speakerResponseObservable = restService.getSpeakers();

        Observable.zip(agendaResponseObservable, speakerResponseObservable, new Func2<AgendaResponse, SpeakerResponse, AgendaAndSpeakersResponse>() {
            @Override
            public AgendaAndSpeakersResponse call(AgendaResponse agendaResponse, SpeakerResponse speakerResponse) {
                return new AgendaAndSpeakersResponse(agendaResponse, speakerResponse, sessionDay.when);
            }
        })
                .map(new Func1<AgendaAndSpeakersResponse, List<AgendaAndSpeakersResponse.AgendaAndSpeakers>>() {
                    @Override
                    public List<AgendaAndSpeakersResponse.AgendaAndSpeakers> call(AgendaAndSpeakersResponse agendaAndSpeakersResponse) {
                        Collections.sort(agendaAndSpeakersResponse.agendaAndSpeakers);
                        return agendaAndSpeakersResponse.agendaAndSpeakers;
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
            binderUtil.clear();
        }
    }

    public void bind(Action1<List<AgendaAndSpeakersResponse.AgendaAndSpeakers>> action1, Action0 error) {
        binderUtil.bindProperty(agendaResponseBehaviorSubject, action1, error);
    }

}
