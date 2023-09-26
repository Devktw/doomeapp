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
import com.mstudio.android.doome.app.model.model_mangarate;
import com.mstudio.android.doome.app.activity.watch_manga;
import com.mstudio.android.doome.app.model.model_mangaep;
import com.mstudio.android.doome.app.activity.read_manga;
import android.content.SharedPreferences;
import android.app.Activity;
import android.content.res.Configuration;
import android.graphics.Color;
import android.support.v4.content.res.ResourcesCompat;
import java.io.File;
import android.widget.Toast;
public class adapter_manganes_about extends RecyclerView.Adapter<adapter_manganes_about.CustomViewHolder> {
    private List<model_mangaep> feedItemList2;
    private Context mContext;
	private final static int FADE_DURATION = 1000; //FADE_DURATION in milliseconds
	private static adapter_manganes_about instance;
	
    private OnItemClickListener onItemClickListener;
	private int globalPosition=-1;

	private boolean isclick=false;
    public adapter_manganes_about(Context context, List<model_mangaep> feedItemList2) {
        this.feedItemList2 = feedItemList2;
        this.mContext = context;
		instance = this;
		
    }

    @Override
    public CustomViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.list_item_manga_about, null);
        CustomViewHolder viewHolder = new CustomViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final CustomViewHolder customViewHolder,final int position) {
        final model_mangaep jsonData2 = feedItemList2.get(position);
		if(position==globalPosition)
		{
			
			customViewHolder.name_anime.setTextColor(ResourcesCompat.getColor(mContext.getResources(), R.color.coloraccent, null));
			customViewHolder.view.setTextColor(ResourcesCompat.getColor(mContext.getResources(), R.color.coloraccent, null));
			
		}
		else

		{
			customViewHolder.name_anime.setTextColor(Color.WHITE);
			customViewHolder.view.setTextColor(Color.WHITE);


		}
        //Render image using Picasso library
        if (!TextUtils.isEmpty(jsonData2.getImage_manga())) {
			Picasso.with(mContext).load(jsonData2.getImage_manga()).fit().centerCrop()
				.into(customViewHolder.image_anime);
				
            
        }

        //Setting text view title
        customViewHolder.name_anime.setText(Html.fromHtml(jsonData2.getName_manga()));
		customViewHolder.subtitle.setText(Html.fromHtml(jsonData2.getSubtitle()));
		customViewHolder.view.setText(Html.fromHtml(jsonData2.getView()));
		
		Html.fromHtml(jsonData2.getYear());
		Html.fromHtml(jsonData2.getSeson());
		Html.fromHtml(jsonData2.getFormation());
		Html.fromHtml(jsonData2.getValus());
		Html.fromHtml(jsonData2.getMini_story());
		Html.fromHtml(jsonData2.getUrl());

		customViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View view) {
			
					
					final model_mangaep json2 = feedItemList2.get(position);
					notifyItemChanged(globalPosition);
					globalPosition=customViewHolder.getLayoutPosition();
					notifyItemChanged(globalPosition);

					Intent intent = new Intent(mContext, read_manga.class);
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
					intent.putExtra("image_view_ep", json2.getImage_view());
					
					mContext.startActivity(intent);
					
					
				}
			});

    }
	public static adapter_manganes_about getInstance() {
        return instance;
    }
	public void nextep(){
		
		if(globalPosition!=feedItemList2.size()-1){
			globalPosition++;
			
			final model_mangaep json2 = feedItemList2.get(globalPosition);
			
			notifyItemChanged(globalPosition);
			
			SharedPreferences mySharedPreferences = mContext.getSharedPreferences("goep", Context.MODE_PRIVATE);
			SharedPreferences.Editor editor = mySharedPreferences.edit();
			editor.putString("goep",json2.getImage_view());
			editor.putString("namemanga",json2.getName_manga());
			editor.commit();
			read_manga.getInstance().nextep();
		}
	}
	public void backep(){
		
		if(globalPosition!=0){
			globalPosition--;
			
			final model_mangaep json2 = feedItemList2.get(globalPosition);
			
			
			SharedPreferences mySharedPreferences = mContext.getSharedPreferences("goep", Context.MODE_PRIVATE);
			SharedPreferences.Editor editor = mySharedPreferences.edit();
			editor.putString("goep",json2.getImage_view());
			editor.putString("namemanga",json2.getName_manga());
			editor.commit();
			read_manga.getInstance().backep();
		}
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
		protected TextView view;
		protected TextView valushome;
        public CustomViewHolder(View view) {
            super(view);
			this.view = (TextView) view.findViewById(R.id.view);
			
            this.image_anime = (ImageView) view.findViewById(R.id.image_anime);
            this.name_anime = (TextView) view.findViewById(R.id.name_anime);
			this.subtitle = (TextView) view.findViewById(R.id.subtitle);
			this.valushome = (TextView) view.findViewById(R.id.valus_home);
        }
    }
}
