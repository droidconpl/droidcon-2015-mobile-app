package pl.droidcon.app.helper;

import org.joda.time.DateTime;

import java.util.ArrayList;
import java.util.List;

import io.realm.RealmList;
import pl.droidcon.app.model.common.Schedule;
import pl.droidcon.app.model.db.RealmSchedule;

public class ScheduleMapper implements Mapper<Schedule, RealmSchedule> {

    @Override
    public RealmSchedule map(Schedule schedule) {
        return null;
    }

    @Override
    public RealmList<RealmSchedule> mapList(List<Schedule> schedules) {
        return null;
    }

    @Override
    public RealmList<RealmSchedule> matchFromApi(List<RealmSchedule> realmSchedules, List<Integer> ids) {
        return null;
    }

    @Override
    public List<Schedule> fromDBList(List<RealmSchedule> realmSchedules) {
        List<Schedule> schedules = new ArrayList<>();
        for (RealmSchedule realmSchedule : realmSchedules) {
            schedules.add(fromDB(realmSchedule));
        }
        return schedules;
    }

    @Override
    public Schedule fromDB(RealmSchedule realmSchedule) {
        return new Schedule(realmSchedule.getRealmSessionId(), new DateTime(realmSchedule.getScheduleDate()));
    }
}
