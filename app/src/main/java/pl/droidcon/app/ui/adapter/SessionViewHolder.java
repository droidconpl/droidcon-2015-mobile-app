package pl.droidcon.app.ui.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;
import pl.droidcon.app.R;
import pl.droidcon.app.dagger.DroidconInjector;
import pl.droidcon.app.model.api.AgendaAndSpeakersResponse;
import pl.droidcon.app.model.api.AgendaAndSpeakersResponse.AgendaAndSpeakers;
import pl.droidcon.app.model.api.Session;
import pl.droidcon.app.model.api.Speaker;
import pl.droidcon.app.wrapper.SnackbarWrapper;

public class SessionViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    @Bind(R.id.session_title)
    TextView sessionTitle;


//    @Bind(R.id.session_speaker_first_name)
//    TextView speakerFirstName;
//
//
//    @Bind(R.id.session_speaker_last_name)
//    TextView speakerLastName;
//
//
//    @Bind(R.id.session_speaker_photo)
//    TextView speakerPhoto;
//
//
//    @Bind(R.id.session_date)
//    TextView sessionDate;

    @Bind(R.id.session_picture)
    ImageView sessionPicture;

    @Inject
    SnackbarWrapper snackbarWrapper;

    AgendaAndSpeakers agendaAndSpeakers;

    public SessionViewHolder(View itemView) {
        super(itemView);

        ButterKnife.bind(this, itemView);
        DroidconInjector.get().inject(this);
        itemView.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        snackbarWrapper.showSnackbar(v,"Click on " + agendaAndSpeakers.session.date);
    }

    public void attachSession(AgendaAndSpeakers agendaAndSpeakers) {
        this.agendaAndSpeakers = agendaAndSpeakers;
        sessionTitle.setText(agendaAndSpeakers.session.title);

//        speakerFirstName.setText(session.speakerFirstName);
//        speakerLastName.setText(session.speakerLastName);
//
//        speakerPhoto.setText(session.speakerPhoto);
//        sessionDate.setText(session.sessionDate)
        Speaker speaker = agendaAndSpeakers.speakers.get(0);

        Picasso.with(sessionPicture.getContext()).load(speaker.imageUrl).into(sessionPicture);
    }
}
