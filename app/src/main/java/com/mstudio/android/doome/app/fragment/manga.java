package com.mstudio.android.doome.app.fragment;

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
import com.mstudio.android.doome.app.model.model_manga1;
import com.mstudio.android.doome.app.adapter.adapter_manga;
import com.mstudio.android.doome.app.model.model_mangahot;
import com.mstudio.android.doome.app.model.model_mangarate;
import com.mstudio.android.doome.app.model.model_mangarecom;

public class manga extends Fragment {
	private static final String TAG = "RecyclerViewJSON";
    private List<model_mangahot> feedsList2;
	private List<model_manga1> feedItemList;
	private List<model_mangarate> feedItemList_rate;
	private List<model_mangarecom> feedItemList_re;
	
    private RecyclerView recyclerView;
    private adapter_manga adapter;
	final String url = "https://github.com/Devktw/Doome/raw/main/mangadoome.php";
	final String url_rate = "https://github.com/Devktw/Doome/raw/main/manga_rate.php";
	final String url_recom = "https://github.com/Devktw/Doome/raw/main/manga_recom.php";
	private SwipeRefreshLayout refresh;
	private LinearLayout nointer;
	private LinearLayout rebtn;
	private TextView tvload;
	private CardView card;
	private static manga instance;
	private View linehome;
	private int overallXScroll = 0;
	private ProgressBar progress;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		instance = this;
		getActivity().setTheme(R.style.AppTheme);
		final View view = inflater.inflate(R.layout.manga, container, false);
	
		SharedPreferences prefss = getActivity().
			getSharedPreferences(getActivity().getPackageName(), Activity.MODE_PRIVATE);
		String ss = prefss.getString("item","");
		if(!ss.equals("")) {
			if(ss.contains("0")){
				int currentNightMode = getActivity().getResources().getConfiguration().uiMode & Configuration.UI_MODE_NIGHT_MASK;  
				switch (currentNightMode) {
					case Configuration.UI_MODE_NIGHT_NO:
						
						break;
					case Configuration.UI_MODE_NIGHT_YES:
						
						break;
				}
			}else{
				if(ss.contains("1")){
					
				}else{
					if(ss.contains("2")){
					}
				}
			}
		}else{
			
		}
		recyclerView=view.findViewById(R.id.recyclerview);
		nointer=view.findViewById(R.id.nointernet_lay);
		rebtn=view.findViewById(R.id.rebtn);
		tvload=view.findViewById(R.id.textre);
		recyclerView = view.findViewById(R.id.recyclerview);
		refresh=view.findViewById(R.id.refresh);
		linehome=view.findViewById(R.id.linehome);
		progress=view.findViewById(R.id.progress);
		refresh.setColorSchemeResources(R.color.coloraccent);
		recyclerView.setLayoutManager(new LinearLayoutManager(getActivity(),LinearLayoutManager.VERTICAL, false));
		rebtn.setOnClickListener(new OnClickListener(){

				@Override
				public void onClick(View p1) {
					new GetDataBinding().execute(url);
					
					
					new GetDataRe().execute(url_recom);

					new GetDataRate().execute(url_rate);
					tvload.setText("กำลังโหลด...");
				}
			});
		

