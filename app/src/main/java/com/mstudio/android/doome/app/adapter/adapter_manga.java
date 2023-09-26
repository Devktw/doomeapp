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
import java.util.Timer;
import java.util.TimerTask;
public class adapter_manga extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    
	private List<model_mangahot> feedItemList2;
	private List<model_mangarate> feedItemListr;
	private List<model_mangarecom> feedItemListre;
    private Context mContext;
	private static final int LAYOUT_TWO = 0;
	private static final int LAYOUT_TREE = 1;
	private static final int LAYOUT_FO = 2;
	
	private final static int FADE_DURATION = 1000; //FADE_DURATION in milliseconds

    private OnItemClickListener onItemClickListener;

	private AsyncTask<Void, Void, Void> asyncTask;


    public adapter_manga(Context context,List<model_mangahot>feedItemList2,List<model_mangarate>feedItemListr,
	List<model_mangarecom>feedItemListre) {
        
		this.feedItemList2 = feedItemList2;
		this.feedItemListr = feedItemListr;
		this.feedItemListre = feedItemListre;
        this.mContext = context;
    }
	@Override
	public int getItemViewType(int position)
	{
		switch (position) {
			
			case LAYOUT_TWO:

				return LAYOUT_TWO;
			case LAYOUT_TREE:

				return LAYOUT_TREE;
			case LAYOUT_FO:

				return LAYOUT_FO;
			}

		return 0;
	}
	@Override
	public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

		View view = null;
		RecyclerView.ViewHolder viewHolder = null;
		switch (viewType) {
			case LAYOUT_TWO:
				view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_mangahot,parent,false);
				return new ViewHolderTwo(view);
			case LAYOUT_TREE:
				view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_mangarecom,parent,false);
				
				return new ViewHolderTree(view);
			case LAYOUT_FO:
				view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_mangarate,parent,false);

				return new ViewHolderFo(view);
				
		
				}

		return null;
	}

	@Override
	public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
		final model_mangahot jsonData = feedItemList2.get(position);
		switch (holder.getItemViewType()) {
			
			case LAYOUT_TWO:
				ViewHolderTwo one = (ViewHolderTwo) holder;
				Html.fromHtml(jsonData.getYear());
				Html.fromHtml(jsonData.getSeson());
				Html.fromHtml(jsonData.getFormation());
				Html.fromHtml(jsonData.getValus());
				Html.fromHtml(jsonData.getMini_story());
				Html.fromHtml(jsonData.getUrl());
				//one.title_formation.setText(Html.fromHtml(jsonData.getTitle_Formation()));
				
				final adapter_nes_magahot adapter_nes_mangahot = new adapter_nes_magahot(mContext,feedItemList2);
				one.recmain.setLayoutManager(new LinearLayoutManager(mContext, RecyclerView.HORIZONTAL, false));
				one.recmain.setHasFixedSize(true);
				one.recmain.setAdapter(adapter_nes_mangahot);
				break;
			case LAYOUT_TREE:
				final ViewHolderTree tree = (ViewHolderTree) holder;
				Html.fromHtml(jsonData.getYear());
				Html.fromHtml(jsonData.getSeson());
				Html.fromHtml(jsonData.getFormation());
				Html.fromHtml(jsonData.getValus());
				Html.fromHtml(jsonData.getMini_story());
				Html.fromHtml(jsonData.getUrl());
				//one.title_formation.setText(Html.fromHtml(jsonData.getTitle_Formation()));
				
				final adapter_nesmangarecon adapter_nes_mangarecom = new adapter_nesmangarecon(mContext,feedItemListre);
				int spanCountt = 3; // 3 columns
				int spacingg = 5; // 50px
				boolean includeEdgee = true;
				tree.rerate.addItemDecoration(new spaceitem(spanCountt, spacingg, includeEdgee));
				tree.rerate.setLayoutManager(new GridLayoutManager(mContext, 3));
				tree.rerate.setHasFixedSize(true);
				tree.rerate.setAdapter(adapter_nes_mangarecom);
				tree.rerate.setNestedScrollingEnabled(false);
				
				break;
			case LAYOUT_FO:
				ViewHolderFo fo = (ViewHolderFo) holder;
				Html.fromHtml(jsonData.getYear());
				Html.fromHtml(jsonData.getSeson());
				Html.fromHtml(jsonData.getFormation());
				Html.fromHtml(jsonData.getValus());
				Html.fromHtml(jsonData.getMini_story());
				Html.fromHtml(jsonData.getUrl());
				//one.title_formation.setText(Html.fromHtml(jsonData.getTitle_Formation()));
			
				final adapter_nesmangarate adapter_nes_mangarate = new adapter_nesmangarate(mContext,feedItemListr);
				int spanCount = 3; // 3 columns
				int spacing = 5; // 50px
				boolean includeEdge = true;
				fo.rerate.addItemDecoration(new spaceitem(spanCount, spacing, includeEdge));
				fo.rerate.setLayoutManager(new GridLayoutManager(mContext, 3));
				fo.rerate.setHasFixedSize(true);
				fo.rerate.setAdapter(adapter_nes_mangarate);
				fo.rerate.setNestedScrollingEnabled(false);
				break;
		}



	}

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    @Override
    public int getItemCount() {
        return 3;
    }
	
	public class ViewHolderTwo extends RecyclerView.ViewHolder {

		public TextView name;

		private RecyclerView recmain;

		final TextView title_formation;

		public ViewHolderTwo(View itemView) {
			super(itemView);
			this.recmain = (RecyclerView) itemView.findViewById(R.id.recy);
            this.title_formation = (TextView) itemView.findViewById(R.id.title_formation);
		}
		
	}
	public class ViewHolderTree extends RecyclerView.ViewHolder {

		public TextView name;

		private RecyclerView rerate;

		final TextView title_formation;

		public ViewHolderTree(View itemView) {
			super(itemView);
			this.rerate = (RecyclerView) itemView.findViewById(R.id.recy);
            this.title_formation = (TextView) itemView.findViewById(R.id.title_formation);
		}
	}
	public class ViewHolderFo extends RecyclerView.ViewHolder {

		public TextView name;

		private RecyclerView rerate;

		final TextView title_formation;

		public ViewHolderFo(View itemView) {
			super(itemView);
			this.rerate = (RecyclerView) itemView.findViewById(R.id.recy);
            this.title_formation = (TextView) itemView.findViewById(R.id.title_formation);
		}
		}
}
