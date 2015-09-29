package pl.droidcon.app.model.api;


import android.support.annotation.NonNull;
import android.util.Log;

import org.joda.time.DateTime;

import java.util.ArrayList;
import java.util.List;

import io.realm.Realm;
import io.realm.RealmList;

public class AgendaAndSpeakersResponse {

    public List<AgendaAndSpeakers> agendaAndSpeakers = new ArrayList<>();

    public AgendaAndSpeakersResponse(AgendaResponse agendaResponse, SpeakerResponse speakerResponse, DateTime when) {
        List<Session> sessions = agendaResponse.sessions;

        Realm realm = Realm.getDefaultInstance();
        realm.beginTransaction();

        realm.copyToRealm(sessions);
        realm.commitTransaction();


        Log.d("DDD", "Stored sessions: " + realm.where(Session.class).findAll().size());

        List<Speaker> speakers = speakerResponse.speakers;
        for (Session session : sessions) {
            DateTime sessionTime = new DateTime(session.getDate().getTime());



            if (sessionTime.toLocalDate().equals(when.toLocalDate())) {
                RealmList<Speaker> ids = session.getSpeakers();
                List<Speaker> targetSpeakers = getSpeakers(ids, speakers);
                agendaAndSpeakers.add(new AgendaAndSpeakers(session, targetSpeakers));
            }
        }
    }

    private List<Speaker> getSpeakers(RealmList<Speaker> ids, List<Speaker> source) {
        List<Speaker> speakers = new ArrayList<>();
        for (Speaker id : ids) {
            for (Speaker speaker : source) {
                if (id.getId() == speaker.getId()) {
                    speakers.add(speaker);
                }
            }
        }
        return speakers;
    }


    public static class AgendaAndSpeakers implements Comparable<AgendaAndSpeakers> {

        public Session session;
        public List<Speaker> speakers;

        public AgendaAndSpeakers(Session session, List<Speaker> speakers) {
            this.session = session;
            this.speakers = speakers;
        }

        @Override
        public int compareTo(@NonNull AgendaAndSpeakers another) {
            return session.getDate().compareTo(another.session.getDate());
        }
    }
}
