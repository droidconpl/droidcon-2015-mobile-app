package pl.droidcon.app.model.db;


import io.realm.RealmObject;

public class RealmSchedule extends RealmObject {
    private RealmSession realmSession;

    public RealmSession getRealmSession() {
        return realmSession;
    }

    public void setRealmSession(RealmSession realmSession) {
        this.realmSession = realmSession;
    }
}
