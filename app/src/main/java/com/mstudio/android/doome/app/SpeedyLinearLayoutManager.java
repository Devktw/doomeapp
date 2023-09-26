package com.mstudio.android.doome.app;
import android.support.v7.widget.LinearLayoutManager;
import android.content.Context;
import android.util.AttributeSet;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.LinearSmoothScroller;
import android.util.DisplayMetrics;
import android.graphics.PointF;
import android.content.SharedPreferences;
import android.app.Activity;

public class SpeedyLinearLayoutManager extends LinearLayoutManager {
	private Context mContext;
    private static final float MILLISECONDS_PER_INCH = 5f; //default is 25f (bigger = slower)
	
    public SpeedyLinearLayoutManager(Context context) {
		
        super(context);
		this.mContext=context;
    }

    public SpeedyLinearLayoutManager(Context context, int orientation, boolean reverseLayout) {
        super(context, orientation, reverseLayout);
    }

    public SpeedyLinearLayoutManager(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    @Override
    public void smoothScrollToPosition(RecyclerView recyclerView, RecyclerView.State state, int position) {

        final LinearSmoothScroller linearSmoothScroller = new LinearSmoothScroller(recyclerView.getContext()) {

            @Override
            public PointF computeScrollVectorForPosition(int targetPosition) {
                return super.computeScrollVectorForPosition(targetPosition);
            }

            @Override
            protected float calculateSpeedPerPixel(DisplayMetrics displayMetrics) {
				//SharedPreferences mySharedPreferences =mContext.getSharedPreferences("goep", Context.MODE_PRIVATE);
				//float speed = mySharedPreferences.getFloat("speedscroll", 0f);
                return 800f / displayMetrics.densityDpi;
            }
        };

        linearSmoothScroller.setTargetPosition(position);
        startSmoothScroll(linearSmoothScroller);
    }
}
