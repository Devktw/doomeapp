package com.mstudio.android.doome.app.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Parcelable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.mstudio.android.doome.app.R;
import com.mstudio.android.doome.app.activity.watch_anime;
import com.mstudio.android.doome.app.adapter.OnItemClickListener;
import com.mstudio.android.doome.app.model.JSONData3;
import com.mstudio.android.doome.app.model.JSONEp;
import java.util.List;
import android.os.Handler;
import com.mstudio.android.doome.app.model.JsonData2;
import java.util.Collections;
import com.mstudio.android.doome.app.LinearLayoutManagerWithSmoothScroller;
public class adapter_formation extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    
	private List<JSONEp> feedItemListEp;
	private List<JSONData3> feedItemList3;
    private Context mContext;
	private static final int LAYOUT_ONE = 0;
	private static final int LAYOUT_TWO = 1;
	private Activity mActivity;
	Parcelable state;
	private final static int FADE_DURATION = 1000; //FADE_DURATION in milliseconds

    private OnItemClickListener onItemClickListener;
	private static adapter_formation instance;
	private AsyncTask<Void, Void, Void> asyncTask;
	private RecyclerView.RecycledViewPool recycledViewPool;
	final boolean isclick = false;
    public adapter_formation(Context context, List<JSONData3> feedItemList3,List<JSONEp>feedItemListEp) {
		this.feedItemList3 = feedItemList3;
		this.feedItemListEp = feedItemListEp;
        this.mContext = context;
		instance = this;
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
		}

		return 2;
	}
	@Override
	public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

		View view = null;
		RecyclerView.ViewHolder viewHolder = null;
		switch (viewType) {
			case LAYOUT_ONE:
				
				
				mContext = parent.getContext();
				view = LayoutInflater.from(mContext).inflate(R.layout.list_recom_ep, parent, false);
				final ViewHolderOne one = new ViewHolderOne(view);
				LinearLayoutManagerWithSmoothScroller lManager
					= new LinearLayoutManagerWithSmoothScroller(mContext, LinearLayoutManagerWithSmoothScroller.HORIZONTAL, false);
				one.recmain.setLayoutManager(lManager);

			    one.recmain.setRecycledViewPool(recycledViewPool);
				

				
				one.nameanime = ((Activity)mContext).getIntent().getStringExtra("name_anime");
				one.formation = ((Activity)mContext).getIntent().getStringExtra("formation");
				one.seson = ((Activity)mContext).getIntent().getStringExtra("seson");
				one.status = ((Activity)mContext).getIntent().getStringExtra("status");

				one.name_anime.setText(one.nameanime);
				one.tv_formation.setText("แนว: "+one.formation+" • "+"ซีซั่น: "+one.seson);
				if(one.status.equals("up")){
					one.tv_formation.setText("แนว: "+one.formation+" • "+"ซีซั่น: "+one.seson+" • "+"อัปเดต");
					if (feedItemListEp !=null && !feedItemListEp.isEmpty()) {
						one.tv_count.setText("ทั้งหมด "+feedItemListEp.size()+" ตอน");
						one.tv_count_up.setText("อัปเดตถึงตอนที่ "+feedItemListEp.size());
					}else{

					}
				}
				if(one.status.equals("end")){
					one.tv_formation.setText("แนว: "+one.formation+" • "+"ซีซั่น: "+one.seson+" • "+"จบแล้ว");
					if (feedItemListEp !=null && !feedItemListEp.isEmpty()) {
						one.tv_count.setText("ทั้งหมด "+feedItemListEp.size()+" ตอน");
						one.tv_count_up.setText("ทั้งหมด");
					}else{

					}
				}

				one.itemView.setOnClickListener(new View.OnClickListener() {
						@Override
						public void onClick(View view) {


						}
					});
			    adapter_nes_ep_re  adapter_nes_ep_re = new adapter_nes_ep_re(mContext, feedItemListEp);
				one.recmain.setHasFixedSize(true);
				one.recmain.setAdapter(adapter_nes_ep_re);
				
				if (feedItemListEp !=null && !feedItemListEp.isEmpty()) {
					one.tv_count.setText("ทั้งหมด "+feedItemListEp.size()+" ตอน");

				}else{

				}
				one.show_formation.setOnClickListener(new OnClickListener(){

						@Override
						public void onClick(View p1) {
							watch_anime.getInstance().show_formation();

						}
					});

				one.seeall.setOnClickListener(new OnClickListener(){

						@Override
						public void onClick(View p1) {
							watch_anime.getInstance().proMethod();

						}
					});
				return one;
				
				
			case LAYOUT_TWO:
				mContext = parent.getContext();
				view = LayoutInflater.from(mContext).inflate(R.layout.list_recom_watch, parent, false);
				
				final ViewHolderTwo two = new ViewHolderTwo(view);
				
				LinearLayoutManager lManager2
					= new LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false);
				two.recycom.setLayoutManager(lManager2);

			    two.recycom.setRecycledViewPool(recycledViewPool);
				
				final adapter_nes_re_watch  adapter_nes_re_watch = new adapter_nes_re_watch(mContext, feedItemList3);
				two.recycom.setLayoutManager(new LinearLayoutManager(mContext, RecyclerView.VERTICAL, false));
				two.recycom.setHasFixedSize(true);
				two.recycom.setAdapter(adapter_nes_re_watch);
				
				
				return two;
		}

		return null;
	}
	
	@Override
	public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
		
		switch (holder.getItemViewType()) {
			case LAYOUT_ONE:
				
				final ViewHolderOne one = (ViewHolderOne) holder;
				
	
				break;

			case LAYOUT_TWO:
				
				final ViewHolderTwo two = (ViewHolderTwo) holder;
			
				//two.title_recom.setText(Html.fromHtml(jsonData.getRecom()));
				
			
				break;
		}



	}
	public static adapter_formation getInstance() {
        return instance;
    }
	
    
	
    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    @Override
    public int getItemCount() {
        return 2;
    }
	


	//****************  VIEW HOLDER 2 ******************//

	public class ViewHolderTwo extends RecyclerView.ViewHolder {

		private RecyclerView recycom;

		private ImageView refresh;

		public ViewHolderTwo(View itemView) {
			super(itemView);
			this.recycom = (RecyclerView) itemView.findViewById(R.id.recy);
            
		}
	}

}
