package pl.droidcon.app.ui.adapter;

import android.content.res.Resources;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;
import pl.droidcon.app.R;
import pl.droidcon.app.dagger.DroidconInjector;
import pl.droidcon.app.helper.DateTimePrinter;
import pl.droidcon.app.helper.UrlHelper;
import pl.droidcon.app.model.api.Session;
import pl.droidcon.app.model.common.Slot;


public class ScheduleViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    private static final String TAG = ScheduleViewHolder.class.getSimpleName();

    public interface ScheduleClickListener {
        void onScheduleClicked(View view, int position);
    }

    @Bind(R.id.slot_view_hour)
    TextView hour;
    @Bind(R.id.slot_view_speaker_image)
    ImageView image;
    @Bind(R.id.slot_view_title)
    TextView title;
    @Bind(R.id.slot_view_clickable)
    View clickable;
    @Bind(R.id.schedule_icon)
    ImageView icon;
    @Bind(R.id.slot_view_session_title)
    TextView sessionTitle;

    @Inject
    Resources resources;

    private ScheduleClickListener scheduleClickListener;

    public ScheduleViewHolder(View itemView, ScheduleClickListener scheduleClickListener) {
        super(itemView);
        this.scheduleClickListener = scheduleClickListener;
        ButterKnife.bind(this, itemView);
        DroidconInjector.get().inject(this);
        clickable.setOnClickListener(this);
    }

    public void attachSlot(Slot slot) {
        ViewGroup.LayoutParams layoutParams = itemView.getLayoutParams();
        hour.setText(DateTimePrinter.toPrintableString(slot.getDateTime()));
        title.setText(slot.getDisplayTitle());
        sessionTitle.setText(slot.getDisplayTitle());
        // be sure to reset title widget
        // show & hide
        title.setVisibility(View.VISIBLE);
        sessionTitle.setVisibility(View.GONE);

        int resId = -1;
        int height = resources.getDimensionPixelSize(R.dimen.list_item_height);
        switch (slot.getSlotType()) {
            case REGISTRATION:
            case OPENING_1_DAY:
            case CLOSING_1_DAY:
            case OPENING_2_DAY:
            case CLOSING_2_DAY:
            case BARCAMP:
                resId = R.drawable.ic_icon_droid;
                resetPhoto();
                break;
            case SESSION:
                height = getSessionSlotHeight(slot.getSession(), height);
                setSessionPhoto(slot.getSession());
                break;
            case LUNCH_BREAK:
                resId = R.drawable.ic_icon_fork;
                resetPhoto();
                break;
            case COFFEE_BREAK:
                resId = R.drawable.ic_icon_coffee;
                resetPhoto();
                break;
            case AFTER_PARTY:
                resId = R.drawable.ic_icon_party;
                resetPhoto();
                break;
        }
        if (resId != -1) {
            icon.setVisibility(View.VISIBLE);
            icon.setImageResource(resId);
            title.setSingleLine(true);
            title.setEllipsize(TextUtils.TruncateAt.END);
            title.setMaxLines(1);
        } else {
            title.setSingleLine(false);
            title.setEllipsize(null);
            title.setMaxLines(2);
            icon.setVisibility(View.GONE);
            icon.setImageDrawable(null);
        }
        layoutParams.height = height;
        itemView.setLayoutParams(layoutParams);
    }

    @Override
    public void onClick(View v) {
        scheduleClickListener.onScheduleClicked(v, getAdapterPosition());
    }

    private void resetPhoto() {
        image.setImageDrawable(null);
    }

    private int getSessionSlotHeight(@Nullable Session session, int defaultValue) {
        return session == null ? defaultValue : resources.getDimensionPixelSize(R.dimen.list_item_expanded_height);
    }

    private void setSessionPhoto(@Nullable Session session) {
        if (session != null && !session.getSpeakersList().isEmpty()) {
            String url = UrlHelper.url(session.getSpeakersList().get(0).imageUrl);
            Glide.with(itemView.getContext())
                    .load(url)
                    .override(512, 512)
                    .fitCenter()
                    .listener(new AvatarRequestListener())
                    .into(image);
        } else {
            resetPhoto();
        }
    }


    class AvatarRequestListener implements RequestListener<String, GlideDrawable> {

        @Override
        public boolean onException(Exception e, String model, Target<GlideDrawable> target, boolean isFirstResource) {
            return false;
        }

        @Override
        public boolean onResourceReady(GlideDrawable resource, String model, Target<GlideDrawable> target, boolean isFromMemoryCache, boolean isFirstResource) {
            // hide & show
            title.setVisibility(View.GONE);
            sessionTitle.setVisibility(View.VISIBLE);
            sessionTitle.setSingleLine(false);
            sessionTitle.setEllipsize(null);
            sessionTitle.setMaxLines(2);
            return false;
        }
    }
}
