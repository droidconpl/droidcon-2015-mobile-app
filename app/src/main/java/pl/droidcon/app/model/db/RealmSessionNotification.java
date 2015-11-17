package pl.droidcon.app.model.db;


import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class RealmSessionNotification extends RealmObject {

    @PrimaryKey
    private int sessionId;

    public RealmSessionNotification() {

    }

    public RealmSessionNotification(int sessionId) {
        this.sessionId = sessionId;
    }

    public void setSessionId(int sessionId) {
        this.sessionId = sessionId;
    }

    public int getSessionId() {
        return sessionId;
    }
}
