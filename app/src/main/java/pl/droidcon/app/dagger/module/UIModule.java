package pl.droidcon.app.dagger.module;

import android.content.Context;
import android.support.v4.content.ContextCompat;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import pl.droidcon.app.R;
import pl.droidcon.app.model.ui.SwipeRefreshColorSchema;
import pl.droidcon.app.ui.fragment.factory.DrawerFragmentFactory;
import pl.droidcon.app.wrapper.SnackbarWrapper;

@Module
public class UIModule {

    @Provides
    public SnackbarWrapper provideSnackbarWrapper() {
        return new SnackbarWrapper();
    }

    @Provides
    public DrawerFragmentFactory provideDrawerFragmentFactory() {
        return new DrawerFragmentFactory();
    }

    @Provides
    @Singleton
    public SwipeRefreshColorSchema provideSwipeRefreshColorSchema(Context context) {
        return new SwipeRefreshColorSchema(3)
                .withColor(ContextCompat.getColor(context, R.color.primaryColor))
                .withColor(ContextCompat.getColor(context, R.color.primaryColorDark))
                .withColor(ContextCompat.getColor(context, R.color.accentColor));
    }
}
