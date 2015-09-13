package pl.droidcon.app.dagger.module;

import dagger.Module;
import dagger.Provides;
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
}
