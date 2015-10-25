package pl.droidcon.app.ui.decoration;


import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;

public class ScheduleItemDecoration extends RecyclerView.ItemDecoration {

    private int space;

    public ScheduleItemDecoration(float space) {
        this.space = (int) space;
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        outRect.top = (int) (space * 0.5f);
        outRect.bottom = (int) (space * 0.5f);
    }
}
