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
public class anime_hot extends AppCompatActivity 
{ 
    private Toolbar toolbar;
	
	
    private static final String TAG = "RecyclerViewJSON";
    private List<JSONData> feedsList;
    private RecyclerView recyclerView;
    private adapter_animehot adapter;
	private LinearLayout nointer;
	private LinearLayout rebtn;
	final String url = "https://github.com/Devktw/Doome/raw/main/doome.php";

	private LinearLayout back;
	private SwipeRefreshLayout refresh;
	private TextView tvload;
	boolean isfirest;
	private ProgressBar progress;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
		setTheme(R.style.AppTheme);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.anime_hot);
		nointer=findViewById(R.id.nointernet_lay);
		rebtn=findViewById(R.id.rebtn);
		tvload=findViewById(R.id.textre);
		progress=findViewById(R.id.progress);
		progress.setVisibility(View.VISIBLE);
		isfirest=true;
		new GetDataBinding().execute(url);
		
		rebtn.setOnClickListener(new OnClickListener(){

				@Override
				public void onClick(View p1) {
					new GetDataBinding().execute(url);
					tvload.setText("กำลังโหลด...");
				
				}
			});
		
		recyclerView = findViewById(R.id.recy_hot);
		back=findViewById(R.id.back);
		back.setOnClickListener(new OnClickListener(){

				@Override
				public void onClick(View p1) {
					finish();
				}
			});
		refresh=findViewById(R.id.refresh);
		refresh.setColorSchemeResources(R.color.coloraccent);
		recyclerView.setLayoutManager(new LinearLayoutManager(anime_hot.this,LinearLayoutManager.VERTICAL, false));
		//String url = "http://stacktips.com/?json=get_category_posts&slug=news&count=30";


	
		refresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
				@Override
				public void onRefresh() {
					refresh.setRefreshing(true);				
					new GetDataBinding().execute(url);
					refresh.setRefreshing(false); 
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
				
				progress.setVisibility(View.GONE);
				SharedPreferences spp =getSharedPreferences("myvaluehot", Activity.MODE_PRIVATE);
				int myIntValue = spp.getInt("feedvaluehot",0);
				String gg= String.valueOf(myIntValue);
				String gl=String.valueOf(feedsList.size());

				SharedPreferences sp = getSharedPreferences("myvaluehot", Activity.MODE_PRIVATE);
				SharedPreferences.Editor editor = sp.edit();
				editor.putInt("feedvaluehot", feedsList.size());
				editor.commit();
				if(gl==gg){
					if(isfirest){
						loop();
						refresh.setRefreshing(false);
						adapter = new adapter_animehot(anime_hot.this, feedsList);
						recyclerView.setHasFixedSize(true);
						recyclerView.setAdapter(adapter);
						nointer.setVisibility(View.GONE);
						recyclerView.setVisibility(View.VISIBLE);
						adapter.notifyDataSetChanged();
					    isfirest=false;
					}

				}else{
					adapter = new adapter_animehot(anime_hot.this, feedsList);
					recyclerView.setHasFixedSize(true);
					recyclerView.setAdapter(adapter);
					nointer.setVisibility(View.GONE);
					recyclerView.setVisibility(View.VISIBLE);
					adapter.notifyDataSetChanged();
				}
				if(refresh.isRefreshing()){
					refresh.setRefreshing(false);
					adapter = new adapter_animehot(anime_hot.this, feedsList);
					recyclerView.setHasFixedSize(true);
					recyclerView.setAdapter(adapter);
					nointer.setVisibility(View.GONE);
					recyclerView.setVisibility(View.VISIBLE);
					adapter.notifyDataSetChanged();
				}
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
            for (int i = 0; i < posts.length(); i++) {
                JSONObject post = posts.optJSONObject(i);
                JSONData item = new JSONData();
                item.setName_anime(post.optString("name_anime"));
                item.setImage_anime(post.optString("image_anime"));
				item.setSubtitle(post.optString("subtitle"));
				item.setYear(post.optString("year"));
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


        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

	protected boolean isConnected()
	{
		boolean enabled = true;

		ConnectivityManager connectivityManager = (ConnectivityManager) anime_hot.this.getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo info = connectivityManager.getActiveNetworkInfo();

		if ((info == null || !info.isConnected() || !info.isAvailable())) {
			enabled = false;

		}
		return enabled;	

		
	}
}
