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
import com.mstudio.android.doome.app.model.model_readmanga;
import com.squareup.picasso.Target;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import com.squareup.picasso.Callback;
import android.widget.ProgressBar;
import com.mstudio.android.doome.app.activity.read_manga;
import com.squareup.picasso.MemoryPolicy;
import java.io.FileOutputStream;
import java.io.ByteArrayOutputStream;
import com.bumptech.glide.Glide;
import android.widget.RelativeLayout;

import com.bumptech.glide.load.DecodeFormat;
import android.content.SharedPreferences;


public class adapter_readmanga extends RecyclerView.Adapter<adapter_readmanga.CustomViewHolder> {
    private List<model_readmanga> feedItemList2;
    private Context mContext;
	private final static int FADE_DURATION = 1000; //FADE_DURATION in milliseconds

    private OnItemClickListener onItemClickListener;

	private Picasso picasso;

    public adapter_readmanga(Context context, List<model_readmanga> feedItemList2) {
        this.feedItemList2 = feedItemList2;
        this.mContext = context;
		
    }

    @Override
    public CustomViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.list_read_manga, null);
        CustomViewHolder viewHolder = new CustomViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final CustomViewHolder customViewHolder,final int position) {
        final model_readmanga jsonData2 = feedItemList2.get(position);
		String a4 = jsonData2.getView_a4();
		
        //Render image using Picasso library
        if (!TextUtils.isEmpty(jsonData2.getView_manga())) {
			Picasso.with(mContext).load(jsonData2.getView_manga())
				.into(customViewHolder.image_view,new Callback() {

					@Override
					public void onError() {
						read_manga.getInstance().proMethod();
					}

					@Override
					public void onSuccess() {
						customViewHolder.resize_prigress.setVisibility(View.GONE);
						read_manga.getInstance().proMethod();
					}


				});
			}
		customViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View view) {

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
        final ImageView image_view;
		final RelativeLayout resize_prigress;
        public CustomViewHolder(View view) {
            super(view);
			resize_prigress=itemView.findViewById(R.id.resize_progress);
            image_view=itemView.findViewById(R.id.image_view);
        }
    }
}
