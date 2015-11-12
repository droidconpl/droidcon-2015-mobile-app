package pl.droidcon.app.ui.view;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import pl.droidcon.app.R;
import pl.droidcon.app.helper.UrlHelper;
import pl.droidcon.app.model.api.Speaker;


public class SpeakerListItem extends SimpleListItem {

    private static final String TAG = SpeakerListItem.class.getSimpleName();

    @Nullable
    private SpeakerList.SpeakerItemClickListener speakerItemClickListener;
    private Speaker speaker;

    public SpeakerListItem(Context context) {
        super(context);
    }

    public SpeakerListItem(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public SpeakerListItem(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public SpeakerListItem(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    @Override
    protected void init(Context context, AttributeSet attributeSet, int defStyleAttr, int defStyleRes) {
        super.init(context, attributeSet, defStyleAttr, defStyleRes);

        if (isInEditMode()) {
            showStubData();
        }
        setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (speakerItemClickListener != null) {
                    speakerItemClickListener.onSpeakerClicked(speaker);
                }
            }
        });
    }

    //only stub data
    private void showStubData() {
        setDescription("Stub data with very long text to display. It should ellipsize on the end. Max lines set to 3");
        description.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_settings_black_24dp, 0, 0, 0);
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
        setTitle(getResources().getString(R.string.speaker_full_name_format, speaker.firstName, speaker.lastName));
        setImage(UrlHelper.url(speaker.imageUrl));
        setDescription(speaker.bio);
    }

}
