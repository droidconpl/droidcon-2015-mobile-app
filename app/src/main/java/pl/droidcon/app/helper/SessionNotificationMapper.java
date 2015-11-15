package pl.droidcon.app.helper;


import java.util.ArrayList;
import java.util.List;

import io.realm.RealmList;
import pl.droidcon.app.model.common.SessionNotification;
import pl.droidcon.app.model.db.RealmSessionNotification;

public class SessionNotificationMapper implements Mapper<SessionNotification, RealmSessionNotification> {

    @Override
    public RealmSessionNotification map(SessionNotification sessionNotification) {
        return new RealmSessionNotification(sessionNotification.getSessionId());
    }

    @Override
    public RealmList<RealmSessionNotification> mapList(List<SessionNotification> sessionNotifications) {
        RealmList<RealmSessionNotification> realmSessionNotifications = new RealmList<>();
        for (SessionNotification sessionNotification : sessionNotifications) {
            realmSessionNotifications.add(map(sessionNotification));
        }
        return realmSessionNotifications;
    }

    @Override
    public RealmList<RealmSessionNotification> matchFromApi(List<RealmSessionNotification> realmSessionNotifications, List<Integer> ids) {
        throw new UnsupportedOperationException("Not acceptable");
    }

    @Override
    public List<SessionNotification> fromDBList(List<RealmSessionNotification> realmSessionNotifications) {
        List<SessionNotification> sessionNotifications = new ArrayList<>();
        for (RealmSessionNotification realmSessionNotification : realmSessionNotifications) {
            sessionNotifications.add(fromDB(realmSessionNotification));
        }
        return sessionNotifications;
    }

    @Override
    public SessionNotification fromDB(RealmSessionNotification realmSessionNotification) {
        return SessionNotification.of(realmSessionNotification);
    }
}
