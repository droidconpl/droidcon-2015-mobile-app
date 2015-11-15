package pl.droidcon.app.dagger;

import javax.inject.Singleton;

import dagger.Component;
import pl.droidcon.app.DroidconApp;
import pl.droidcon.app.dagger.module.AndroidModule;
import pl.droidcon.app.dagger.module.ApiModule;
import pl.droidcon.app.dagger.module.LogicModule;
import pl.droidcon.app.dagger.module.UIModule;
import pl.droidcon.app.database.DatabaseManager;
import pl.droidcon.app.reminder.SessionReminder;

@Singleton
@Component(modules = {UIModule.class, ApiModule.class, LogicModule.class, AndroidModule.class})
public interface DroidconComponent extends DroidconGraph {

    DatabaseManager databaseManager();

    SessionReminder sessionReminder();

    final class Initializer {
        private Initializer() {

        }

        public static DroidconComponent init(DroidconApp droidconApp) {
            return DaggerDroidconComponent.builder()
                    .androidModule(new AndroidModule(droidconApp))
                    .build();
        }
    }
}
