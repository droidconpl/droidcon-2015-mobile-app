package pl.droidcon.app.dagger.module;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.squareup.okhttp.OkHttpClient;

import org.joda.time.DateTime;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import pl.droidcon.app.converter.DateTimeConverter;
import pl.droidcon.app.http.RestService;
import retrofit.Endpoint;
import retrofit.Endpoints;
import retrofit.RestAdapter;
import retrofit.client.Client;
import retrofit.client.OkClient;
import retrofit.converter.GsonConverter;

@Module
public class ApiModule {

    private final static String BASE_URL = "https://raw.githubusercontent.com/droidconpl/droidcon-2015-web/feature/json_model/";

    @Provides
    @Singleton
    RestService provideRestService(RestAdapter restAdapter) {
        return restAdapter.create(RestService.class);
    }

    @Provides
    @Singleton
    Gson provideGson() {
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapter(DateTime.class, new DateTimeConverter());
        return gsonBuilder.create();
    }

    @Provides
    @Singleton
    RestAdapter provideRestAdapter(Client client, Endpoint endpoint, Gson gson) {
        return new RestAdapter.Builder()
                .setClient(client)
                .setEndpoint(endpoint)
                .setConverter(new GsonConverter(gson))
                .build();
    }

    @Provides
    @Singleton
    Client provideClient(OkHttpClient okHttpClient) {
        return new OkClient(okHttpClient);
    }

    @Provides
    @Singleton
    OkHttpClient provideOkHttpClient() {
        return new OkHttpClient();
    }

    @Provides
    @Singleton
    Endpoint provideEndpoint() {
        return Endpoints.newFixedEndpoint(BASE_URL);
    }
}
