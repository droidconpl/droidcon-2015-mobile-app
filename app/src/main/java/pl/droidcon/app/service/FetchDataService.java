package pl.droidcon.app.service;

import android.app.IntentService;
import android.content.Intent;
import android.widget.Toast;

import pl.droidcon.app.service.api.AgendaService;
import retrofit.RestAdapter;
import retrofit.RetrofitError;

//TODO
public class FetchDataService extends IntentService {

    private static final String TAG = FetchDataService.class.getSimpleName();

    public FetchDataService() {
        super(TAG);
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        RestAdapter.Builder builder = new RestAdapter.Builder();
        builder.setEndpoint("https://raw.githubusercontent.com/droidconpl/droidcon-2015-web/master");
        builder.setLogLevel(RestAdapter.LogLevel.FULL);
        RestAdapter build = builder.build();
        AgendaService agendaService = build.create(AgendaService.class);

        Toast.makeText(getApplicationContext(), agendaService.getAllSessions().toString(), Toast.LENGTH_LONG).show();
    }
}
