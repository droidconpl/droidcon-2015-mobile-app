package pl.droidcon.app.dagger;

import javax.inject.Singleton;

import dagger.Component;
import pl.droidcon.app.dagger.module.ApiModule;
import pl.droidcon.app.dagger.module.UIModule;

@Singleton
@Component(modules = {UIModule.class, ApiModule.class})
public interface DroidconComponent extends DroidconGraph {

    final class Initializer {
        private Initializer(){

        }

        public static DroidconComponent init(){
            return DaggerDroidconComponent.builder()
                    .build();
        }
    }
}