		new GetDataBinding().execute(url);
		new GetDataRe().execute(url_recom);
		new GetDataRate().execute(url_rate);
		progress.setVisibility(View.VISIBLE);
		refresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {


				@Override
				public void onRefresh() {
					refresh.setRefreshing(true);				
					new GetDataBinding().execute(url);
					new GetDataRe().execute(url_recom);
					new GetDataRate().execute(url_rate);
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



					if(lManager.findFirstCompletelyVisibleItemPosition()==0){
						linehome.setVisibility(View.GONE);

					}else{
						linehome.setVisibility(View.VISIBLE);
					}
				}
			});
		return view;
}

	private class GetDataRe extends AsyncTask<String, Void, Integer> {
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
                    parseRe(response.toString());

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
				tvload.setText("โหลดสำเร็จ!");
                adapter = new adapter_manga(getActivity(),feedsList2,feedItemList_rate,feedItemList_re);
				recyclerView.setHasFixedSize(true);
		        recyclerView.setAdapter(adapter);
				
				nointer.setVisibility(View.GONE);
				recyclerView.setVisibility(View.VISIBLE);
				progress.setVisibility(View.GONE);
				adapter.notifyDataSetChanged();

				refresh.setRefreshing(false);
                adapter.setOnItemClickListener(new OnItemClickListener() {
						@Override
						public void onItemClick(JSONData item) {

						}
					});

            } else {
				progress.setVisibility(View.GONE);
               	new Handler().postDelayed(new Runnable() {
						public void run() {
							refresh.setRefreshing(false);
							tvload.setText("แตะเพื่อลองใหม่");
						}
					}, 1000);
				if (feedItemList !=null && !feedItemList.isEmpty()) {
				}else{
					nointer.setVisibility(View.VISIBLE);
					
					recyclerView.setVisibility(View.GONE);
				}

			}
        }
    }

	private void parseRe(String s) {
        try {
            JSONObject response = new JSONObject(s);
            JSONArray posts = response.optJSONArray("listmanga");
			feedItemList_re = new ArrayList<>();
            for (int i = 0; i < posts.length(); i++) {

                JSONObject post = posts.optJSONObject(i);
				model_mangarecom itemre = new model_mangarecom();
				itemre.setTitle_Formation(post.optString("title_formation"));
				itemre.setRecom(post.optString("title_recom"));
                itemre.setName_manga(post.optString("name_manga"));
                itemre.setImage_manga(post.optString("image_manga"));
				itemre.setYear(post.optString("year"));
                itemre.setFormation(post.optString("formation"));
				itemre.setValus(post.optString("valus"));
                itemre.setUrl(post.optString("url_ep"));
				itemre.setSeson(post.optString("seson"));
				itemre.setProfile(post.optString("profile"));
				itemre.setMini_story(post.optString("mini_story"));
				itemre.setSubtitle(post.optString("subtitle"));
				itemre.setSub_use(post.optString("sub_use"));
				feedItemList_re.add(itemre);
				Collections.shuffle(feedItemList_re);

			}
        } catch (JSONException e) {
            e.printStackTrace();
        }

	}
	

	private class GetDataRate extends AsyncTask<String, Void, Integer> {
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
                    parseRate(response.toString());

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
				tvload.setText("โหลดสำเร็จ!");
                adapter = new adapter_manga(getActivity(),feedsList2,feedItemList_rate,feedItemList_re);
				recyclerView.setHasFixedSize(true);
		        recyclerView.setAdapter(adapter);
				nointer.setVisibility(View.GONE);
				recyclerView.setVisibility(View.VISIBLE);
				progress.setVisibility(View.GONE);

				adapter.notifyDataSetChanged();

				new Handler().postDelayed(new Runnable() {
						public void run() {
							refresh.setRefreshing(false);
						}
					}, 700);
                adapter.setOnItemClickListener(new OnItemClickListener() {
						@Override
						public void onItemClick(JSONData item) {

						}
					});

            } else {
               	new Handler().postDelayed(new Runnable() {
						public void run() {
							refresh.setRefreshing(false);
							tvload.setText("แตะเพื่อลองใหม่");
						}
					}, 1000);
				if (feedItemList !=null && !feedItemList.isEmpty()) {
				}else{
					nointer.setVisibility(View.VISIBLE);
					recyclerView.setVisibility(View.GONE);
				}

			}
        }
    }

	private void parseRate(String s) {
        try {
            JSONObject response = new JSONObject(s);
            JSONArray posts = response.optJSONArray("listmanga");
			feedItemList_rate = new ArrayList<>();
            for (int i = 0; i < posts.length(); i++) {

                JSONObject post = posts.optJSONObject(i);
				model_mangarate itemr = new model_mangarate();
				itemr.setTitle_Formation(post.optString("title_formation"));
				itemr.setRecom(post.optString("title_recom"));
                itemr.setName_manga(post.optString("name_manga"));
                itemr.setImage_manga(post.optString("image_manga"));
				itemr.setYear(post.optString("year"));
                itemr.setFormation(post.optString("formation"));
				itemr.setValus(post.optString("valus"));
                itemr.setUrl(post.optString("url_ep"));
				itemr.setSeson(post.optString("seson"));
				itemr.setMini_story(post.optString("mini_story"));
				itemr.setSubtitle(post.optString("subtitle"));
				itemr.setView(post.optString("view"));
				itemr.setProfile(post.optString("profile"));
				itemr.setSub_use(post.optString("sub_use"));
				feedItemList_rate.add(itemr);


			}
        } catch (JSONException e) {
            e.printStackTrace();
        }
   
	}

	public static manga getInstance() {
        return instance;
    }

    public void refresh() {

		new GetDataBinding().execute(url);
		new GetDataRe().execute(url_recom);

		new GetDataRate().execute(url_rate);

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
				tvload.setText("โหลดสำเร็จ!");
                adapter = new adapter_manga(getActivity(),feedsList2,feedItemList_rate,feedItemList_re);
				recyclerView.setItemViewCacheSize(20);
				recyclerView.setHasFixedSize(true);
		        recyclerView.setAdapter(adapter);
				nointer.setVisibility(View.GONE);
				recyclerView.setVisibility(View.VISIBLE);


				adapter.notifyDataSetChanged();

				refresh.setRefreshing(false);
                adapter.setOnItemClickListener(new OnItemClickListener() {
						@Override
						public void onItemClick(JSONData item) {

						}
					});

            } else {
               	new Handler().postDelayed(new Runnable() {
						public void run() {
							refresh.setRefreshing(false);
							tvload.setText("แตะเพื่อลองใหม่");
						}
					}, 1000);
				if (feedItemList !=null && !feedItemList.isEmpty()) {
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
            JSONArray posts = response.optJSONArray("listmanga");
            feedItemList = new ArrayList<>();
			feedsList2 = new ArrayList<>();
			feedItemList_rate = new ArrayList<>();
            for (int i = 0; i < posts.length(); i++) {

                JSONObject post = posts.optJSONObject(i);

                model_manga1 item = new model_manga1();
				item.setTitle_Formation(post.optString("title_formation"));
				item.setRecom(post.optString("title_recom"));
                item.setName_manga(post.optString("name_manga"));
                item.setImage_manga(post.optString("image_manga"));
				item.setYear(post.optString("year"));
                item.setFormation(post.optString("formation"));
				item.setValus(post.optString("valus"));
                item.setUrl(post.optString("url_ep"));
				item.setSeson(post.optString("seson"));
				item.setMini_story(post.optString("mini_story"));
				item.setSubtitle(post.optString("subtitle"));
				item.setProfile(post.optString("profile"));
				item.setSub_use(post.optString("sub_use"));
				
                feedItemList.add(item);
			}

			for (int i = 0; i < posts.length(); i++) {
				JSONObject post = posts.optJSONObject(i);
				model_mangahot item = new model_mangahot();
				item.setTitle_Formation(post.optString("title_formation"));
				item.setRecom(post.optString("title_recom"));
                item.setName_manga(post.optString("name_manga"));
                item.setImage_manga(post.optString("image_manga"));
				item.setYear(post.optString("year"));
                item.setFormation(post.optString("formation"));
				item.setValus(post.optString("valus"));
                item.setUrl(post.optString("url_ep"));
				item.setSeson(post.optString("seson"));
				item.setMini_story(post.optString("mini_story"));
				item.setSubtitle(post.optString("subtitle"));
				item.setProfile(post.optString("profile"));
				item.setSub_use(post.optString("sub_use"));
				feedsList2.add(item);
				

				
			}
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
	
}
