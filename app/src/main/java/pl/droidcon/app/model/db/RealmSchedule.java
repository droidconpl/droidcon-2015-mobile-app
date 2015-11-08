package pl.droidcon.app.model.db;


import io.realm.RealmObject;

public class RealmSchedule extends RealmObject {
    private int realmSessionId;

    public RealmSchedule() {
    }

    public RealmSchedule(int realmSessionId) {
        this.realmSessionId = realmSessionId;
    }

    public int getRealmSessionId() {
        return realmSessionId;
    }

    public void setRealmSessionId(int realmSessionId) {
        this.realmSessionId = realmSessionId;
    }
}
