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

import com.mstudio.android.doome.app.adapter.OnItemClickListener;
import com.mstudio.android.doome.app.activity.watch_anime;
import com.mstudio.android.doome.app.model.model_mangahot;
import com.mstudio.android.doome.app.activity.watch_manga;
public class adapter_nes_magahot extends RecyclerView.Adapter<adapter_nes_magahot.CustomViewHolder> {
    private List<model_mangahot> feedItemList2;
    private Context mContext;
	private final static int FADE_DURATION = 1000; //FADE_DURATION in milliseconds

    private OnItemClickListener onItemClickListener;

	private boolean isclick;

    public adapter_nes_magahot(Context context, List<model_mangahot> feedItemList2) {
        this.feedItemList2 = feedItemList2;
        this.mContext = context;
    }

    @Override
    public CustomViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.list_item_mangahot, null);
        CustomViewHolder viewHolder = new CustomViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final CustomViewHolder customViewHolder,final int position) {
        final model_mangahot jsonData2 = feedItemList2.get(position);

        //Render image using Picasso library
        if (!TextUtils.isEmpty(jsonData2.getImage_manga())) {
			
			
            Picasso.with(mContext).load(jsonData2.getImage_manga()).placeholder(R.drawable.ic_present).fit().centerCrop()
				.into(customViewHolder.image_anime);
        }

        //Setting text view title
        customViewHolder.name_anime.setText(Html.fromHtml(jsonData2.getName_manga()));
		customViewHolder.subtitle.setText(Html.fromHtml(jsonData2.getSubtitle()));
		customViewHolder.valushome.setText(Html.fromHtml(jsonData2.getValus()));
		
		Html.fromHtml(jsonData2.getYear());
		Html.fromHtml(jsonData2.getSeson());
		Html.fromHtml(jsonData2.getFormation());
		Html.fromHtml(jsonData2.getValus());
		Html.fromHtml(jsonData2.getMini_story());
		Html.fromHtml(jsonData2.getUrl());

		customViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View view) {
					final model_mangahot json2 = feedItemList2.get(position);
				
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
					mContext.startActivity(intent);
				}
			});

    }



    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    @Override
    public int getItemCount() {
        return (null != feedItemList2 ? feedItemList2.size() : 0);
    }

    class CustomViewHolder extends RecyclerView.ViewHolder {
        protected ImageView image_anime;
        protected TextView name_anime;
		protected TextView subtitle;
		protected TextView valushome;
        public CustomViewHolder(View view) {
            super(view);
            this.image_anime = (ImageView) view.findViewById(R.id.image_anime);
            this.name_anime = (TextView) view.findViewById(R.id.name_anime);
			this.subtitle = (TextView) view.findViewById(R.id.subtitle);
			this.valushome = (TextView) view.findViewById(R.id.valus_home);
        }
    }
}
