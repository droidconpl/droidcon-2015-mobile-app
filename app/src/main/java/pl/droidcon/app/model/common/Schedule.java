package pl.droidcon.app.model.common;


import org.joda.time.DateTime;

import pl.droidcon.app.model.ObservableModel;

public class Schedule implements ObservableModel {
    private int sessionId;
    private DateTime dateTime;

    public Schedule(int sessionId, DateTime dateTime) {
        this.sessionId = sessionId;
        this.dateTime = dateTime;
    }

    public int getSessionId() {
        return sessionId;
    }

    public DateTime getDateTime() {
        return dateTime;
    }

    @Override
    public String toString() {
        return "Schedule{" +
                "sessionId=" + sessionId +
                '}';
    }
}
