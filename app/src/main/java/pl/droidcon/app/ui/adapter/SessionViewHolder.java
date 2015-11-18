package pl.droidcon.app.ui.adapter;

import android.content.res.Resources;
import android.graphics.Typeface;
import android.support.v7.widget.RecyclerView;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.StyleSpan;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;
import pl.droidcon.app.R;
import pl.droidcon.app.dagger.DroidconInjector;
import pl.droidcon.app.helper.DateTimePrinter;
import pl.droidcon.app.helper.UrlHelper;
import pl.droidcon.app.model.api.Session;
import pl.droidcon.app.model.api.Speaker;

public class SessionViewHolder extends RecyclerView.ViewHolder {

    @Bind(R.id.session_picture)
    ImageView sessionPicture;

    @Bind(R.id.session_title)
    TextView sessionTitle;
    @Bind(R.id.session_date)
    TextView sessionDate;


    @Inject
    Resources resources;

    private Session session;

    public SessionViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
        DroidconInjector.get().inject(this);
    }


    public void attachSession(Session session) {
        this.session = session;

        String title = resources.getString(R.string.session_topic, session.title);
        SpannableString spannableString = new SpannableString(title);

        int startIndex = title.indexOf(session.title);
        int endIndex = startIndex + session.title.length();

        spannableString.setSpan(new StyleSpan(Typeface.BOLD), startIndex, endIndex, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        sessionTitle.setText(spannableString);

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
