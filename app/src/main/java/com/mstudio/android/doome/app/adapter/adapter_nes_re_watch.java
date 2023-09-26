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
import com.mstudio.android.doome.app.R;
import com.mstudio.android.doome.app.model.JSONData;
import com.mstudio.android.doome.app.model.JsonData2;
import com.mstudio.android.doome.app.model.JSONData3;

import com.mstudio.android.doome.app.adapter.OnItemClickListener;
import com.mstudio.android.doome.app.activity.watch_anime;
import android.app.Activity;
import java.util.ArrayList;
import android.widget.RelativeLayout;
import com.mstudio.android.doome.app.activity.watch_manga;
public class adapter_nes_re_watch extends RecyclerView.Adapter<adapter_nes_re_watch.CustomViewHolder> {
    private List<JSONData3> feedItemList3;
    private Context mContext;
	private final static int FADE_DURATION = 1000; //FADE_DURATION in milliseconds
	private List<JSONData3> array=null;
    private OnItemClickListener onItemClickListener;
	private static adapter_nes_re_watch instance;
	String nameanime ;
    public adapter_nes_re_watch(Context context, List<JSONData3> feedItemList3) {
        this.feedItemList3 = feedItemList3;
        this.mContext = context;
		
		nameanime= ((Activity)mContext).getIntent().getStringExtra("name_anime");
		
	
    }

    @Override
    public CustomViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.list_item_hori, null);
        CustomViewHolder viewHolder = new CustomViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final CustomViewHolder customViewHolder,final int position) {
        final JSONData3 Jsondata3 = feedItemList3.get(position);

        //Render image using Picasso library
        if (!TextUtils.isEmpty(Jsondata3.getImage_anime())) {
            Picasso.with(mContext).load(Jsondata3.getImage_anime()).placeholder(R.drawable.ic_present).fit().centerCrop()
				.into(customViewHolder.image_anime);
        }
		
        //Setting text view title
        customViewHolder.name_anime.setText(Html.fromHtml(Jsondata3.getName_anime()));
		customViewHolder.subtitle.setText(Html.fromHtml(Jsondata3.getSubtitle()));
		if(Jsondata3.getStatus().equals("up")){
			customViewHolder.valushome.setText(Html.fromHtml(Jsondata3.getValus()+" ตอน"+" • "+"อัปเดต"));
		}
		if(Jsondata3.getStatus().equals("soon")){
			customViewHolder.valushome.setText(Html.fromHtml(Jsondata3.getValus()+" ตอน"+" • "+"เร็วๆนี้"));
		}
		if(Jsondata3.getStatus().equals("end")){
			customViewHolder.valushome.setText(Html.fromHtml(Jsondata3.getValus()+" ตอน"+" • "+"จบแล้ว"));
		}
		if(Jsondata3.getPackage1().equals("free")){
			customViewHolder.free.setVisibility(View.VISIBLE);
			customViewHolder.premium.setVisibility(View.GONE);
		}
		if(Jsondata3.getPackage1().equals("premium")){
			customViewHolder.free.setVisibility(View.GONE);
			customViewHolder.premium.setVisibility(View.VISIBLE);
		}
		
		customViewHolder.formation.setText(Html.fromHtml("แนว: "+Jsondata3.getFormation()));
		customViewHolder.seson.setText(Html.fromHtml("ซีซั่น: "+Jsondata3.getSeson()));
		if(Jsondata3.getType().equals("manga")){
			customViewHolder.name_anime.setText(Html.fromHtml(Jsondata3.getName_manga()));
			Picasso.with(mContext).load(Jsondata3.getImage_manga()).placeholder(R.drawable.ic_present).fit().centerCrop()
				.into(customViewHolder.image_anime);
		}
		Html.fromHtml(Jsondata3.getYear());
		Html.fromHtml(Jsondata3.getSeson());
		Html.fromHtml(Jsondata3.getFormation());
		Html.fromHtml(Jsondata3.getValus());
		Html.fromHtml(Jsondata3.getMini_story());
		Html.fromHtml(Jsondata3.getUrl());

		customViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View view) {

					final JSONData3 json2 = feedItemList3.get(position);

					if(json2.getType().equals("manga")){
						Intent intent = new Intent(mContext, watch_manga.class);
						intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
						intent.putExtra("name_manga", json2.getName_manga());
						intent.putExtra("image_manga", json2.getImage_manga());
						intent.putExtra("year", json2.getYear());
						intent.putExtra("formation", json2.getFormation());
						intent.putExtra("valus", json2.getValus());
						intent.putExtra("seson", json2.getSeson());
						intent.putExtra("mini_story", json2.getMini_story());
						intent.putExtra("url_ep", json2.getUrl());
						intent.putExtra("subtitle", json2.getSubtitle());
						intent.putExtra("profile", json2.getProfile());
						intent.putExtra("sub_use", json2.getSub_use());
						intent.putExtra("status", json2.getStatus());
						intent.putExtra("package1", json2.getPackage1());
						mContext.startActivity(intent);
					}
					if(json2.getType().equals("anime")){
						Intent intent = new Intent(mContext, watch_anime.class);

						intent.putExtra("name_anime", json2.getName_anime());
						intent.putExtra("image_anime", json2.getImage_anime());
						intent.putExtra("year", json2.getYear());
						intent.putExtra("formation", json2.getFormation());
						intent.putExtra("valus", json2.getValus());
						intent.putExtra("seson", json2.getSeson());
						intent.putExtra("mini_story", json2.getMini_story());
						intent.putExtra("url_ep", json2.getUrl());
						intent.putExtra("status", json2.getStatus());
						intent.putExtra("package1", json2.getPackage1());
						mContext.startActivity(intent);
					}
					
				}
			});

    }
	public static adapter_nes_re_watch getInstance() {
        return instance;
    }
	
	
			

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    @Override
    public int getItemCount() {
        return (null != feedItemList3 ? feedItemList3.size() : 0);
    }

    class CustomViewHolder extends RecyclerView.ViewHolder {
        protected ImageView image_anime;
        protected TextView name_anime;
		protected TextView subtitle;
		protected TextView valushome;
		protected TextView seson;
		protected TextView formation;
		protected RelativeLayout free;
		protected RelativeLayout premium;
        public CustomViewHolder(View view) {
            super(view);
			this.free = (RelativeLayout) view.findViewById(R.id.free);
			this.premium = (RelativeLayout) view.findViewById(R.id.premium);
			
            this.image_anime = (ImageView) view.findViewById(R.id.image_anime);
            this.name_anime = (TextView) view.findViewById(R.id.name_anime);
			this.subtitle = (TextView) view.findViewById(R.id.subtitle);
			this.valushome = (TextView) view.findViewById(R.id.valus_home);
			this.formation = (TextView) view.findViewById(R.id.formation);
			this.seson = (TextView) view.findViewById(R.id.seson);
        }
    }
}
