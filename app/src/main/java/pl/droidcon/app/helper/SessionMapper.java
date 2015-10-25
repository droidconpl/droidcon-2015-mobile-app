package pl.droidcon.app.helper;


import org.joda.time.DateTime;

import java.util.ArrayList;
import java.util.List;

import io.realm.RealmList;
import pl.droidcon.app.model.api.Session;
import pl.droidcon.app.model.db.RealmSession;
import pl.droidcon.app.model.db.RealmSpeaker;

public class SessionMapper implements Mapper<Session, RealmSession> {

    private SpeakerMapper speakerMapper;

    public SessionMapper(SpeakerMapper speakerMapper) {
        this.speakerMapper = speakerMapper;
    }

    @Override
    public RealmSession map(Session session) {
        RealmSession realmSession = new RealmSession();
        realmSession.setId(session.id);
        realmSession.setDate(session.date.toDate());
        realmSession.setTitle(session.title);
        realmSession.setDescription(session.description);
        realmSession.setRating(session.rating);
        return realmSession;
    }

    @Override
    public RealmList<RealmSession> mapList(List<Session> sessions) {
        RealmList<RealmSession> realmSessions = new RealmList<>();
        for (Session session : sessions) {
            realmSessions.add(map(session));
        }
        return realmSessions;
    }

    @Override
    public RealmList<RealmSession> matchFromApi(List<RealmSession> databaseObjects, List<Integer> ids) {
        RealmList<RealmSession> dbs = new RealmList<>();
        for (Integer id : ids) {
            for (RealmSession orig : databaseObjects) {
                if (id.equals(orig.getId())) {
                    dbs.add(orig);
                }
            }
        }
        return dbs;
    }

    @Override
    public List<Session> fromDBList(List<RealmSession> realmSessions) {
        List<Session> sessions = new ArrayList<>();
        for (RealmSession realmSession : realmSessions) {
            sessions.add(fromDB(realmSession));
        }
        return sessions;
    }

    @Override
    public Session fromDB(RealmSession realmSession) {
        Session session = new Session();
        session.id = realmSession.getId();
        session.date = new DateTime(realmSession.getDate());
        session.title = realmSession.getTitle();
        session.description = realmSession.getDescription();
        session.rating = realmSession.getRating();

        RealmList<RealmSpeaker> speakers = realmSession.getSpeakers();
        session.setRealSpeakerList(speakerMapper.fromDBList(speakers));
        return session;
    }
}
