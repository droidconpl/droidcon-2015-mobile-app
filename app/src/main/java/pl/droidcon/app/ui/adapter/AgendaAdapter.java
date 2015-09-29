package pl.droidcon.app.ui.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import pl.droidcon.app.R;
import pl.droidcon.app.model.api.AgendaAndSpeakersResponse.AgendaAndSpeakers;

public class AgendaAdapter extends RecyclerView.Adapter<SessionViewHolder> {

    private List<AgendaAndSpeakers> sessions;

    public AgendaAdapter(List<AgendaAndSpeakers> sessions) {
        this.sessions = sessions;
    }

    @Override
    public SessionViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.session_element, parent, false);

        return new SessionViewHolder(v);
    }

    @Override
    public void onBindViewHolder(SessionViewHolder holder, int position) {
        holder.attachSession(sessions.get(position));
    }

    @Override
    public int getItemCount() {
        return sessions.size();
    }
}
