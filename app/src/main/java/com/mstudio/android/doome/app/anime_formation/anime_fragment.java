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
import com.mstudio.android.doome.app.activity.watch_anime;
import com.mstudio.android.doome.app.fragment.home;
import com.mstudio.android.doome.app.adapter.adapter_nes_general;
import com.mstudio.android.doome.app.PreloadLinearLayoutManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import java.util.Collection;
import com.mstudio.android.doome.app.adapter.adapter_nes_re;
public class anime_fragment extends Fragment {

    private static final String TAG = "RecyclerViewJSON";
    private List<JSONData> feedsList;
	private List<JsonData2> feedsList2;
	private List<JSONData3> feedsList3;
    private RecyclerView recyclerView;
    private adapter adapter;
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
	boolean isfirest;
	boolean checklist;
	int i =0;
	LinearLayoutManager lManager;
	JSONData item = new JSONData();
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		
		getActivity().setTheme(R.style.AppTheme);
		final View view = inflater.inflate(R.layout.anime_fragment, container, false);
		
		isfirest=true;
		nointer=view.findViewById(R.id.nointernet_lay);
		rebtn=view.findViewById(R.id.rebtn);
		tvload=view.findViewById(R.id.textre);
		recyclerView = view.findViewById(R.id.recyclerview);

		refresh=view.findViewById(R.id.refresh);
		progress=view.findViewById(R.id.progress);
		refresh.setColorSchemeResources(R.color.coloraccent);
		new GetDataBinding().execute(url);
		
		LinearLayoutManager lManager
			= new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
		recyclerView.setLayoutManager(lManager);
		
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
	private void loop() {
		final Handler hander = new Handler();
		new Thread(new Runnable() {
				@Override
				public void run() {
					try {
						Thread.sleep(2000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					hander.post(new Runnable() {
							@Override
							public void run() {
								new GetDataBinding().execute(url);
								
								loop();
								}
						});
				}
			}).start();
	}
	private void looptext() {
		final Handler hander = new Handler();
		new Thread(new Runnable() {
				@Override
				public void run() {
					try {
						Thread.sleep(4000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					hander.post(new Runnable() {
							@Override
							public void run() {
								
								
								if(i== feedsList.size()){
									
									i = 0;
								}else{
									
									Collections.shuffle(feedsList2);
									if(feedsList2.get(i).getName_anime().equals("")){
										
										
										home.getInstance().changtext(feedsList2.get(i).getName_manga());			
										
									}if(feedsList2.get(i).getName_manga().equals("")){
										home.getInstance().changtext(feedsList2.get(i).getName_anime());			
										
									}
								
								}
								
								looptext();
								
							}
						});
				}
			}).start();
	}
	LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false){
		@Override
		public boolean canScrollVertically() {
			return false;
		}
	};
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
				adapter = new adapter(getActivity(), feedsList,feedsList2,feedsList3);
				nointer.setVisibility(View.GONE);
				recyclerView.setVisibility(View.VISIBLE);
				recyclerView.setAdapter(adapter);
				adapter.notifyDataSetChanged();
				looptext();
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
            feedsList = new ArrayList<>();
			feedsList2 = new ArrayList<>();
			feedsList3 = new ArrayList<>();

            for (int i = 0; i < posts.length(); i++) {

                JSONObject post = posts.optJSONObject(i);

                
				item.setName_anime(post.optString("name_anime"));
                item.setImage_anime(post.optString("image_anime"));
				item.setYear(post.optString("year"));
				item.setSubtitle(post.optString("subtitle"));
                item.setFormation(post.optString("formation"));
				item.setValus(post.optString("valus"));
                item.setSeson(post.optString("seson"));
				item.setMini_story(post.optString("mini_story"));
				item.setUrl(post.optString("url_ep"));
				item.setStatus(post.optString("status"));
				item.setPackage1(post.optString("package"));
				item.setName_manga(post.optString("name_manga"));
				item.setImage_manga(post.optString("image_manga"));
				item.setType(post.optString("type"));
				item.setSub_use(post.optString("sub_use"));
				item.setProfile(post.optString("profile"));


                feedsList.add(item);
			}

			for (int i = 0; i < posts.length(); i++) {
				JSONObject post = posts.optJSONObject(i);
				JsonData2 item2 = new JsonData2();
				item2.setName_anime(post.optString("name_anime"));
                item2.setImage_anime(post.optString("image_anime"));
				item2.setSubtitle(post.optString("subtitle"));
				item2.setYear(post.optString("year"));
                item2.setFormation(post.optString("formation"));
				item2.setValus(post.optString("valus"));
                item2.setSeson(post.optString("seson"));
				item2.setMini_story(post.optString("mini_story"));
				item2.setUrl(post.optString("url_ep"));
				item2.setStatus(post.optString("status"));
				item2.setPackage1(post.optString("package"));
				item2.setName_manga(post.optString("name_manga"));
				item2.setImage_manga(post.optString("image_manga"));
				item2.setType(post.optString("type"));
				item2.setSub_use(post.optString("sub_use"));
				item2.setProfile(post.optString("profile"));
				feedsList2.add(item2);

				JSONData3 item3 = new JSONData3();
				item3.setName_anime(post.optString("name_anime"));
				item3.setSubtitle(post.optString("subtitle"));
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
				feedsList3.add(item3);
				
				//SharedPreferences mySharedPreferences = mContext.getSharedPreferences("textsearch", Context.MODE_PRIVATE);
				//SharedPreferences.Editor editor = mySharedPreferences.edit();
				//editor.putString("textsearch",jsonData.getName_anime());
				//editor.commit();
				
				Collections.shuffle(feedsList3);
				
				}
        } catch (JSONException e) {
			
			
			
            e.printStackTrace();
        }
    }
	


}


