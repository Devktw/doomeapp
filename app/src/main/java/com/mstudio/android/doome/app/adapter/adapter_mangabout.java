package com.mstudio.android.doome.app.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;
import android.graphics.Rect;
import android.content.Intent;
import android.os.Bundle;
import android.view.animation.AlphaAnimation;
import android.support.v7.widget.LinearLayoutManager;
import com.mstudio.android.doome.app.model.JSONData;
import com.mstudio.android.doome.app.model.JsonData2;
import com.mstudio.android.doome.app.adapter.OnItemClickListener;
import com.mstudio.android.doome.app.activity.watch_anime;
import com.mstudio.android.doome.app.R;
import java.util.Collections;
import com.mstudio.android.doome.app.model.JSONData3;
import android.view.View.OnClickListener;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.os.AsyncTask;
import com.mstudio.android.doome.app.model.model_manga1;
import com.mstudio.android.doome.app.model.model_mangahot;
import android.support.v7.widget.GridLayoutManager;
import com.mstudio.android.doome.app.model.model_mangarate;
import com.mstudio.android.doome.app.activity.watch_manga;
import com.mstudio.android.doome.app.model.model_mangarecom;
import com.mstudio.android.doome.app.model.model_mangaep;
import android.app.Activity;
import android.widget.Toast;
public class adapter_mangabout extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

	private List<model_mangaep> feedItemList2;
	private List<JSONData> feedItemList;
    private Context mContext;
	private static final int LAYOUT_ONE = 0;
	private static final int LAYOUT_TWO = 1;
	private static final int LAYOUT_TREE = 2;
	private static final int LAYOUT_FO = 3;
	private String st_nameanga,st_imagdmanga;
	private final static int FADE_DURATION = 1000; //FADE_DURATION in milliseconds

    private OnItemClickListener onItemClickListener;

	private AsyncTask<Void, Void, Void> asyncTask;
	boolean epistrue=false;

    public adapter_mangabout(Context context,List<model_mangaep>feedItemList2,List<JSONData>feedItemList) {

		this.feedItemList2 = feedItemList2;
		this.feedItemList = feedItemList;
        this.mContext = context;
    }
	@Override
	public int getItemViewType(int position)
	{
		switch (position) {
			case LAYOUT_ONE:

				return LAYOUT_ONE;
			case LAYOUT_TWO:

				return LAYOUT_TWO;
			
		}

		return 0;
	}
	@Override
	public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

		View view = null;
		RecyclerView.ViewHolder viewHolder = null;
		switch (viewType) {
			case LAYOUT_ONE:
				view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_manga_tvabout,parent,false);
				
				return new ViewHolderOne(view);
			case LAYOUT_TWO:
				view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_mangare,parent,false);
				return new ViewHolderTwo(view);


		}

		return null;
	}

	@Override
	public void onBindViewHolder(final RecyclerView.ViewHolder holder, final int position) {
		
		switch (holder.getItemViewType()) {
			case LAYOUT_ONE:
				final JSONData jsonData2 = feedItemList.get(position);
				ViewHolderOne one = (ViewHolderOne) holder;
				one.mini_story = ((Activity)mContext).getIntent().getStringExtra("mini_story");
				
				one.nameanime = ((Activity)mContext).getIntent().getStringExtra("name_manga");
				one.image_anime = ((Activity)mContext).getIntent().getStringExtra("image_manga");
				one.profile_image = ((Activity)mContext).getIntent().getStringExtra("profile");
				one.yearr = ((Activity)mContext).getIntent().getStringExtra("year");
				one.formationn = ((Activity)mContext).getIntent().getStringExtra("formation");
				one.sesonn = ((Activity)mContext).getIntent().getStringExtra("seson");
				one.valuss = ((Activity)mContext).getIntent().getStringExtra("valus");
				one.sub_use = ((Activity)mContext).getIntent().getStringExtra("sub_use");
				
				if (!TextUtils.isEmpty(one.image_anime)) {
					
					Picasso.with(mContext).load(one.profile_image).fit().centerCrop()
						.into(one.profile);
				}
				if (!TextUtils.isEmpty(one.image_anime)) {
					Picasso.with(mContext).load(one.image_anime).fit().centerCrop()
						.into(one.imageView);
				}
				one.anime_name.setText(one.nameanime);
				one.tv_ministory.setText(one.mini_story);
				
				one.year.setText("ปี: "+one.yearr);
				one.valus.setText("จำนวนตอน: "+one.valuss);
				one.formation.setText("แนว: "+one.formationn);
				one.seson.setText("ซีซั่น: "+one.sesonn);
				one.tv_subuse.setText("ผู้แปล: "+one.sub_use);
				
				break;
			case LAYOUT_TWO:
				final model_mangaep jsonData = feedItemList2.get(position);
				final ViewHolderTwo two = (ViewHolderTwo) holder;
				Html.fromHtml(jsonData.getYear());
				Html.fromHtml(jsonData.getSeson());
				Html.fromHtml(jsonData.getFormation());
				Html.fromHtml(jsonData.getValus());
				Html.fromHtml(jsonData.getMini_story());
				Html.fromHtml(jsonData.getUrl());
				
				if (feedItemList2 ==null && feedItemList2.isEmpty()) {
					two.tv_current.setText("ทั้งหมด "+"0"+" ตอน");
				}else{
					
						int i = feedItemList2.size();
						String c= String.valueOf(i);
						two.tv_current.setText("ทั้งหมด "+c+" ตอน");
					

				}
				
				
				
				//one.title_formation.setText(Html.fromHtml(jsonData.getTitle_Formation()));
				two.itemView.setOnClickListener(new View.OnClickListener() {
						@Override
						public void onClick(View view) {

							final model_mangaep json = feedItemList2.get(position);


							Intent intent = new Intent(mContext, watch_manga.class);
							intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
							intent.putExtra("name_manga", json.getName_manga());
							intent.putExtra("image_manga", json.getImage_manga());
							intent.putExtra("year", json.getYear());
							intent.putExtra("formation", json.getFormation());
							intent.putExtra("valus", json.getValus());
							intent.putExtra("seson", json.getSeson());
							intent.putExtra("mini_story", json.getMini_story());
							intent.putExtra("url_ep", json.getUrl());
							intent.putExtra("subtitle", json.getSubtitle());
							mContext.startActivity(intent);



						}
					});
				new Handler().postDelayed(new Runnable() {
						public void run() {
							final adapter_manganes_about adapter_nes_mangahot = new adapter_manganes_about(mContext,feedItemList2);
							two.recmain.setLayoutManager(new LinearLayoutManager(mContext, RecyclerView.VERTICAL, false));
							two.recmain.setHasFixedSize(true);
							two.recmain.setAdapter(adapter_nes_mangahot);
							two.recmain.setNestedScrollingEnabled(false);

						}
					}, 50);
				
			
				break;
		}



	}

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    @Override
    public int getItemCount() {
        return 2;
    }
	public class ViewHolderOne extends RecyclerView.ViewHolder {
		final ImageView imageView;
		final ImageView profile;
		final TextView tv_ministory;
        protected TextView anime_name;
		protected TextView year;
		protected TextView formation;
		protected TextView valus;
		protected TextView tv_subuse;
		protected TextView seson;
		
		
		String  image_anime, nameanime,yearr,formationn,valuss,sesonn,mini_story,profile_image,sub_use;
		

		public ViewHolderOne(View itemView) {
			super(itemView);
			profile = (ImageView) itemView.findViewById(R.id.image_profile);
			tv_ministory = (TextView) itemView.findViewById(R.id.tv_ministory);
			tv_subuse = (TextView) itemView.findViewById(R.id.sub_use);
					
			imageView = (ImageView) itemView.findViewById(R.id.image_manga);
            this.anime_name = (TextView) itemView.findViewById(R.id.name_manga);
			this.year = (TextView) itemView.findViewById(R.id.year);
            this.formation = (TextView) itemView.findViewById(R.id.formation);
			this.valus = (TextView) itemView.findViewById(R.id.valus);
            this.seson = (TextView) itemView.findViewById(R.id.seson);
				
		}
	}
	public class ViewHolderTwo extends RecyclerView.ViewHolder {

		public TextView name;
		protected TextView tv_current;
		private RecyclerView recmain;

		final TextView title_formation;

		public ViewHolderTwo(View itemView) {
			super(itemView);
			tv_current = (TextView) itemView.findViewById(R.id.tv_current);
			
			this.recmain = (RecyclerView) itemView.findViewById(R.id.recy);
            this.title_formation = (TextView) itemView.findViewById(R.id.title_formation);
		}

	}
}
