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
import com.mstudio.android.doome.app.model.JSONEp;
import android.support.v4.content.res.ResourcesCompat;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.app.Activity;
import android.graphics.Color;
import com.mstudio.android.doome.app.fragment.formation_anime;
import android.os.Handler;
import android.widget.RelativeLayout;
public class adapter_nes_ep extends RecyclerView.Adapter<adapter_nes_ep.CustomViewHolder> {
    private List<JSONEp> feedItemListEp;
    private Context mContext;
	private static adapter_nes_ep instance;
	boolean isclick=false;
	private final static int FADE_DURATION = 1000; //FADE_DURATION in milliseconds
	private int globalPosition = 0;
    private OnItemClickListener onItemClickListener;
	private String epname,ep;
	String premium;
    public adapter_nes_ep(Context context, List<JSONEp> feedItemListEp) {
        this.feedItemListEp = feedItemListEp;
        this.mContext = context;
		
		
    }

    @Override
    public CustomViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.lis_item_ep_all, null);
        CustomViewHolder viewHolder = new CustomViewHolder(view);
        return viewHolder;
    }
	
    @Override
    public void onBindViewHolder(final CustomViewHolder customViewHolder,final int position) {
        final JSONEp jsonDataEp = feedItemListEp.get(position);
		SharedPreferences settings =mContext.getSharedPreferences("saveselect", 0);
		final int statese = settings.getInt("globalposition", 0); //0 is the default value
		SharedPreferences scrollto =mContext.getSharedPreferences("readap", 0);
		SharedPreferences.Editor editorto = scrollto.edit();
		editorto.putInt("position",statese);
		editorto.commit();
		
		premium = ((Activity)mContext).getIntent().getStringExtra("package1");

		if(premium.equals("premium")){
			if(feedItemListEp.size()<2){
				if(position==0){
					customViewHolder.premium.setVisibility(View.VISIBLE);
				}
			}else{
				if(position>3){
					customViewHolder.premium.setVisibility(View.VISIBLE);
				}else{
					customViewHolder.premium.setVisibility(View.GONE);

				}

			}
		}else{
			customViewHolder.premium.setVisibility(View.GONE);
		}
		
		if(position==statese)
		{
					customViewHolder.ep_name.setTextColor(ResourcesCompat.getColor(mContext.getResources(), R.color.coloraccent, null));
		}
		else

		{
			customViewHolder.ep_name.setTextColor(Color.WHITE);
			
			}
        //Render image using Picasso library
        if (!TextUtils.isEmpty(jsonDataEp.getEp_name())) {
			Picasso.with(mContext).load(jsonDataEp.getEp_image()).placeholder(R.drawable.ic_present).fit().centerCrop()
				.into(customViewHolder.ep_image);
		
        }

        //Setting text view title
        customViewHolder.ep_name.setText(Html.fromHtml(jsonDataEp.getEp_name()));
	///	customViewHolder.subtitle.setText(Html.fromHtml(jsonData2.getSubtitle()));
		//customViewHolder.ep_valus.setText(Html.fromHtml(jsonDataEp.getEp_valus()));

		

		customViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View view) {

					
					watch_anime.getInstance().resetgotime();
					globalPosition=customViewHolder.getLayoutPosition();
					notifyDataSetChanged();
					SharedPreferences settings =mContext.getSharedPreferences("saveselect", 0);
					SharedPreferences.Editor editorr = settings.edit();
					editorr.putInt("globalposition",globalPosition);
					editorr.commit();
					
					final JSONEp jsonDataEp = feedItemListEp.get(position);
					epname = jsonDataEp.getEp_name();
					ep=jsonDataEp.getEp();
					SharedPreferences mySharedPreferences = mContext.getSharedPreferences("myep_name", Context.MODE_PRIVATE);


					SharedPreferences.Editor editor = mySharedPreferences.edit();
					editor.putString("epname",epname);
					editor.putString("ep",ep);
					editor.commit();
					new Handler().postDelayed(new Runnable() {
							public void run() {
								if(premium.equals("premium")){
									if(feedItemListEp.size()<2){
										if(position==0){
											watch_anime.getInstance().checkpackage();
											watch_anime.getInstance().stopexo();
										}
									}else{
										if(position>3){
											watch_anime.getInstance().checkpackage();
											watch_anime.getInstance().stopexo();
										}else{
											watch_anime.getInstance().playurl();
											
											adapter_nes_ep_re.getInstance().myMethod();
										}
									}
								}else{
									watch_anime.getInstance().playurl();
									adapter_nes_ep_re.getInstance().myMethod();
								}
								
							}
						}, 50);
					SharedPreferences scrollto =mContext.getSharedPreferences("readap", 0);
					SharedPreferences.Editor editorto = scrollto.edit();
					editorto.putInt("position",globalPosition);
					editorto.commit();
					new Handler().postDelayed(new Runnable() {
							public void run() {
								watch_anime.getInstance().readap();
								ViewHolderOne.getInstance().readap();
							}
						}, 200);
				
					}
			});

    }

	public static adapter_nes_ep getInstance() {
        return instance;
    }

    public void myMethod() {
		notifyDataSetChanged();
		
    }

    @Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public int getItemViewType(int position) {
	
		return position;
	}
    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    @Override
    public int getItemCount() {
        return (null != feedItemListEp ? feedItemListEp.size() : 0);
    }

    class CustomViewHolder extends RecyclerView.ViewHolder {
        protected ImageView ep_image;
        protected TextView ep_name;
		protected TextView subtitle;
		protected TextView ep_valus;
		final RelativeLayout premium;
        public CustomViewHolder(View view) {
            super(view);
			this.premium = (RelativeLayout) view.findViewById(R.id.premium);
			
            this.ep_image = (ImageView) view.findViewById(R.id.ep_image);
            this.ep_name = (TextView) view.findViewById(R.id.ep_name);
			//this.subtitle = (TextView) view.findViewById(R.id.subtitle);
			//this.ep_valus = (TextView) view.findViewById(R.id.ep_valus);
        }
    }
}
