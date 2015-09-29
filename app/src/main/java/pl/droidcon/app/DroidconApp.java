package pl.droidcon.app;

import android.app.Application;

import net.danlew.android.joda.JodaTimeAndroid;

import pl.droidcon.app.dagger.DroidconInjector;

public class DroidconApp extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        JodaTimeAndroid.init(this);
        DroidconInjector.init(this);
    }
}
