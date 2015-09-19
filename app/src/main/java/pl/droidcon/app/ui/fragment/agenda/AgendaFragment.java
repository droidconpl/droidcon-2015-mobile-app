package pl.droidcon.app.ui.fragment.agenda;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.octo.android.robospice.SpiceManager;
import com.octo.android.robospice.persistence.DurationInMillis;
import com.octo.android.robospice.persistence.exception.SpiceException;
import com.octo.android.robospice.request.listener.RequestListener;

import java.util.List;

import pl.droidcon.app.R;
import pl.droidcon.app.service.api.AgendaRetrofitService;
import pl.droidcon.app.service.api.AgendaRetrofitSpiceRequest;
import pl.droidcon.app.service.model.AgendaResponse;
import pl.droidcon.app.service.model.Session;


public class AgendaFragment extends Fragment {

    private SpiceManager spiceManager = new SpiceManager(AgendaRetrofitService.class);
    private RecyclerView agendaList;

    public static AgendaFragment newInstance() {
        Bundle args = new Bundle();

        AgendaFragment fragment = new AgendaFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.agenda_fragment, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        agendaList = (RecyclerView) view.findViewById(R.id.agenda_view);

        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        agendaList.setHasFixedSize(true);

        // use a linear layout manager
        LinearLayoutManager mLayoutManager = new GridLayoutManager(view.getContext(), 2);
        agendaList.setLayoutManager(mLayoutManager);

        AgendaRetrofitSpiceRequest contactsRetrofitSpiceRequest = new AgendaRetrofitSpiceRequest();
        spiceManager.execute(contactsRetrofitSpiceRequest, "contacts", DurationInMillis.ALWAYS_RETURNED, new ListAllSessionsListener());
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
    }

    @Override
    public void onStart() {
        super.onStart();
        spiceManager.start(getActivity());
    }

    @Override
    public void onStop() {
        if (spiceManager.isStarted()) {
            spiceManager.shouldStop();
        }
        super.onStop();
    }

    public void update(List<Session> sessions) {
        AgendaAdapter mAdapter = new AgendaAdapter(sessions);
        agendaList.setAdapter(mAdapter);
    }

    class ListAllSessionsListener implements RequestListener<AgendaResponse> {

        @Override
        public void onRequestFailure(SpiceException spiceException) {
            Toast.makeText(getContext(), "WILL HANDLE LATER", Toast.LENGTH_SHORT);
        }

        @Override
        public void onRequestSuccess(AgendaResponse agendaResponse) {
            update(agendaResponse.sessions);
        }
    }
}
