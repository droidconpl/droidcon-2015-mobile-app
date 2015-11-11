package pl.droidcon.app.ui.view;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.Gravity;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.List;

import pl.droidcon.app.model.api.Speaker;

public class SpeakerList extends LinearLayout {

    public interface SpeakerItemClickListener {
        void onSpeakerClicked(@NonNull Speaker speaker);
    }

    public SpeakerList(Context context) {
        this(context, null);
    }

    public SpeakerList(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public SpeakerList(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs, defStyleAttr, 0);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public SpeakerList(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(context, attrs, defStyleAttr, defStyleRes);
    }

    private void init(Context context, AttributeSet attributeSet, int defStyleAttr, int defStyleRes) {
        setGravity(Gravity.CENTER_VERTICAL);
        setOrientation(VERTICAL);
        if (isInEditMode()) {
            showStubData();
        }
    }

    private void showStubData() {
        List<Speaker> speakers = new ArrayList<>();
        speakers.add(new Speaker());
        speakers.add(new Speaker());
        setSpeakers(speakers, null);
    }

    public void setSpeakers(List<Speaker> speakers, @Nullable SpeakerItemClickListener speakerItemClickListener) {
        removeAllViews();
        for (Speaker speaker : speakers) {
            SpeakerListItem speakerListItem = new SpeakerListItem(getContext());
            speakerListItem.setSpeaker(speaker);
            speakerListItem.setSpeakerItemClickListener(speakerItemClickListener);
            addView(speakerListItem);
        }
    }
}
