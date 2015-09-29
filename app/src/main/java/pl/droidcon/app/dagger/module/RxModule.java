package pl.droidcon.app.dagger.module;

import dagger.Module;
import dagger.Provides;
import pl.droidcon.app.rx.BinderUtil;

@Module
public class RxModule {

    @Provides
    public BinderUtil provideBinderUtil() {
        return new BinderUtil();
    }
}
