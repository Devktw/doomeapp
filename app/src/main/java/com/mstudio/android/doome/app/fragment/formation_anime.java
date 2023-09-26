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
import com.mstudio.android.doome.app.adapter.adapter_nes_ep;
import com.mstudio.android.doome.app.model.JSONEp;
import com.mstudio.android.doome.app.adapter.adapter_formation;
import java.util.Iterator;
import android.os.Parcelable;
import com.mstudio.android.doome.app.activity.watch_anime;
import com.mstudio.android.doome.app.adapter.adapter_nes_ep_re;
import com.mstudio.android.doome.app.adapter.ViewHolderOne;
import com.mstudio.android.doome.app.adapter.adapter_nes_re_watch;
public class formation_anime extends Fragment {

    private static final String TAG = "RecyclerViewJSON";
	private List<JSONEp> feedsListEp;
	private List<JSONData3> feedsList3;
    private RecyclerView recyclerView;
    private adapter_formation adapter;
	final String url = "https://github.com/Devktw/Doome/raw/main/doome.php";

	String  image_anime, name_anime,year,formation,valus,seson,mini_story,urlep;
	private LinearLayout nointer;
	private LinearLayout rebtn;
	private TextView tvload;
	private CardView card;
	private ProgressBar progress;
	private Context mContext;
	private static formation_anime instance;
	boolean isfirest;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		instance = this;
		getActivity().setTheme(R.style.AppTheme);
		final View view = inflater.inflate(R.layout.formation_anime, container, false);
		nointer=view.findViewById(R.id.nointernet_lay);
		isfirest=true;
		Intent intent = getActivity().getIntent();

        image_anime = intent.getStringExtra("image_anime");
        name_anime = intent.getStringExtra("name_anime");
		year = intent.getStringExtra("year");
		formation = intent.getStringExtra("formation");
		valus = intent.getStringExtra("valus");
		seson = intent.getStringExtra("seson");
		urlep = intent.getStringExtra("url_ep");
		mini_story = intent.getStringExtra("mini_story");

		rebtn=view.findViewById(R.id.rebtn);
		tvload=view.findViewById(R.id.textre);
		progress=view.findViewById(R.id.progress);
		recyclerView = view.findViewById(R.id.recyclerview);
		rebtn.setOnClickListener(new OnClickListener(){

				@Override
				public void onClick(View p1) {
					new GetDataBinding().execute(url);
					new GetDataEp().execute(urlep);
					tvload.setText("กำลังโหลด...");
				}


			});

		recyclerView.setLayoutManager(new LinearLayoutManager(getActivity(),LinearLayoutManager.VERTICAL, false));
		//String url = "http://stacktips.com/?json=get_category_posts&slug=news&count=30";
		new GetDataEp().execute(urlep);

		new GetDataBinding().execute(url);


		progress.setVisibility(View.VISIBLE);
        return view;

	}

	
	
	private class GetDataEp extends AsyncTask<String, Void, Integer> {
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
                    parseEp(response.toString());

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
				progress.setVisibility(View.GONE);
				adapter = new adapter_formation(getActivity(),feedsList3,feedsListEp);

				recyclerView.setHasFixedSize(true);
				adapter.notifyDataSetChanged();
				recyclerView.setAdapter(adapter);
				nointer.setVisibility(View.GONE);
				recyclerView.setVisibility(View.VISIBLE);
				watch_anime.getInstance().checkpackage();
				new Handler().postDelayed(new Runnable() {
						public void run() {
							watch_anime.getInstance().refreshep();
							ViewHolderOne.getInstance().readap();

						}
					}, 500);

				




                adapter.setOnItemClickListener(new OnItemClickListener() {
						@Override
						public void onItemClick(JSONData item) {

						}
					});

            } else {
               	new Handler().postDelayed(new Runnable() {
						public void run() {

							tvload.setText("แตะเพื่อลองใหม่");
						}
					}, 1000);
				if (feedsListEp !=null && !feedsListEp.isEmpty()) {
				}else{
					nointer.setVisibility(View.VISIBLE);
					recyclerView.setVisibility(View.GONE);
					progress.setVisibility(View.GONE);
				}

			}
        }
    }

	private void parseEp(String s) {
        try {
            JSONObject response = new JSONObject(s);
            JSONArray posts = response.optJSONArray("listanime");

            feedsListEp = new ArrayList<>();

			for (int i = 0; i < posts.length(); i++) {
				JSONObject post = posts.optJSONObject(i);

				JSONEp item3 = new JSONEp();
				item3.setEp_name(post.optString("name_ep"));
				item3.setEp_image(post.optString("image_ep"));
				item3.setEp(post.optString("ep"));
				feedsListEp.add(item3);
			}
        } catch (JSONException e) {
            e.printStackTrace();
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
				tvload.setText("โหลดสำเร็จ!");
				progress.setVisibility(View.GONE);
				adapter = new adapter_formation(getActivity(),feedsList3,feedsListEp);
				
				
				adapter.notifyDataSetChanged();
				recyclerView.setAdapter(adapter);
				
                adapter.setOnItemClickListener(new OnItemClickListener() {
						@Override
						public void onItemClick(JSONData item) {

						}
					});

            } else {
               	new Handler().postDelayed(new Runnable() {
						public void run() {

							tvload.setText("แตะเพื่อลองใหม่");
						}
					}, 1000);
				if (feedsListEp !=null &&feedsList3!=null&&feedsList3.isEmpty()&& !feedsListEp.isEmpty()) {
				}else{
					nointer.setVisibility(View.VISIBLE);
					recyclerView.setVisibility(View.GONE);
					progress.setVisibility(View.GONE);
				}

			}
        }
    }

	private void parseResult(String s) {
        try {
            JSONObject response = new JSONObject(s);
            JSONArray posts = response.optJSONArray("listanime");

            //feedsListEp = new ArrayList<>();
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
				Collections.shuffle(feedsList3);
				
			}
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

	public static formation_anime getInstance() {
        return instance;
    }

    public void proMethod() {
		new GetDataEp().execute(urlep);
		new GetDataBinding().execute(url);
	}


}

