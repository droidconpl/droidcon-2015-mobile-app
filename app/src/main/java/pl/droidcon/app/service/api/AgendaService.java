package pl.droidcon.app.service.api;

import pl.droidcon.app.model.api.AgendaResponse;
import retrofit.http.GET;

public interface AgendaService {

    @GET("/model/agenda.json")
    AgendaResponse getAllSessions();
}
