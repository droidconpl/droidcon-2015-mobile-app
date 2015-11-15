package pl.droidcon.app.model.common;


import pl.droidcon.app.model.api.Session;
import pl.droidcon.app.model.db.RealmSessionNotification;

public class SessionNotification {

    private final int sessionId;

    public SessionNotification(int sessionId) {
        this.sessionId = sessionId;
    }

    public static SessionNotification of(Session session) {
        return new SessionNotification(session.id);
    }

    public static SessionNotification of(RealmSessionNotification sessionNotification) {
        return new SessionNotification(sessionNotification.getSessionId());
    }

    public int getSessionId() {
        return sessionId;
    }
}
