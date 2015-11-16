package pl.droidcon.app.ui.dialog;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatDialogFragment;
import android.text.Html;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;
import com.joanzapata.iconify.widget.IconButton;

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

    @Bind(R.id.speaker_photo)
    ImageView speakerPhoto;
    @Bind(R.id.speaker_full_name)
    TextView speakerFullName;
    @Bind(R.id.speaker_title)
    TextView speakerTitle;
    @Bind(R.id.speaker_bio)
    TextView speakerBio;
    @Bind(R.id.website_link)
    IconButton websiteButton;
    @Bind(R.id.facebook_link)
    IconButton facebookButton;
    @Bind(R.id.twitter_link)
    IconButton twitterButton;
    @Bind(R.id.github_link)
    IconButton githubButton;
    @Bind(R.id.linkedin_link)
    IconButton linkedInButton;
    @Bind(R.id.google_link)
    IconButton googleLink;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(STYLE_NO_TITLE, getTheme());
        speaker = getArguments().getParcelable(SPEAKER_EXTRA);
    }

    @SuppressLint("InflateParams")
    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        View view = LayoutInflater.from(getContext()).inflate(R.layout.speaker_dialog, null);
        ButterKnife.bind(this, view);
        Glide.with(getContext()).load(UrlHelper.url(speaker.imageUrl)).asBitmap().centerCrop().into(target);
        speakerFullName.setText(getString(R.string.speaker_full_name_format, speaker.firstName, speaker.lastName));
        speakerBio.setText(Html.fromHtml(speaker.bio));
        speakerTitle.setText(speaker.websiteTitle);
        setLinks();
        builder.setView(view)
                .setPositiveButton(R.string.hide, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dismiss();
                    }
                });

        return builder.create();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        Glide.clear(target);
        ButterKnife.unbind(this);
    }

    private void setLinks() {
        if (TextUtils.isEmpty(speaker.websiteLink)) {
            websiteButton.setVisibility(View.GONE);
        } else {
            websiteButton.setOnClickListener(new LinkClickListener(speaker.websiteLink, LinkClickListener.Type.WEBSITE));
        }
        if (TextUtils.isEmpty(speaker.facebookLink)) {
            facebookButton.setVisibility(View.GONE);
        } else {
            facebookButton.setOnClickListener(new LinkClickListener(speaker.facebookLink, LinkClickListener.Type.FACEBOOK));
        }
        if (TextUtils.isEmpty(speaker.twitterHandler)) {
            twitterButton.setVisibility(View.GONE);
        } else {
            twitterButton.setOnClickListener(new LinkClickListener(speaker.twitterHandler, LinkClickListener.Type.TWITTER));
        }
        if (TextUtils.isEmpty(speaker.githubLink)) {
            githubButton.setVisibility(View.GONE);
        } else {
            githubButton.setOnClickListener(new LinkClickListener(speaker.githubLink, LinkClickListener.Type.GITHUB));
        }
        if (TextUtils.isEmpty(speaker.linkedIn)) {
            linkedInButton.setVisibility(View.GONE);
        } else {
            linkedInButton.setOnClickListener(new LinkClickListener(speaker.linkedIn, LinkClickListener.Type.LINKEDIN));
        }
        if (TextUtils.isEmpty(speaker.googlePlus)) {
            googleLink.setVisibility(View.GONE);
        } else {
            googleLink.setOnClickListener(new LinkClickListener(speaker.googlePlus, LinkClickListener.Type.GOOGLE));
        }
    }

    private SimpleTarget<Bitmap> target = new SimpleTarget<Bitmap>(128, 128) {
        @Override
        public void onResourceReady(Bitmap resource, GlideAnimation<? super Bitmap> glideAnimation) {
            RoundedBitmapDrawable roundedBitmapDrawable = RoundedBitmapDrawableFactory.create(getResources(), resource);
            roundedBitmapDrawable.setCircular(true);
            speakerPhoto.setImageDrawable(roundedBitmapDrawable);
        }
    };

    private static class LinkClickListener implements View.OnClickListener {

        private enum Type {
            WEBSITE,
            FACEBOOK,
            TWITTER,
            GITHUB,
            LINKEDIN,
            GOOGLE
        }

        private final Type type;
        private final String value;

        public LinkClickListener(String value, Type type) {
            this.value = value;
            this.type = type;
        }

        @Override
        public void onClick(View v) {
            Intent intent = getIntent(v.getContext());
            v.getContext().startActivity(intent);
        }

        private Intent getIntent(Context context) {
            switch (type) {
                case WEBSITE:
                    return getBrowserIntent();
                case FACEBOOK:
                    return getFBIntent(context);
                case TWITTER:
                    return getTwitterIntent();
                case GITHUB:
                    return getGithubIntent();
                case LINKEDIN:
                    return getBrowserIntent();
                case GOOGLE:
                    return getBrowserIntent();
            }
            throw new UnsupportedOperationException("Not supported intent");
        }

        private Intent getBrowserIntent() {
            return new Intent(Intent.ACTION_VIEW, Uri.parse(value));
        }

        private Intent getFBIntent(Context context) {
            try {
                context.getPackageManager().getPackageInfo("com.facebook.katana", 0);
                String facebookScheme = "fb://facewebmodal/f?href=" + value;
                return new Intent(Intent.ACTION_VIEW, Uri.parse(facebookScheme));
            } catch (Exception e) {
                return new Intent(Intent.ACTION_VIEW, Uri.parse(value));
            }
        }

        private Intent getTwitterIntent() {
            String url = "https://twitter.com/" + value;
            Intent intent = new Intent(Intent.ACTION_VIEW);
            intent.setData(Uri.parse(url));
            return intent;
        }

        private Intent getGithubIntent() {
            String url = "https://github.com/" + value;
            Intent intent = new Intent(Intent.ACTION_VIEW);
            intent.setData(Uri.parse(url));
            return intent;
        }
    }

}
