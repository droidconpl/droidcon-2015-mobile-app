package pl.droidcon.app.ui.fragment.agenda;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import butterknife.Bind;
import butterknife.ButterKnife;
import pl.droidcon.app.R;
import pl.droidcon.app.service.model.Session;

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
//
//

    @Bind(R.id.session_picture)
    ImageView sessionPicture;

    public SessionViewHolder(View itemView) {
        super(itemView);

        ButterKnife.bind(this, itemView);
    }

    @Override
    public void onClick(View v) {

    }

    public void attachSession(Session session) {
        sessionTitle.setText(session.sessionTopic);

//        speakerFirstName.setText(session.speakerFirstName);
//        speakerLastName.setText(session.speakerLastName);
//
//        speakerPhoto.setText(session.speakerPhoto);
//        sessionDate.setText(session.sessionDate);

        Picasso.with(sessionPicture.getContext()).load(session.speakerPhoto).into(sessionPicture);
    }
}
