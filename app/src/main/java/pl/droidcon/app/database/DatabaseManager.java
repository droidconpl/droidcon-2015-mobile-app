package pl.droidcon.app.database;


import android.content.Context;
import android.util.Log;

import java.util.Date;
import java.util.List;

import javax.inject.Inject;

import io.realm.Realm;
import io.realm.RealmResults;
import pl.droidcon.app.dagger.DroidconInjector;
import pl.droidcon.app.helper.SessionMapper;
import pl.droidcon.app.helper.SpeakerMapper;
import pl.droidcon.app.model.api.AgendaResponse;
import pl.droidcon.app.model.api.Session;
import pl.droidcon.app.model.api.SpeakerResponse;
import pl.droidcon.app.model.common.SessionDay;
import pl.droidcon.app.model.db.RealmSession;
import pl.droidcon.app.model.db.RealmSpeaker;
import pl.droidcon.app.rx.RealmObservable;
import rx.Observable;
import rx.functions.Func1;

public class DatabaseManager {

    private static final String TAG = DatabaseManager.class.getSimpleName();


    @Inject
    Context context;
    @Inject
    SpeakerMapper speakerMapper;
    @Inject
    SessionMapper sessionMapper;

    public DatabaseManager() {
        DroidconInjector.get().inject(this);
    }


    public Observable<List<Session>> sessions(final SessionDay sessionDay) {
        return RealmObservable.results(context, new Func1<Realm, RealmResults<RealmSession>>() {
            @Override
            public RealmResults<RealmSession> call(Realm realm) {
                Log.d(TAG, "getting sessions from db for day=" +sessionDay + " and transforming to base models");
                //its a hack
                Date beginDate = sessionDay.when.toDate();
                Date endOfDate = sessionDay.when.plusHours(23).toDate();
                return realm
                        .where(RealmSession.class)
                        .between("date", beginDate, endOfDate)
                        .findAll();
            }
        }).map(new Func1<RealmResults<RealmSession>, List<Session>>() {
            @Override
            public List<Session> call(RealmResults<RealmSession> realmSessions) {
                return sessionMapper.fromDBList(realmSessions);
            }
        });
    }

    public void saveData(AgendaResponse agendaResponse, SpeakerResponse speakerResponse) {
        List<RealmSession> sessionDBs = sessionMapper.mapList(agendaResponse.sessions);
        List<RealmSpeaker> speakerDBs = speakerMapper.mapList(speakerResponse.speakers);

        Realm realm = Realm.getDefaultInstance();
        realm.beginTransaction();

        realm.copyToRealmOrUpdate(sessionDBs);
        List<RealmSpeaker> realmSpeakers = realm.copyToRealmOrUpdate(speakerDBs);

        for (Session session : agendaResponse.sessions) {
            List<RealmSpeaker> sessionsSpeaker = speakerMapper.matchFromApi(realmSpeakers, session.speakers);
            RealmSession sessionDB = realm
                    .where(RealmSession.class)
                    .equalTo("id", session.id)
                    .findFirst();
            sessionDB.getSpeakers().addAll(sessionsSpeaker);
            realm.copyToRealmOrUpdate(sessionDB);
        }
        realm.commitTransaction();
        realm.close();
    }
}
