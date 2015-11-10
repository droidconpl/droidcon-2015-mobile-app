package pl.droidcon.app.ui.adapter;

import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import pl.droidcon.app.R;
import pl.droidcon.app.helper.DateTimePrinter;
import pl.droidcon.app.helper.UrlHelper;
import pl.droidcon.app.model.api.Session;
import pl.droidcon.app.model.api.Speaker;
import pl.droidcon.app.model.common.Slot;


public class ScheduleViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

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

    private ScheduleClickListener scheduleClickListener;

    public ScheduleViewHolder(View itemView, ScheduleClickListener scheduleClickListener) {
        super(itemView);
        this.scheduleClickListener = scheduleClickListener;
        ButterKnife.bind(this, itemView);
        clickable.setOnClickListener(this);
    }

    public void attachSlot(Slot slot) {
        hour.setText(DateTimePrinter.toPrintableString(slot.getDateTime()));
        title.setText(slot.getDisplayTitle());
        if (Slot.Type.SESSION != slot.getSlotType()) {
            image.setImageResource(R.drawable.droidcon_krakow_logo);
        } else {
            setSessionPhoto(slot.getSession());
        }
    }

    @Override
    public void onClick(View v) {
        scheduleClickListener.onScheduleClicked(v, getAdapterPosition());
    }

    private void setSessionPhoto(@Nullable Session session) {
        if (session == null) {
            image.setImageDrawable(null);
        } else {
            List<Speaker> realSpeakerList = session.getSpeakersList();
            if (!realSpeakerList.isEmpty()) {
                String url = UrlHelper.url(realSpeakerList.get(0).imageUrl);
                Picasso.with(itemView.getContext())
                        .load(url)
                        .into(image);
            } else {
                image.setImageDrawable(null);
            }
        }
    }

}
