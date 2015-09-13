package pl.droidcon.app;

import android.app.Application;

import pl.droidcon.app.dagger.DroidconInjector;

public class DroidconApp extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        DroidconInjector.init();
    }
}
