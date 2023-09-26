package com.mstudio.android.doome.app.anime_formation;


import android.app.AlertDialog;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.LinearLayout;
import android.graphics.Rect;
import android.widget.ImageButton;
import android.view.View.OnClickListener;
import android.content.Intent;
import android.support.v4.widget.SwipeRefreshLayout;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.content.Context;

import android.view.MenuItem;
import android.view.Menu;
import android.view.MenuInflater;
import android.widget.ImageView;
import android.content.res.Configuration;
import android.content.SharedPreferences;
import android.app.Activity;
import android.os.strictmode.InstanceCountViolation;
import android.widget.FrameLayout;
import android.os.Handler;
import android.widget.TextView;
import android.location.Address;
import android.location.Geocoder;
import com.mstudio.android.doome.app.model.JsonData2;

import com.mstudio.android.doome.app.model.JSONData;

import com.mstudio.android.doome.app.R;
import com.mstudio.android.doome.app.adapter.adapter;
import com.mstudio.android.doome.app.adapter.OnItemClickListener;
import android.support.v7.widget.CardView;
import com.mstudio.android.doome.app.model.JSONData3;
import java.util.Collections;
import com.mstudio.android.doome.app.adapter.adapter_dinamic;
import android.annotation.NonNull;
import android.support.annotation.DimenRes;
import android.support.v7.widget.GridLayoutManager;
import com.mstudio.android.doome.app.adapter.spaceitem;
import com.mstudio.android.doome.app.fragment.manga;
import com.mstudio.android.doome.app.activity.search_activity;
import com.mstudio.android.doome.app.activity.anime_hot;
import com.mstudio.android.doome.app.adapter.adapter_nes;
public class anime_fragment_fight extends Fragment {

    private static final String TAG = "RecyclerViewJSON";
    private List<JSONData> feedsList;
	private List<JsonData2> feedsList2;
	private List<JSONData3> feedsList3;
    private RecyclerView recyclerView;
    private adapter_dinamic adapter;
	final String url = "https://github.com/Devktw/Doome/raw/main/doome.php";
	private CardView search;
	private SwipeRefreshLayout refresh;
	private LinearLayout nointer;
	private LinearLayout rebtn;
	private TextView tvload;
	private CardView card;
	private Context mComtext;

	private LinearLayout go_search;
	private int overallXScroll = 0;
	private ProgressBar progress;
	private static anime_fragment_fight instance;
	boolean isloadsucses;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		instance = this;
		getActivity().setTheme(R.style.AppTheme);
		final View view = inflater.inflate(R.layout.anime_fragment_more, container, false);
		

		nointer=view.findViewById(R.id.nointernet_lay);
		rebtn=view.findViewById(R.id.rebtn);
		tvload=view.findViewById(R.id.textre);
		recyclerView = view.findViewById(R.id.recyclerview);

		refresh=view.findViewById(R.id.refresh);
		progress=view.findViewById(R.id.progress);
		refresh.setColorSchemeResources(R.color.coloraccent
										);
	
		
		int spanCount = 3; // 3 columns
		int spacing = 5; // 50px
		boolean includeEdge = true;
		
