package pl.droidcon.app.dagger.module;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import pl.droidcon.app.database.DatabaseManager;
import pl.droidcon.app.helper.ScheduleMapper;
import pl.droidcon.app.helper.SessionMapper;
import pl.droidcon.app.helper.SpeakerMapper;
import pl.droidcon.app.rx.BinderUtil;
import pl.droidcon.app.rx.DataSubscription;

@Module
public class LogicModule {

    @Provides
    public BinderUtil provideBinderUtil() {
        return new BinderUtil();
    }

    @Provides
    @Singleton
    public DataSubscription provideDatabaseSubscription() {
        return new DataSubscription();
    }

    @Provides
    public DatabaseManager provideDatabaseManager() {
        return new DatabaseManager();
    }

    @Provides
    public SpeakerMapper provideSpeakerMapper() {
        return new SpeakerMapper();
    }

    @Provides
    public SessionMapper provideSessionMapper(SpeakerMapper speakerMapper) {
        return new SessionMapper(speakerMapper);
    }

    @Provides
    public ScheduleMapper provideScheduleMapper(SessionMapper sessionMapper) {
        return new ScheduleMapper(sessionMapper);
    }
}
