package pl.droidcon.app.service.api;

import com.octo.android.robospice.retrofit.RetrofitGsonSpiceService;

import retrofit.RestAdapter;

public class AgendaRetrofitService extends RetrofitGsonSpiceService {

    private final static String BASE_URL = "https://raw.githubusercontent.com/droidconpl/droidcon-2015-web/master";


    @Override
    public void onCreate() {
        super.onCreate();
        addRetrofitInterface(AgendaService.class);
    }

    @Override
    protected String getServerUrl() {
        return BASE_URL;
    }

    @Override
    protected RestAdapter.Builder createRestAdapterBuilder() {
        return super.createRestAdapterBuilder().setLogLevel(RestAdapter.LogLevel.FULL);
    }
}
