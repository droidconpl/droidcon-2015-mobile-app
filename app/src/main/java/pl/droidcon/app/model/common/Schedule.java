package pl.droidcon.app.model.common;


public class Schedule {
    private int sessionId;


    public Schedule(int sessionId) {
        this.sessionId = sessionId;
    }

    public int getSessionId() {
        return sessionId;
    }

    @Override
    public String toString() {
        return "Schedule{" +
                "sessionId=" + sessionId +
                '}';
    }
}
