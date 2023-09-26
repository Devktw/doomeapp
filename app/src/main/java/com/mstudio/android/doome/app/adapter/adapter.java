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
import android.widget.Toast;
import android.os.Parcelable;
import java.util.ArrayList;
import android.content.SharedPreferences;
import android.widget.RelativeLayout;
import com.mstudio.android.doome.app.activity.anime_hot;
import android.support.v7.widget.GridLayoutManager;
import android.widget.ProgressBar;

public class adapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private List<JSONData> feedItemList;
	private List<JsonData2> feedItemList2;
	private List<JSONData3> feedItemList3;
    private Context mContext;
	private static final int LAYOUT_ONE = 0;
	private static final int LAYOUT_TWO = 1;
	private static final int LAYOUT_TREE = 2;
	private final static int FADE_DURATION = 1000; //FADE_DURATION in milliseconds

    private OnItemClickListener onItemClickListener;
	private RecyclerView.RecycledViewPool recycledViewPool;
	
	private AsyncTask<Void, Void, Void> asyncTask;


    public adapter(Context context, List<JSONData> feedItemList,List<JsonData2>feedItemList2,List<JSONData3>feedItemList3) {
        this.feedItemList = feedItemList;
		this.feedItemList2 = feedItemList2;
		this.feedItemList3 = feedItemList3;
        this.mContext = context;
		this.recycledViewPool = new RecyclerView.RecycledViewPool();
	
    }
	@Override
	public int getItemViewType(int position)
	{
		switch (position) {
			case LAYOUT_ONE:

				return LAYOUT_ONE;
			case LAYOUT_TWO:

				return LAYOUT_TWO;
			case LAYOUT_TREE:

				return LAYOUT_TREE;
		}

		return 3;
	}
	@Override
	public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

		View view = null;
		RecyclerView.RecycledViewPool viewHolder = null;
		switch (viewType) {
			case LAYOUT_ONE:
				mContext = parent.getContext();
				view = LayoutInflater.from(mContext).inflate(R.layout.list_recom, parent, false);
				final ViewHolderOne viewHolderone = new ViewHolderOne(view);
				LinearLayoutManager lManager
					= new LinearLayoutManager(mContext, LinearLayoutManager.HORIZONTAL, false);
				viewHolderone.recycom.setLayoutManager(lManager);
				
			    viewHolderone.recycom.setRecycledViewPool(recycledViewPool);
				final adapter_nes_re  adapter_nes_re = new adapter_nes_re(mContext, feedItemList3);
				viewHolderone.recycom.setHasFixedSize(true);


				viewHolderone.recycom.setLayoutManager(new LinearLayoutManager(mContext, RecyclerView.HORIZONTAL, false));

				viewHolderone.recycom.setAdapter(adapter_nes_re);
				viewHolderone.refresh.setOnClickListener(new OnClickListener(){

						@Override
						public void onClick(View p1) {
							Collections.shuffle(feedItemList3);
							adapter_nes_re.notifyDataSetChanged();
						}
					});
				return viewHolderone;
			case LAYOUT_TWO:
				mContext = parent.getContext();
				view = LayoutInflater.from(mContext).inflate(R.layout.list_main, parent, false);
				ViewHolderTwo viewHoldertwo = new ViewHolderTwo(view);
				LinearLayoutManager lManager2
					= new LinearLayoutManager(mContext, LinearLayoutManager.HORIZONTAL, false);
				
				adapter_nes adapter_nes = new adapter_nes(mContext, feedItemList2);
				viewHoldertwo.recmain.setHasFixedSize(true);
				viewHoldertwo.recmain.setLayoutManager(new LinearLayoutManager(mContext, RecyclerView.HORIZONTAL, false));

				viewHoldertwo.recmain.setAdapter(adapter_nes);		
				viewHoldertwo.recmain.setLayoutManager(lManager2);
				
				viewHoldertwo.recmain.setRecycledViewPool(recycledViewPool);
				viewHoldertwo.open_hotanime.setOnClickListener(new OnClickListener(){

						@Override
						public void onClick(View p1) {
							Intent i = new Intent(mContext,anime_hot.class);
							mContext.startActivity(i);
						}


					});
				
				return viewHoldertwo;
			case LAYOUT_TREE:
				mContext = parent.getContext();
			     view = LayoutInflater.from(mContext).inflate(R.layout.list_general, parent, false);
				ViewHolderTree viewHoldertree = new ViewHolderTree(view);
				
				final adapter_nes_general adapter = new adapter_nes_general(mContext,feedItemList);
				int spanCountt = 3; // 3 columns
				int spacingg = 5; // 50px
				boolean includeEdgee = true;

				viewHoldertree.rerate.setHasFixedSize(true);
				viewHoldertree.rerate.addItemDecoration(new spaceitem(spanCountt, spacingg, includeEdgee));
				GridLayoutManager lManager3
					= new GridLayoutManager(mContext,3);
				
				viewHoldertree.rerate.setLayoutManager(new GridLayoutManager(mContext, 3));


				viewHoldertree.rerate.setAdapter(adapter);
				viewHoldertree.rerate.setNestedScrollingEnabled(false);
				
				viewHoldertree.rerate.setRecycledViewPool(recycledViewPool);
				
				return viewHoldertree;
		}
		

		return null;
	}

	@Override
	public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
		final JSONData jsonData = feedItemList.get(position);
		switch (holder.getItemViewType()) {
			case LAYOUT_ONE:
				final ViewHolderOne one = (ViewHolderOne) holder;
				Html.fromHtml(jsonData.getYear());
				Html.fromHtml(jsonData.getSeson());
				Html.fromHtml(jsonData.getFormation());
				Html.fromHtml(jsonData.getValus());
				Html.fromHtml(jsonData.getMini_story());
				Html.fromHtml(jsonData.getUrl());
				//two.title_recom.setText(Html.fromHtml(jsonData.getRecom()));
				one.itemView.setOnClickListener(new View.OnClickListener() {
						@Override
						public void onClick(View view) {

							final JSONData json = feedItemList.get(position);


							Intent intent = new Intent(mContext, watch_anime.class);
							intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
							intent.putExtra("name_anime", json.getName_anime());
							intent.putExtra("image_anime", json.getImage_anime());
							intent.putExtra("year", json.getYear());
							intent.putExtra("formation", json.getFormation());
							intent.putExtra("valus", json.getValus());
							intent.putExtra("seson", json.getSeson());
							intent.putExtra("mini_story", json.getMini_story());
							intent.putExtra("url_ep", json.getUrl());

							mContext.startActivity(intent);



						}
					});


				
				
				break;

			case LAYOUT_TWO:
				
				
				ViewHolderTwo two = (ViewHolderTwo) holder;
				Html.fromHtml(jsonData.getYear());
				Html.fromHtml(jsonData.getSeson());
				Html.fromHtml(jsonData.getFormation());
				Html.fromHtml(jsonData.getValus());
				Html.fromHtml(jsonData.getMini_story());
				Html.fromHtml(jsonData.getUrl());
				//one.title_formation.setText(Html.fromHtml(jsonData.getTitle_Formation()));
				two.itemView.setOnClickListener(new View.OnClickListener() {
						@Override
						public void onClick(View view) {

							final JSONData json = feedItemList.get(position);


							Intent intent = new Intent(mContext, watch_anime.class);
							intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
							intent.putExtra("name_anime", json.getName_anime());
							intent.putExtra("image_anime", json.getImage_anime());
							intent.putExtra("year", json.getYear());
							intent.putExtra("formation", json.getFormation());
							intent.putExtra("valus", json.getValus());
							intent.putExtra("seson", json.getSeson());
							intent.putExtra("mini_story", json.getMini_story());
							intent.putExtra("url_ep", json.getUrl());
							mContext.startActivity(intent);



						}
					});
				
				
				
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
	
	public class ViewHolderOne extends RecyclerView.ViewHolder {

		private RecyclerView recycom;

		private TextView title_recom;
		private ImageView refresh;
		private Parcelable recyclerViewState;

		public ViewHolderOne(View itemView) {
			super(itemView);

			this.recycom = (RecyclerView) itemView.findViewById(R.id.recy_recom);
            this.title_recom = (TextView) itemView.findViewById(R.id.title_recom);
			this.refresh = (ImageView) itemView.findViewById(R.id.refresh);


		}

	
	}


	//****************  VIEW HOLDER 2 ******************//

	public class ViewHolderTwo extends RecyclerView.ViewHolder {
		
		
		public TextView name;

		private RecyclerView recmain;

		final TextView title_formation;
		protected RelativeLayout open_hotanime;
		public ViewHolderTwo(View itemView) {
			super(itemView);
			this.open_hotanime = (RelativeLayout) itemView.findViewById(R.id.hot_anime);

			this.recmain = (RecyclerView) itemView.findViewById(R.id.recy);
            this.title_formation = (TextView) itemView.findViewById(R.id.title_formation);

		}
		
		
	}
	public class ViewHolderTree extends RecyclerView.ViewHolder {

		public TextView name;

		private RecyclerView rerate;

		final TextView title_formation;
		//private ProgressBar progress;
		public ViewHolderTree(View itemView) {
			super(itemView);
			this.rerate = (RecyclerView) itemView.findViewById(R.id.recy);
			//this.progress = (ProgressBar) itemView.findViewById(R.id.progress);
			
			this.title_formation = (TextView) itemView.findViewById(R.id.title_formation);
		}
	}
}

