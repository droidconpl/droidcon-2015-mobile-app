package pl.droidcon.app.service.api;

import pl.droidcon.app.service.model.AgendaResponse;
import retrofit.http.GET;

public interface AgendaService {

    @GET("/model/agenda.json")
    AgendaResponse getAllSessions();
}
