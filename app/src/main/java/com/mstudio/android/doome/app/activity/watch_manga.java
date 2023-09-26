package com.mstudio.android.doome.app.activity;


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


import android.support.v7.app.*;
import android.os.*;
import android.content.res.Configuration;
import android.content.SharedPreferences;
import android.app.Activity;
import android.widget.TextView;
import com.mstudio.android.doome.app.R;
import com.mstudio.android.doome.app.model.JSONData;

import com.mstudio.android.doome.app.adapter.adapter_animehot;
import com.mstudio.android.doome.app.adapter.OnItemClickListener;
import android.widget.ImageView;
import com.mstudio.android.doome.app.adapter.adapter_mangabout;
import android.support.v7.widget.CardView;
import com.squareup.picasso.Picasso;
import com.mstudio.android.doome.app.model.model_mangaep;
import com.mstudio.android.doome.app.model.model_manga1;
import com.mstudio.android.doome.app.adapter.adapter_manganes_about;



public class watch_manga extends AppCompatActivity  {
	ImageView profie_manga;
	ImageView image_manga;
	String  image_manga2, name_manga,year,formation,valus,seson,mini_story,url_ep;
	TextView tvname_manga;
	int BLUR_PRECENTAGE =50;
	private static final String TAG = "RecyclerViewJSON";
    private RecyclerView recyclerView;
    private adapter_mangabout adapter;
	final String url = "https://mrjovpn.tk/mangadoome.php";
	final String url_rate = "https://mrjovpn.tk/manga_rate.php";
	final String url_recom = "https://mrjovpn.tk/manga_recom.php";
	private SwipeRefreshLayout refresh;
	private LinearLayout nointer;
	private LinearLayout rebtn;
	private TextView tvload;
	private TextView tv_watchmanga;
	private List<model_mangaep> feedsList;
	private List<JSONData> feedsList2;
	private LinearLayout back;
	private ProgressBar progress;
	boolean isfirest;
	private int overallXScroll = 0;
    @Override
    public void onCreate(Bundle savedInstanceState) {
		setTheme(R.style.AppTheme);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.watch_manga);
		recyclerView=findViewById(R.id.recyclerview);
		tv_watchmanga=findViewById(R.id.tv_watchmanga);
		
		isfirest=true;
		Intent intent = getIntent();

        image_manga2 = intent.getStringExtra("image_manga");
        name_manga = intent.getStringExtra("name_manga");
		year = intent.getStringExtra("year");
		formation = intent.getStringExtra("formation");
		valus = intent.getStringExtra("valus");
		seson = intent.getStringExtra("seson");
		mini_story = intent.getStringExtra("mini_story");
		url_ep = intent.getStringExtra("url_ep");	
		refresh=findViewById(R.id.refresh);
		nointer=findViewById(R.id.nointernet_lay);
		rebtn=findViewById(R.id.rebtn);
		tvload=findViewById(R.id.textre);
		back=findViewById(R.id.back);
		progress=findViewById(R.id.progress);
		
		back.setOnClickListener(new OnClickListener(){

				@Override
				public void onClick(View p1) {
					finish();
				}
			});
		refresh.setColorSchemeResources(R.color.coloraccent);
		rebtn.setOnClickListener(new OnClickListener(){

				@Override
				public void onClick(View p1) {
					new GetDataBinding().execute(url_ep);
					tvload.setText("กำลังโหลด...");
				}
			});
		
		new GetDataBinding().execute(url_ep);
		progress.setVisibility(View.VISIBLE);

		refresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {


				@Override
				public void onRefresh() {
					refresh.setRefreshing(true);				
					new GetDataBinding().execute(url_ep);
					
					refresh.setRefreshing(false); 
					
				}
			});
		
		
		recyclerView.setLayoutManager(new LinearLayoutManager(watch_manga.this,LinearLayoutManager.VERTICAL, false));
		recyclerView.setOnScrollListener(new RecyclerView.OnScrollListener() {
				@Override
				public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
					super.onScrolled(recyclerView, dx, dy);

					overallXScroll = overallXScroll + dx;
					Log.i("check","overall X  = " + overallXScroll);
					LinearLayoutManager myLayoutManager = (LinearLayoutManager) recyclerView.getLayoutManager();
					int scrollPosition = myLayoutManager.findFirstVisibleItemPosition();
					if(scrollPosition==1){
						tv_watchmanga.setText(name_manga);
					}else{
						tv_watchmanga.setText("อ่านมังงะ");
					}
				}
			});
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
				refresh.setRefreshing(false);
				SharedPreferences spp =getSharedPreferences("myvalueepm", Activity.MODE_PRIVATE);
				int myIntValue = spp.getInt("feedvalueepm",0);
				String gg= String.valueOf(myIntValue);
				String gl=String.valueOf(feedsList2.size());

				SharedPreferences sp = getSharedPreferences("myvalueepm", Activity.MODE_PRIVATE);
				SharedPreferences.Editor editor = sp.edit();
				editor.putInt("feedvalueepm", feedsList2.size());
				editor.commit();
				if(gl==gg){
					if(isfirest){
						loop();
						adapter = new adapter_mangabout(watch_manga.this, feedsList,feedsList2);
						recyclerView.setHasFixedSize(true);
						recyclerView.setItemViewCacheSize(20);
						recyclerView.setAdapter(adapter);

						nointer.setVisibility(View.GONE);
						recyclerView.setVisibility(View.VISIBLE);

						progress.setVisibility(View.GONE);
						adapter.notifyDataSetChanged();

						
					    isfirest=false;
					}

				}else{
					
					adapter = new adapter_mangabout(watch_manga.this, feedsList,feedsList2);
					recyclerView.setHasFixedSize(true);
					recyclerView.setItemViewCacheSize(20);
					recyclerView.setAdapter(adapter);

					nointer.setVisibility(View.GONE);
					recyclerView.setVisibility(View.VISIBLE);

					progress.setVisibility(View.GONE);
					adapter.notifyDataSetChanged();
				}
                if(refresh.isRefreshing()){
					adapter = new adapter_mangabout(watch_manga.this, feedsList,feedsList2);
					recyclerView.setHasFixedSize(true);
					recyclerView.setItemViewCacheSize(20);
					recyclerView.setAdapter(adapter);

					nointer.setVisibility(View.GONE);
					recyclerView.setVisibility(View.VISIBLE);

					progress.setVisibility(View.GONE);
					adapter.notifyDataSetChanged();
				}
				
                
				
                adapter.setOnItemClickListener(new OnItemClickListener() {
						@Override
						public void onItemClick(JSONData item) {

						}
					});

            } else {
				progress.setVisibility(View.GONE);
                new Handler().postDelayed(new Runnable() {
						public void run() {
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
            JSONArray posts = response.optJSONArray("listmanga");
            feedsList = new ArrayList<>();
			feedsList2 = new ArrayList<>();
            for (int i = 0; i < posts.length(); i++) {
                JSONObject post = posts.optJSONObject(i);
                model_mangaep item = new model_mangaep();
                item.setName_manga(post.optString("name_manga_ep"));
                item.setImage_manga(post.optString("image_manga_ep"));
				item.setYear(post.optString("year"));
                item.setFormation(post.optString("formation"));
				item.setValus(post.optString("valus"));
                item.setSeson(post.optString("seson"));
				item.setMini_story(post.optString("mini_story"));
				item.setSubtitle(post.optString("subtitle"));
				item.setView(post.optString("view"));
				item.setUrl(post.optString("url_ep"));
				item.setImage_view(post.optString("image_view_ep"));
				item.setSubtitle(post.optString("subtitle"));
                feedsList.add(item);
					JSONData item2 = new JSONData();
				item2.setName_anime(post.optString("name_anime"));
                item2.setImage_anime(post.optString("image_anime"));
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
				item2.setSubtitle(post.optString("subtitle"));
					feedsList2.add(item2);
			}


        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
	
