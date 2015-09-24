package pl.droidcon.app.model.api;


import android.support.annotation.NonNull;

import org.joda.time.DateTime;

import java.util.ArrayList;
import java.util.List;

public class AgendaAndSpeakersResponse {

    public List<AgendaAndSpeakers> agendaAndSpeakers = new ArrayList<>();

    public AgendaAndSpeakersResponse(AgendaResponse agendaResponse, SpeakerResponse speakerResponse, DateTime when) {
        List<Session> sessions = agendaResponse.sessions;
        List<Speaker> speakers = speakerResponse.speakers;
        for (Session session : sessions) {
            if (session.date.toLocalDate().equals(when.toLocalDate())) {
                List<Integer> ids = session.speakers;
                List<Speaker> targetSpeakers = getSpeakers(ids, speakers);
                agendaAndSpeakers.add(new AgendaAndSpeakers(session, targetSpeakers));
            }
        }
    }

    private List<Speaker> getSpeakers(List<Integer> ids, List<Speaker> source) {
        List<Speaker> speakers = new ArrayList<>();
        for (Integer id : ids) {
            for (Speaker speaker : source) {
                if (id == speaker.id) {
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
            return session.date.compareTo(another.session.date);
        }
    }
}
