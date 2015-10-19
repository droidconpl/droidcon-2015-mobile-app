package pl.droidcon.app.helper;


public final class UrlHelper {

    private static final String BASE_URL = "https://raw.githubusercontent.com/droidconpl/droidcon-2015-web/master/";

    private UrlHelper() {

    }

    public static String url(String imgAddress){
        return BASE_URL + imgAddress;
    }
}
