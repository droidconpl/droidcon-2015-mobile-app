package pl.droidcon.app.http;

import pl.droidcon.app.model.api.AgendaResponse;
import pl.droidcon.app.model.api.SpeakerResponse;
import retrofit.http.GET;
import rx.Observable;

public interface RestService {

    @GET("/model/sessions.json")
    Observable<AgendaResponse> getAgenda();

    @GET("/model/speakers.json")
    Observable<SpeakerResponse> getSpeakers();
}
