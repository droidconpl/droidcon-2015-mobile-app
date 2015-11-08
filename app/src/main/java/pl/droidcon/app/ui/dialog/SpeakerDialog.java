package pl.droidcon.app.ui.dialog;

import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatDialogFragment;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

import butterknife.Bind;
import butterknife.ButterKnife;
import pl.droidcon.app.R;
import pl.droidcon.app.helper.UrlHelper;
import pl.droidcon.app.model.api.Speaker;
import pl.droidcon.app.ui.transformation.CircleTransform;

public class SpeakerDialog extends AppCompatDialogFragment {

    private static final String SPEAKER_EXTRA = "speaker";

    public static SpeakerDialog newInstance(Speaker speaker) {
        Bundle args = new Bundle();
        args.putParcelable(SPEAKER_EXTRA, speaker);
        SpeakerDialog fragment = new SpeakerDialog();
        fragment.setArguments(args);
        return fragment;
    }

    private Speaker speaker;

    @Bind(R.id.speaker_header)
    TextView speakerHeader;

    @Bind(R.id.speaker_bio)
    TextView speakerBio;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(STYLE_NO_TITLE, getTheme());
        speaker = getArguments().getParcelable(SPEAKER_EXTRA);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.speaker_dialog, container);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);

        Picasso.with(getContext()).load(UrlHelper.url(speaker.imageUrl)).resize(128, 128).transform(new CircleTransform()).into(target);
        speakerHeader.setText(getString(R.string.speaker_full_name_format, speaker.firstName, speaker.lastName));
        speakerBio.setText(Html.fromHtml(speaker.bio));
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        Picasso.with(getContext()).cancelRequest(target);
        ButterKnife.unbind(this);
    }

    private Target target = new Target() {
        @Override
        public void onBitmapLoaded(Bitmap bitmap, Picasso.LoadedFrom from) {
            speakerHeader.setCompoundDrawablesWithIntrinsicBounds(new BitmapDrawable(getResources(), bitmap), null, null, null);
        }

        @Override
        public void onBitmapFailed(Drawable errorDrawable) {

        }

        @Override
        public void onPrepareLoad(Drawable placeHolderDrawable) {

        }
    };
}
