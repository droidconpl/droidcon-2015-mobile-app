package pl.droidcon.app.ui.dialog;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory;
import android.support.v7.app.AppCompatDialogFragment;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;

import butterknife.Bind;
import butterknife.ButterKnife;
import pl.droidcon.app.R;
import pl.droidcon.app.helper.UrlHelper;
import pl.droidcon.app.model.api.Speaker;

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
        Glide.with(getContext()).load(UrlHelper.url(speaker.imageUrl)).asBitmap().centerCrop().into(target);
        speakerHeader.setText(getString(R.string.speaker_full_name_format, speaker.firstName, speaker.lastName));
        speakerBio.setText(Html.fromHtml(speaker.bio));
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        Glide.clear(target);
        ButterKnife.unbind(this);
    }

    private SimpleTarget<Bitmap> target = new SimpleTarget<Bitmap>(128, 128) {
        @Override
        public void onResourceReady(Bitmap resource, GlideAnimation<? super Bitmap> glideAnimation) {
            RoundedBitmapDrawable roundedBitmapDrawable = RoundedBitmapDrawableFactory.create(getResources(), resource);
            roundedBitmapDrawable.setCircular(true);
            speakerHeader.setCompoundDrawablesWithIntrinsicBounds(roundedBitmapDrawable, null, null, null);
        }
    };
}
