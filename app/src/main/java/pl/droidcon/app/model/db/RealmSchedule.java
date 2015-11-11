package pl.droidcon.app.model.db;


import java.util.Date;

import io.realm.RealmObject;

public class RealmSchedule extends RealmObject {
    private int realmSessionId;
    private Date scheduleDate;

    public RealmSchedule() {
    }

    public RealmSchedule(int realmSessionId, Date scheduleDate) {
        this.realmSessionId = realmSessionId;
        this.scheduleDate = scheduleDate;
    }

    public int getRealmSessionId() {
        return realmSessionId;
    }

    public void setRealmSessionId(int realmSessionId) {
        this.realmSessionId = realmSessionId;
    }

    public Date getScheduleDate() {
        return scheduleDate;
    }

    public void setScheduleDate(Date scheduleDate) {
        this.scheduleDate = scheduleDate;
    }
}
