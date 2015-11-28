package pl.droidcon.app.ui.adapter;


import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import butterknife.Bind;
import butterknife.ButterKnife;
import pl.droidcon.app.R;
import pl.droidcon.app.helper.DateTimePrinter;
import pl.droidcon.app.model.api.Session;

public class AgendaNonSessionLargeViewHolder extends BaseSessionViewHolder {

    @Bind(R.id.session_picture)
    ImageView sessionPicture;
    @Bind(R.id.session_title)
    TextView sessionTitle;
    @Bind(R.id.session_date)
    TextView sessionDate;
    @Bind(R.id.agenda_large_icon)
    ImageView icon;

    private Session session;

    public AgendaNonSessionLargeViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    @Override
    public void attachSession(Session session) {
        this.session = session;

        String title = session.title;

        sessionTitle.setText(title);
        sessionDate.setText(DateTimePrinter.toPrintableStringWithDay(session.date));
        String lowerCaseTitle = session.title.toLowerCase();
        if (lowerCaseTitle.startsWith("registration") || lowerCaseTitle.startsWith("opening") ||
                lowerCaseTitle.startsWith("closing") || lowerCaseTitle.startsWith("barcamp")) {
            icon.setImageResource(R.drawable.ic_icon_droid_large);
            return;
        }

        if (lowerCaseTitle.startsWith("coffe")) {
            icon.setImageResource(R.drawable.ic_icon_coffee_large);
            return;
        }

        if (lowerCaseTitle.startsWith("lunch")) {
            icon.setImageResource(R.drawable.ic_icon_fork_large);
            return;
        }
        if (lowerCaseTitle.startsWith("afterparty")) {
            icon.setImageResource(R.drawable.ic_icon_party_large);
        }
    }


    @Override
    public Session getSession() {
        return session;
    }
}
