package pl.droidcon.app.ui.adapter;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import pl.droidcon.app.R;
import pl.droidcon.app.helper.DateTimePrinter;
import pl.droidcon.app.helper.UrlHelper;
import pl.droidcon.app.model.api.Session;
import pl.droidcon.app.model.api.Speaker;

public class AgendaSessionViewHolder extends BaseSessionViewHolder {

    @Bind(R.id.session_picture)
    ImageView sessionPicture;

    @Bind(R.id.session_title)
    TextView sessionTitle;
    @Bind(R.id.session_date)
    TextView sessionDate;

    private Session session;

    public AgendaSessionViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    @Override
    public void attachSession(Session session) {
        this.session = session;

        sessionTitle.setText(session.title);

        sessionDate.setText(DateTimePrinter.toPrintableStringWithDay(session.date));

        List<Speaker> realSpeakerList = session.getSpeakersList();
        if (realSpeakerList.isEmpty()) {
            sessionPicture.setImageResource(R.drawable.droidcon_krakow_logo);
        } else {
            String url = UrlHelper.url(realSpeakerList.get(0).imageUrl);
            Glide.with(sessionPicture.getContext())
                    .load(url)
                    .fitCenter()
                    .crossFade()
                    .into(sessionPicture);
        }
    }

    public Session getSession() {
        return session;
    }
}
