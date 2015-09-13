package pl.droidcon.app.dagger;


public class DroidconInjector {

    private static DroidconComponent droidconComponent;

    public static void init(){
        droidconComponent = DroidconComponent.Initializer.init();
    }

    public static DroidconComponent get(){
        return droidconComponent;
    }
}
