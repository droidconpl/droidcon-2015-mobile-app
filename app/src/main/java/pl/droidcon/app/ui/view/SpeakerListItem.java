package pl.droidcon.app.ui.view;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.Html;
import android.util.AttributeSet;
import android.util.Log;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.squareup.picasso.RequestCreator;
import com.squareup.picasso.Target;

import butterknife.Bind;
import butterknife.ButterKnife;
import pl.droidcon.app.R;
import pl.droidcon.app.helper.UrlHelper;
import pl.droidcon.app.model.api.Speaker;
import pl.droidcon.app.ui.transformation.CircleTransform;


public class SpeakerListItem extends LinearLayout {

    private static final String TAG = SpeakerListItem.class.getSimpleName();

    @Bind(R.id.speaker_list_item_text)
    TextView bio;
    @Bind(R.id.speaker_list_item_full_name)
    TextView fullName;

    @Nullable
    private SpeakerList.SpeakerItemClickListener speakerItemClickListener;
    private Speaker speaker;

    public SpeakerListItem(Context context) {
        this(context, null);
    }

    public SpeakerListItem(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public SpeakerListItem(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs, defStyleAttr, 0);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public SpeakerListItem(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(context, attrs, defStyleAttr, defStyleRes);
    }


    private void init(Context context, AttributeSet attributeSet, int defStyleAttr, int defStyleRes) {
        LayoutInflater.from(context).inflate(R.layout.speaker_list_item, this, true);
        ButterKnife.bind(this, this);
        setGravity(Gravity.CENTER_VERTICAL);
        setOrientation(VERTICAL);
        TypedValue typedValue = new TypedValue();
        context.getTheme().resolveAttribute(android.R.attr.selectableItemBackground, typedValue, true);
        setBackgroundResource(typedValue.resourceId);
        setClickable(true);
        setFocusable(true);

        getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                removeGlobalLayoutListener(this);
                MarginLayoutParams marginLayoutParams = (MarginLayoutParams) getLayoutParams();
                int marginPixelSize = getResources().getDimensionPixelSize(R.dimen.list_element_margin);
                marginLayoutParams.topMargin = marginPixelSize;
                marginLayoutParams.bottomMargin = marginPixelSize;
                setLayoutParams(marginLayoutParams);
                setPadding(marginPixelSize, marginPixelSize, marginPixelSize, marginPixelSize);
            }
        });
        if (isInEditMode()) {
            showStubData();
        }
        setOnClickListener(clickListener);
    }

    @SuppressWarnings("deprecation")
    @SuppressLint("NewApi")
    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    private void removeGlobalLayoutListener(ViewTreeObserver.OnGlobalLayoutListener globalLayoutListener) {
        if (Build.VERSION.SDK_INT < 16) {
            getViewTreeObserver().removeGlobalOnLayoutListener(globalLayoutListener);
        } else {
            getViewTreeObserver().removeOnGlobalLayoutListener(globalLayoutListener);
        }
    }

    //only stub data
    private void showStubData() {
        setBio("Stub data with very long text to display. It should ellipsize on the end. Max lines set to 3");
        bio.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_settings_black_24dp, 0, 0, 0);
    }

    public void setSpeakerItemClickListener(@Nullable SpeakerList.SpeakerItemClickListener speakerItemClickListener) {
        this.speakerItemClickListener = speakerItemClickListener;
    }

    public void setSpeaker(Speaker speaker) {
        if (isInEditMode()) {
            showStubData();
            return;
        }
        this.speaker = speaker;
        setFullName(getResources().getString(R.string.speaker_full_name_format, speaker.firstName, speaker.lastName));
        setImage(UrlHelper.url(speaker.imageUrl));
        setBio(speaker.bio);
    }

    private void setFullName(String text) {
        fullName.setText(text);
    }

    private void setImage(@NonNull String imageUrl) {
        load(Picasso.with(getContext()).load(imageUrl));
    }

    private void setBio(String text) {
        bio.setText(Html.fromHtml(text));
    }

    private void load(RequestCreator requestCreator) {
        requestCreator
                .resize(64, 64)
                .transform(new CircleTransform())
                .into(bitmapTarget);
    }

    private Target bitmapTarget = new Target() {
        @Override
        public void onBitmapLoaded(Bitmap bitmap, Picasso.LoadedFrom from) {
            Log.d(TAG, "onBitmapLoaded");
            bio.setCompoundDrawablesWithIntrinsicBounds(new BitmapDrawable(getResources(), bitmap), null, null, null);
        }

        @Override
        public void onBitmapFailed(Drawable errorDrawable) {
            Log.d(TAG, "onBitmapFailed");
        }

        @Override
        public void onPrepareLoad(Drawable placeHolderDrawable) {

        }
    };


    private OnClickListener clickListener = new OnClickListener() {
        @Override
        public void onClick(View v) {
            if (speakerItemClickListener != null) {
                speakerItemClickListener.onSpeakerClicked(speaker);
            }
        }
    };
}
