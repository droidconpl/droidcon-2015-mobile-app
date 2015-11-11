package pl.droidcon.app.model.common;


import android.support.annotation.Nullable;

public class ScheduleCollision {

    @Nullable
    private final Schedule schedule;

    private final boolean collision;


    public ScheduleCollision(@Nullable Schedule schedule, boolean collision) {
        this.schedule = schedule;
        this.collision = collision;
    }

    @Nullable
    public Schedule getSchedule() {
        return schedule;
    }

    public boolean isCollision() {
        return collision;
    }
}
