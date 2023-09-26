package com.mstudio.android.doome.app.adapter;
import android.support.v7.widget.RecyclerView;
import android.graphics.Rect;
import android.view.View;

public class spaceitem extends RecyclerView.ItemDecoration {

    private int spanCount;
    private int spacing;
    private boolean includeEdge;

    public spaceitem(int spanCount, int spacing, boolean includeEdge) {
        this.spanCount = spanCount;
        this.spacing = spacing;
        this.includeEdge = includeEdge;
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        int position = parent.getChildAdapterPosition(view); // item position
        int column = position % spanCount; // item column

        if (includeEdge) {
            outRect.left = 15; // spacing - column * ((1f / spanCount) * spacing)
            outRect.right = 0; // (column + 1) * ((1f / spanCount) * spacing)

            if (position < spanCount) { // top edge
                outRect.top = spacing;
            }
            outRect.bottom = 15; // item bottom
        } else {
            outRect.left = 15; // column * ((1f / spanCount) * spacing)
            outRect.right = 0; // spacing - (column + 1) * ((1f /    spanCount) * spacing)
            if (position >= spanCount) {
                outRect.top = spacing; // item top
            }
        }
    }
}
