package pl.droidcon.app.dagger;


import pl.droidcon.app.DroidconApp;

public class DroidconInjector {

    private static DroidconComponent droidconComponent;

    public static void init(DroidconApp droidconApp) {
        droidconComponent = DroidconComponent.Initializer.init(droidconApp);
    }

    public static DroidconComponent get() {
        return droidconComponent;
    }
}
