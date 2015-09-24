package pl.droidcon.app.ui.decoration;

import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;

public class SpacesItemDecoration extends RecyclerView.ItemDecoration {
    private int space;

    public SpacesItemDecoration(float space) {
        this.space = (int) space;
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        int childPosition = parent.getChildAdapterPosition(view);

        if (childPosition % 2 == 0) {
            // left element
            outRect.right = (int) (space * 0.5f);
            outRect.left = space;
        } else {
            // right element
            outRect.right = space;
            outRect.left = (int) (space * 0.5f);
        }

        outRect.bottom = space;
    }
}
