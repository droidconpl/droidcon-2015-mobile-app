package pl.droidcon.app.model.common;


import pl.droidcon.app.model.api.Session;

public class Schedule {
    private Session session;



    public Schedule(Session session) {
        this.session = session;
    }

    public Session getSession() {
        return session;
    }

    @Override
    public String toString() {
        return "Schedule{" +
                "session=" + session +
                '}';
    }
}
