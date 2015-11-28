package pl.droidcon.app.http;

import pl.droidcon.app.model.api.RatingsResponse;
import retrofit.http.GET;
import rx.Observable;

public interface RatingsService {

    @GET("gists")
    Observable<RatingsResponse> getGists();
}
