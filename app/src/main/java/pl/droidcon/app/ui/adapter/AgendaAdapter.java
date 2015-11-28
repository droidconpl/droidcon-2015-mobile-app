package pl.droidcon.app.ui.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import pl.droidcon.app.R;
import pl.droidcon.app.model.api.Session;

public class AgendaAdapter extends RecyclerView.Adapter<BaseSessionViewHolder> {

    private enum ViewType {
        NORMAL_SESSION(0),
        LARGE_NON_SESSION(1),
        LARGE_SESSION(2);
        private int viewType;

        ViewType(int viewType) {
            this.viewType = viewType;
        }

        public int getViewType() {
            return viewType;
        }

        public static ViewType of(int viewType) {
            for (ViewType value : values()) {
                if (viewType == value.getViewType()) {
                    return value;
                }
            }
            throw new IllegalArgumentException("Not supported view type");
        }
    }

    private List<Session> sessions;

    public AgendaAdapter(List<Session> sessions) {
        this.sessions = sessions;
    }

    @Override
    public BaseSessionViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ViewType type = ViewType.of(viewType);
        View v;
        switch (type) {
            case NORMAL_SESSION:
                v = LayoutInflater.from(parent.getContext()).inflate(R.layout.agenda_element, parent, false);
                return new AgendaSessionViewHolder(v);
            case LARGE_NON_SESSION:
                v = LayoutInflater.from(parent.getContext()).inflate(R.layout.agenda_non_session_large_element, parent, false);
                return new AgendaNonSessionLargeViewHolder(v);
            case LARGE_SESSION:
                v = LayoutInflater.from(parent.getContext()).inflate(R.layout.agenda_session_large_element, parent, false);
                return new AgendaSessionViewHolder(v);
        }

        throw new IllegalStateException("Not valid viewType");
    }

    @Override
    public int getItemViewType(int position) {
        Session sessionByPosition = getSessionByPosition(position);
        if (!sessionByPosition.singleItem) {
            return ViewType.NORMAL_SESSION.getViewType();
        }
        if (sessionByPosition.getSpeakersList().isEmpty()) {
            return ViewType.LARGE_NON_SESSION.getViewType();
        } else {
            return ViewType.LARGE_SESSION.getViewType();
        }
    }

    @Override
    public void onBindViewHolder(BaseSessionViewHolder holder, int position) {
        holder.attachSession(sessions.get(position));
    }

    @Override
    public int getItemCount() {
        return sessions.size();
    }

    @NonNull
    public Session getSessionByPosition(int position) {
        return sessions.get(position);
    }
}
