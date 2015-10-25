package pl.droidcon.app.ui.adapter;


import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import pl.droidcon.app.R;
import pl.droidcon.app.model.common.Slot;

public class ScheduleAdapter extends RecyclerView.Adapter<ScheduleViewHolder> {

    private List<Slot> slots;

    private ScheduleViewHolder.ScheduleClickListener scheduleClickListener;

    public ScheduleAdapter(List<Slot> slots, ScheduleViewHolder.ScheduleClickListener scheduleClickListener) {
        this.slots = slots;
        this.scheduleClickListener = scheduleClickListener;
    }

    @Override
    public ScheduleViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.slot_view, parent, false);
        return new ScheduleViewHolder(view, scheduleClickListener);
    }

    @Override
    public void onBindViewHolder(ScheduleViewHolder holder, int position) {
        holder.attachSlot(getSlot(position));
    }

    @Override
    public int getItemCount() {
        return slots.size();
    }

    public Slot getSlot(int position) {
        return slots.get(position);
    }
}
