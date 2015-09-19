package pl.droidcon.app.ui.fragment.agenda;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import butterknife.Bind;
import butterknife.ButterKnife;
import pl.droidcon.app.R;
import pl.droidcon.app.service.model.Session;

public class SessionViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    @Bind(R.id.session_title)
    TextView sessionTitle;

    public SessionViewHolder(View itemView) {
        super(itemView);

        ButterKnife.bind(this, itemView);
    }

    @Override
    public void onClick(View v) {

    }

    public void attachSession(Session session) {
        sessionTitle.setText(session.sessionTopic);
    }
}
