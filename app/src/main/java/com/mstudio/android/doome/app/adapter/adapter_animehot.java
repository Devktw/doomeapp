package com.mstudio.android.doome.app.adapter;

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
import com.mstudio.android.doome.app.model.JSONData;
import com.mstudio.android.doome.app.adapter.OnItemClickListener;
import com.mstudio.android.doome.app.R;
import com.mstudio.android.doome.app.activity.watch_anime;
import java.util.ArrayList;
import java.util.Locale;
import android.text.SpannableString;
import java.util.regex.Pattern;
import java.util.regex.Matcher;
import android.text.style.ForegroundColorSpan;
import android.support.v4.content.ContextCompat;
import android.text.Spanned;
import android.text.SpannableStringBuilder;
import android.graphics.Color;
import android.text.Spannable;
import com.mstudio.android.doome.app.activity.watch_manga;
import com.xeoh.android.texthighlighter.TextHighlighter;
import android.media.tv.TvContract;

public class adapter_animehot extends RecyclerView.Adapter<ViewHolderHot> {
    private List<JSONData> feedItemList;
    private Context mContext;
	private List<JSONData> array=null;
    private OnItemClickListener onItemClickListener;
	private static adapter_animehot instance;
    public adapter_animehot(Context context, List<JSONData> feedItemList) {
        this.feedItemList = feedItemList;
        this.mContext = context;
        instance=this;
		this.array = new ArrayList<JSONData>();
		this.array.addAll(feedItemList);
		
    }
    
	
    @Override
    public ViewHolderHot onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.list_item_animehot, null);
        ViewHolderHot viewHolder = new ViewHolderHot(view,feedItemList);
        return viewHolder;
    }
    
    @Override
    public void onBindViewHolder(ViewHolderHot customViewHolder, final int position) {
        final JSONData jsonData = feedItemList.get(position);

        //Render image using Picasso library
        if (!TextUtils.isEmpty(jsonData.getImage_anime())) {
            Picasso.with(mContext).load(jsonData.getImage_anime()).placeholder(R.drawable.ic_present2).fit().centerCrop()
				.into(customViewHolder.imageView_hot);
        }
	
		
		
        customViewHolder.anime_name.setText(jsonData.getName_anime());
		
		
		if(jsonData.getType().equals("anime")){
			customViewHolder.subuse.setVisibility(View.GONE);
		}
       	customViewHolder.year.setText(Html.fromHtml("ปี: "+jsonData.getYear()));
		customViewHolder.valus.setText(Html.fromHtml("จำนวนตอน: "+jsonData.getValus()));
		customViewHolder.formation.setText(Html.fromHtml("แนว: "+jsonData.getFormation()));
		customViewHolder.seson.setText(Html.fromHtml("ซีซั่น: "+jsonData.getSeson()));
		Html.fromHtml(jsonData.getUrl());
		if(jsonData.getType().equals("manga")){
			customViewHolder.anime_name.setText(Html.fromHtml(jsonData.getName_manga()));
			Picasso.with(mContext).load(jsonData.getImage_manga()).placeholder(R.drawable.ic_present).fit().centerCrop()
				.into(customViewHolder.imageView_hot);
				customViewHolder.subuse.setVisibility(View.VISIBLE);
			customViewHolder.subuse.setText(Html.fromHtml("ผู้แปล: "+jsonData.getSub_use()));
			
		}
		
		customViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View view) {

					final JSONData json2 = feedItemList.get(position);


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

    public OnItemClickListener getOnItemClickListener() {
        return onItemClickListener;
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    @Override
    public int getItemCount() {
        return (null != feedItemList ? feedItemList.size() : 0);
    }
    
    
    public static adapter_animehot getInstance() {
        return instance;
    }
    
    public void update(){
        notifyDataSetChanged();
    }
    
}
