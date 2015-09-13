package pl.droidcon.app.service;

import android.app.IntentService;
import android.content.Intent;

//TODO
public class FetchDataService extends IntentService {

    private static final String TAG = FetchDataService.class.getSimpleName();

    public FetchDataService() {
        super(TAG);
    }

    @Override
    protected void onHandleIntent(Intent intent) {

    }
}