		recyclerView.addItemDecoration(new spaceitem(spanCount, spacing, includeEdge));
		recyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 3));     
		rebtn.setOnClickListener(new OnClickListener(){

				@Override
				public void onClick(View p1) {
					new GetDataBinding().execute(url);
					
					tvload.setText("กำลังโหลด...");
				}
			});


		progress.setVisibility(View.VISIBLE);
		refresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {


				@Override
				public void onRefresh() {
					refresh.setRefreshing(true);				
					new GetDataBinding().execute(url);
					refresh.setRefreshing(false); 
				}
			});
		

		recyclerView.setOnScrollListener(new RecyclerView.OnScrollListener() {
				@Override
				public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
					super.onScrolled(recyclerView, dx, dy);

					overallXScroll = overallXScroll + dx;
					Log.i("check","overall X  = " + overallXScroll);



					LinearLayoutManager lManager = (LinearLayoutManager) recyclerView.getLayoutManager();



				}
			});
			

		
        return view;

	}
	public static anime_fragment_fight getInstance() {
        return instance;
    }

    public void loaddata() {
		if(!isloadsucses){
			new GetDataBinding().execute(url);
		}
		
    }
	
	
	public class EqualSpaceItemDecoration extends RecyclerView.ItemDecoration {

		private final int mSpaceHeight;

		public EqualSpaceItemDecoration(int mSpaceHeight) {
			this.mSpaceHeight = mSpaceHeight;
		}

		@Override
		public void getItemOffsets(Rect outRect, View view, RecyclerView parent,
								   RecyclerView.State state) {
			outRect.bottom = mSpaceHeight;
			outRect.top = mSpaceHeight;
			outRect.left = mSpaceHeight;
			outRect.right = mSpaceHeight;
		}
	}
	
	private class GetDataBinding extends AsyncTask<String, Void, Integer> {
        @Override
        protected void onPreExecute() {

		}
        @Override
        protected Integer doInBackground(String... strings) {
            Integer result = 0;
            HttpURLConnection urlConnection;
            try {
                URL url = new URL(strings[0]);
                urlConnection = (HttpURLConnection) url.openConnection();
                int statusCode = urlConnection.getResponseCode();

                // 200 represents HTTP OK
                if (statusCode == 200) {
                    BufferedReader r = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
                    StringBuilder response = new StringBuilder();
                    String line;
                    while ((line = r.readLine()) != null) {
                        response.append(line);
                    }
                    parseResult(response.toString());

                    result = 1; // Successful
                } else {
                    result = 0; //"Failed to fetch data!";
                }
            } catch (Exception e) {
                Log.d(TAG, e.getLocalizedMessage());
            }
            return result; //"Failed to fetch data!";
        }

        @Override
        protected void onPostExecute(Integer result) {


            if (result == 1) {
				progress.setVisibility(View.GONE);

				isloadsucses=true;
                adapter = new adapter_dinamic(getActivity(),feedsList3);
				recyclerView.setHasFixedSize(true);
		        recyclerView.setAdapter(adapter);
				nointer.setVisibility(View.GONE);
				recyclerView.setVisibility(View.VISIBLE);


				adapter.notifyDataSetChanged();
				adapter_dinamic.getInstance().filter();

            } else {
				progress.setVisibility(View.GONE);
               	new Handler().postDelayed(new Runnable() {
						public void run() {
							refresh.setRefreshing(false);
							tvload.setText("แตะเพื่อลองใหม่");
						}
					}, 1000);
				if (feedsList !=null && !feedsList.isEmpty()) {
				}else{
					nointer.setVisibility(View.VISIBLE);
					recyclerView.setVisibility(View.GONE);
				}

			}
        }
    }

	private void parseResult(String s) {
        try {
            JSONObject response = new JSONObject(s);
            JSONArray posts = response.optJSONArray("listanime");
            
			feedsList3 = new ArrayList<>();

            for (int i = 0; i < posts.length(); i++) {

                JSONObject post = posts.optJSONObject(i);

				JSONData3 item3 = new JSONData3();
				item3.setName_anime(post.optString("name_anime"));
                item3.setImage_anime(post.optString("image_anime"));
				item3.setYear(post.optString("year"));
                item3.setFormation(post.optString("formation"));
				item3.setValus(post.optString("valus"));
                item3.setSeson(post.optString("seson"));
				item3.setMini_story(post.optString("mini_story"));
				item3.setUrl(post.optString("url_ep"));
				item3.setStatus(post.optString("status"));
				item3.setPackage1(post.optString("package"));
				item3.setName_manga(post.optString("name_manga"));
				item3.setImage_manga(post.optString("image_manga"));
				item3.setType(post.optString("type"));
				item3.setSub_use(post.optString("sub_use"));
				item3.setProfile(post.optString("profile"));
				item3.setSubtitle(post.optString("subtitle"));
				feedsList3.add(item3);

                
			}

			
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }



}


