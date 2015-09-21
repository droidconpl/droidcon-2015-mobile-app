package pl.droidcon.app.service.api;

import com.octo.android.robospice.request.retrofit.RetrofitSpiceRequest;

import pl.droidcon.app.model.api.AgendaResponse;

public class AgendaRetrofitSpiceRequest extends RetrofitSpiceRequest<AgendaResponse, AgendaService> {
    public AgendaRetrofitSpiceRequest() {
        super(AgendaResponse.class, AgendaService.class);
    }

    @Override
    public AgendaResponse loadDataFromNetwork() throws Exception {
        return getService().getAllSessions();
    }
}
