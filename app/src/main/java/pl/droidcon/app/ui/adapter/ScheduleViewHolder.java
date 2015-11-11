package pl.droidcon.app.ui.adapter;

import android.content.res.Resources;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
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
    FrameLayout clickable;

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
        if (Slot.Type.SESSION != slot.getSlotType()) {
            image.setImageResource(R.drawable.droidcon_krakow_logo);
            layoutParams.height = resources.getDimensionPixelSize(R.dimen.list_item_height);
        } else if (slot.getSession() != null) {
            layoutParams.height = resources.getDimensionPixelSize(R.dimen.list_item_expanded_height);
            setSessionPhoto(slot.getSession());
        } else {
            image.setImageDrawable(null);
            layoutParams.height = resources.getDimensionPixelSize(R.dimen.list_item_height);
        }
        itemView.setLayoutParams(layoutParams);
    }

    @Override
    public void onClick(View v) {
        scheduleClickListener.onScheduleClicked(v, getAdapterPosition());
    }

    private void setSessionPhoto(@NonNull Session session) {
        List<Speaker> realSpeakerList = session.getSpeakersList();
        if (!realSpeakerList.isEmpty()) {
            String url = UrlHelper.url(realSpeakerList.get(0).imageUrl);
            Glide.with(itemView.getContext())
                    .load(url)
                    .override(512, 512)
                    .fitCenter()
                    .into(image);
        } else {
            image.setImageDrawable(null);
        }
    }

}
