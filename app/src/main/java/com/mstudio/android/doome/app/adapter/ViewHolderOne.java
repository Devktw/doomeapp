package com.mstudio.android.doome.app.adapter;
import android.content.SharedPreferences;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.mstudio.android.doome.app.R;
import android.content.Context;
import android.app.Activity;
import com.mstudio.android.doome.app.LinearLayoutManagerWithSmoothScroller;

public class ViewHolderOne extends RecyclerView.ViewHolder {


	final RecyclerView recmain;
	final RelativeLayout seeall;
	final TextView name_anime;
	private Context mContext;
	final TextView tv_formation;
	final TextView tv_count_up;
	adapter_nes_ep_re adapter;
	final TextView tv_count;
	private static ViewHolderOne instance;
	final LinearLayout show_formation;
	String  image_anime, nameanime,year,formation,valus,seson,mini_story,status;
	public ViewHolderOne(View itemView) {
		super(itemView);
		instance=this;
		mContext=itemView.getContext();
		this.recmain = (RecyclerView) itemView.findViewById(R.id.recy_ep);
		this.seeall = (RelativeLayout) itemView.findViewById(R.id.see_all);
		this.tv_count = (TextView) itemView.findViewById(R.id.tv_count);
		this.tv_count_up = (TextView) itemView.findViewById(R.id.tv_count_up);
		
		this.tv_formation = (TextView) itemView.findViewById(R.id.formation);
		this.show_formation=(LinearLayout) itemView.findViewById(R.id.show_formation);
		this.name_anime = (TextView) itemView.findViewById(R.id.name_recom_anime);
		recmain.setLayoutManager(new LinearLayoutManagerWithSmoothScroller(mContext, RecyclerView.HORIZONTAL, false));
		
	}
	public static ViewHolderOne getInstance() {
        return instance;
    }
	
	
	public void readap() {
		
		SharedPreferences settings =mContext.getSharedPreferences("readap", 0);
		final int statese = settings.getInt("position", 0); //0 is the default value

		LinearLayoutManagerWithSmoothScroller lManager = (LinearLayoutManagerWithSmoothScroller) recmain.getLayoutManager();
		lManager.scrollToPositionWithOffset(statese, 0);
		
	}
	
}
