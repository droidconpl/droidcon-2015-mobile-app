package pl.droidcon.app.dagger.module;

import android.content.Context;
import android.content.res.Resources;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import pl.droidcon.app.DroidconApp;

@Module
public class AndroidModule {

    private DroidconApp droidconApp;

    public AndroidModule(DroidconApp droidconApp) {
        this.droidconApp = droidconApp;
    }

    @Provides
    @Singleton
    Context provideContext(){
        return droidconApp.getApplicationContext();
    }

    @Provides
    @Singleton
    Resources provideResources(){
        return droidconApp.getResources();
    }
}
