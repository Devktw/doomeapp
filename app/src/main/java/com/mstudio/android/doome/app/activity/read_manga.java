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
import com.mstudio.android.doome.app.model.model_readmanga;
import com.mstudio.android.doome.app.adapter.adapter_readmanga;
import android.app.Dialog;
import android.widget.AbsListView;
import android.view.MotionEvent;
import android.support.v4.view.MotionEventCompat;
import android.view.WindowManager;
import android.graphics.Color;
import android.support.v4.content.ContextCompat;
import android.app.PendingIntent;
import android.net.Uri;
import android.media.RingtoneManager;
import android.support.v4.app.NotificationCompat;
import android.app.NotificationManager;
import android.annotation.SuppressLint;
import android.app.NotificationChannel;
import android.content.DialogInterface;
import android.view.Gravity;
import android.annotation.NonNull;
import com.mstudio.android.doome.app.adapter.adapter_manganes_about;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.View.OnTouchListener;
import android.support.v7.widget.LinearSnapHelper;
import java.util.Timer;
import java.util.TimerTask;
import android.support.v7.widget.LinearSmoothScroller;
import android.util.DisplayMetrics;
import android.view.Window;
import android.graphics.drawable.ColorDrawable;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Switch;
import android.widget.CompoundButton;
import android.support.v4.widget.Space;
import android.graphics.PointF;
import com.mstudio.android.doome.app.SpeedyLinearLayoutManager;


public class read_manga extends AppCompatActivity  {
	ImageView profie_manga;
	ImageView image_manga;
	String  image_manga2, name_manga,year,formation,valus,seson,mini_story,url_ep,image_view;
	TextView tvname_manga;
	int BLUR_PRECENTAGE =50;
	private static final String TAG = "RecyclerViewJSON";
    private RecyclerView recyclerView;
    private adapter_readmanga adapter;
	private SwipeRefreshLayout refresh;
	private LinearLayout nointer;
	private LinearLayout rebtn;
	private TextView tvload;
	private List<model_readmanga> feedsList;
	private TextView tv_toast;
	private static read_manga instance;
	
	ProgressBar progress;
	
	RelativeLayout setting;
	LinearLayout customtoast;
	private boolean istouch;
	private boolean issuc;
	static boolean toast=true;
	private int overallXScroll = 0;
	Boolean iscurent;
	boolean windowfix=false;
	private RelativeLayout nextep;
	private RelativeLayout backep;
	private LinearLayout setting_main;
	boolean issettingmain_show=false;
	Animation anim; 
	ImageView hide_setting;
	ImageView show_setting;
	private RelativeLayout ppscroll;
	ImageView playscroll;
	ImageView stopscroll;
	boolean autoscroll =false;
	RelativeLayout setting_autoscroll;
	
	
	private RadioButton one;
	private RadioButton two;
	private RadioButton defaultt;
    
	private RadioGroup fastgruop;
	private LinearLayout tabone;
	private LinearLayout tabtwo;
	private LinearLayout tabdefaultt;
	SharedPreferences sharedpreferences;
	SharedPreferences.Editor editor;
	String checkedItem =null;
	private Switch scroll_autoep;
	private Switch start_autoscroll;
	private String perswitch1;
	private String perswitch2;
	Boolean status;
    @Override
    public void onCreate(Bundle savedInstanceState) {
		setTheme(R.style.AppTheme);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.read_manga);
		getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
			getWindow().getAttributes().layoutInDisplayCutoutMode = WindowManager.LayoutParams.LAYOUT_IN_DISPLAY_CUTOUT_MODE_SHORT_EDGES;
		}
		
		instance = this;
		
		recyclerView=findViewById(R.id.recyclerview);
		
		
		Intent intent = getIntent();

        image_manga2 = intent.getStringExtra("image_manga");
        name_manga = intent.getStringExtra("name_manga");
		year = intent.getStringExtra("year");
		formation = intent.getStringExtra("formation");
		valus = intent.getStringExtra("valus");
		seson = intent.getStringExtra("seson");
		mini_story = intent.getStringExtra("mini_story");
		url_ep = intent.getStringExtra("url_ep");	
		image_view = intent.getStringExtra("image_view_ep");	
		refresh=findViewById(R.id.refresh);
		nointer=findViewById(R.id.nointernet_lay);
		rebtn=findViewById(R.id.rebtn);
		tvload=findViewById(R.id.textre);
		customtoast=findViewById(R.id.customtoast);
		tv_toast=findViewById(R.id.tv_toast);
		nextep=findViewById(R.id.nextep);
		backep=findViewById(R.id.backep);
		hide_setting=findViewById(R.id.hide_setting_manga);
		show_setting=findViewById(R.id.show_setting_manga);
		progress=findViewById(R.id.progress);
		setting=findViewById(R.id.setting_manga);
		setting_main=findViewById(R.id.setting_main);
		ppscroll=findViewById(R.id.ppsctoll);
		playscroll=findViewById(R.id.playscroll);
		stopscroll=findViewById(R.id.stopscroll);
		setting_autoscroll=findViewById(R.id.setting_autoscroll);
		
		
		getWindow().getDecorView().setSystemUiVisibility(
			View.SYSTEM_UI_FLAG_FULLSCREEN
			|View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
			| View.SYSTEM_UI_FLAG_LAYOUT_STABLE
			| View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
			| View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
			| View.SYSTEM_UI_FLAG_HIDE_NAVIGATION);
		
		SharedPreferences mySharedPreferences = read_manga.this.getSharedPreferences("goep", Context.MODE_PRIVATE);
		SharedPreferences.Editor editor = mySharedPreferences.edit();
		editor.putString("namemanga",name_manga);
		editor.commit();
		if (feedsList !=null && !feedsList.isEmpty()) {
			tv_toast.setText(name_manga+" "+"หน้าที่ "+"0"+"/"+feedsList.size());
		}else{
			tv_toast.setText(name_manga+" "+"หน้าที่ "+"0"+"/"+"0");
		}
		
		
		
		
		
		
		
		refresh.setColorSchemeResources(R.color.coloraccent);
		
		
		
	
		rebtn.setOnClickListener(new OnClickListener(){

				@Override
				public void onClick(View p1) {
					SharedPreferences mySharedPreferences = read_manga.this.getSharedPreferences("goep", Context.MODE_PRIVATE);
					String goep = mySharedPreferences.getString("goep", "");
					if(!goep.equals("")){
						new GetDataBinding().execute(goep);
					}else{
						new GetDataBinding().execute(image_view);
					}
					
					tvload.setText("กำลังโหลด...");
				}
			});
		nextep.setOnClickListener(new OnClickListener(){

				@Override
				public void onClick(View p1) {
					adapter_manganes_about.getInstance().nextep();
				}
			});
		backep.setOnClickListener(new OnClickListener(){

				@Override
				public void onClick(View p1) {
					adapter_manganes_about.getInstance().backep();
				}
			});
		setting_autoscroll.setOnClickListener(new OnClickListener(){

				@Override
				public void onClick(View p1) {
					hidesetting();
					showscroll_dialog();
					
				}
			});
		ppscroll.setOnClickListener(new OnClickListener(){

				@Override
				public void onClick(View p1) {
					if (feedsList !=null && !feedsList.isEmpty()) {
						if(!autoscroll){
							playscroll();
						}else{
							stopscroll();
						}
						
					}else{
					
					}
				}
			});
		setting.setOnClickListener(new OnClickListener(){

				@Override
				public void onClick(View p1) {
					if(!issettingmain_show){
						showsetting();
					
					}else{
						hidesetting();
					}
					}
					
			});
		new GetDataBinding().execute(image_view);
		
		recyclerView.setOnTouchListener(new OnTouchListener(){

				@Override
				public boolean onTouch(View p1, MotionEvent p2) {

					switch (p2.getAction()) { 
						case MotionEvent.ACTION_UP:
							hidesetting();
							stopscroll.setVisibility(View.GONE);
							playscroll.setVisibility(View.VISIBLE);
							autoscroll=false;
							break;
					
				
				
			}
					return false;
				}
			
		});
		refresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {


				@Override
				public void onRefresh() {
					issuc=true;
					refresh.setRefreshing(true);				
					SharedPreferences mySharedPreferences = read_manga.this.getSharedPreferences("goep", Context.MODE_PRIVATE);
					String goep = mySharedPreferences.getString("goep", "");
					if(!goep.equals("")){
						new GetDataBinding().execute(goep);
					}else{
						new GetDataBinding().execute(image_view);
					}
					
					refresh.setRefreshing(false); 
				}
			});
		
	
		
		
		recyclerView.setLayoutManager(new SpeedyLinearLayoutManager(read_manga.this, SpeedyLinearLayoutManager.VERTICAL, false));
		
		
		
		recyclerView.setOnScrollListener(new RecyclerView.OnScrollListener() {
				@Override
				public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
					super.onScrolled(recyclerView, dx, dy);

					overallXScroll = overallXScroll + dx;
					Log.i("check","overall X  = " + overallXScroll);
					
					
					
					LinearLayoutManager lManager = (LinearLayoutManager) recyclerView.getLayoutManager();
					
					SharedPreferences sh = read_manga.this.getSharedPreferences("goep", Context.MODE_PRIVATE);
					String goname = sh.getString("namemanga", "");
					
					if(lManager.findFirstCompletelyVisibleItemPosition()==0){
						tv_toast.setText(goname+" "+"หน้าที่ "+"0"+"/"+feedsList.size());
						iscurent=false;
					}else{
						iscurent=true;
						
						
					}
					
					if (!recyclerView.canScrollVertically(1)) {
						
						int position = getCurrentItem()+1;
						String i = String.valueOf(position);
						final SharedPreferences mySharedPreferences = read_manga.this.getSharedPreferences("count_a4", Context.MODE_PRIVATE);
						SharedPreferences.Editor editor = mySharedPreferences.edit();
						editor.putString("a4",i);
						editor.commit();
						read_manga.getInstance().runa4();
					}else{
						
						int position = getCurrentItem();
						String i = String.valueOf(position);
						final SharedPreferences mySharedPreferences = read_manga.this.getSharedPreferences("count_a4", Context.MODE_PRIVATE);
						SharedPreferences.Editor editor = mySharedPreferences.edit();
						editor.putString("a4",i);
						editor.commit();
						read_manga.getInstance().runa4();
					}
					
					
				}
			});
		
			}
	
	@Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        View decorView = getWindow().getDecorView();
        if(hasFocus){
			if(!windowfix){
				if(status){
					
				
				decorView.setSystemUiVisibility(
					View.SYSTEM_UI_FLAG_FULLSCREEN
					|View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
					| View.SYSTEM_UI_FLAG_LAYOUT_STABLE
					| View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
					| View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
					| View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
				);
				}
			}
            
        }
    }
	
	public void showscroll_dialog(){
		new Handler().postDelayed(new Runnable() {

				
				public void run() {
					final Dialog dialog = new Dialog(read_manga.this);
					dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
					dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
					dialog.setContentView(R.layout.dialog_scroll);
					dialog.setCancelable(false);
					scroll_autoep=dialog.findViewById(R.id.scroll_autoep);
					start_autoscroll=dialog.findViewById(R.id.start_autoscroll);
					defaultt=dialog.findViewById(R.id.defaultt);
					one=dialog.findViewById(R.id.one);
					two=dialog.findViewById(R.id.two);
					tabdefaultt=dialog.findViewById(R.id.tabdefault);
					tabone=dialog.findViewById(R.id.tabone);
					tabtwo=dialog.findViewById(R.id.tabtwo);
					fastgruop=dialog.findViewById(R.id.fastgruop);
					
					SharedPreferences settings = getSharedPreferences(perswitch1, 0);
					boolean ischeck = settings.getBoolean("switchkey1", false);
					scroll_autoep.setChecked(ischeck);
					
					SharedPreferences settings2 = getSharedPreferences(perswitch2, 0);
					boolean ischeck2 = settings2.getBoolean("switchkey2", false);
					start_autoscroll.setChecked(ischeck2);
					
					SharedPreferences prefss = getApplicationContext().
						getSharedPreferences(getPackageName(), Activity.MODE_PRIVATE);
					String ss = prefss.getString("itemfast","");
					if(!ss.equals("")) {
						if(ss.contains("0")){
							defaultt.setChecked(true);
						}else{
							if(ss.contains("1")){
								one.setChecked(true);
							}else{
								two.setChecked(true);
							}
						}
					}else{
						defaultt.setChecked(true);
					}
					tabdefaultt.setOnClickListener(new OnClickListener(){

							private String speed;


							@Override
							public void onClick(View p1) {
								checkedItem = "0";
								
								SharedPreferences prefs = getApplicationContext().
									getSharedPreferences(getPackageName(), Activity.MODE_PRIVATE);
								SharedPreferences.Editor edit = prefs.edit();
								edit.putString("itemfast", checkedItem);
								edit.apply();
								
								SharedPreferences prefss = getApplicationContext().
									getSharedPreferences(getPackageName(), Activity.MODE_PRIVATE);
								SharedPreferences.Editor editt = prefss.edit();
								editt.putFloat("speedscroll", 800f);
								editt.apply();
								
								defaultt.setChecked(true);
								one.setChecked(false);
								two.setChecked(false);
							}
						});
					tabone.setOnClickListener(new OnClickListener(){

							private String speed;

							@Override
							public void onClick(View p1) {
								checkedItem = "1";
								speed = "700";
								SharedPreferences prefs = getApplicationContext().
									getSharedPreferences(getPackageName(), Activity.MODE_PRIVATE);
								SharedPreferences.Editor edit = prefs.edit();
								edit.putString("itemfast", checkedItem);
								edit.apply();
								
								SharedPreferences prefss = getApplicationContext().
									getSharedPreferences(getPackageName(), Activity.MODE_PRIVATE);
								SharedPreferences.Editor editt = prefss.edit();
								editt.putFloat("speedscroll", 700f);
								editt.apply();
								
								one.setChecked(true);
								two.setChecked(false);
								defaultt.setChecked(false);	
							}
						});
						
					tabtwo.setOnClickListener(new OnClickListener(){

							private String speed;

							@Override
							public void onClick(View p1) {
								checkedItem = "2";
								speed = "600";
								SharedPreferences prefs = getApplicationContext().
									getSharedPreferences(getPackageName(), Activity.MODE_PRIVATE);
								SharedPreferences.Editor edit = prefs.edit();
								edit.putString("itemfast", checkedItem);
								edit.apply();
								SharedPreferences prefss = getApplicationContext().
									getSharedPreferences(getPackageName(), Activity.MODE_PRIVATE);
								SharedPreferences.Editor editt = prefss.edit();
								editt.putFloat("speedscroll", 800f);
								editt.apply();
								
								two.setChecked(true);
								one.setChecked(false);
								defaultt.setChecked(false);

							}
						});
						
						
						
					scroll_autoep.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
							@Override
							public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
								if(scroll_autoep.isChecked()){
									SharedPreferences settings = getSharedPreferences(perswitch1, 0);
									SharedPreferences.Editor editor = settings.edit();
									editor.putBoolean("switchkey1", true);
									editor.commit();
								}else{
									SharedPreferences settings = getSharedPreferences(perswitch1, 0);
									SharedPreferences.Editor editor = settings.edit();
									editor.putBoolean("switchkey1", false);
									editor.commit();
								}
								
								}
							});
					start_autoscroll.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

							
							@Override
							public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
								if(start_autoscroll.isChecked()){
									SharedPreferences settings = getSharedPreferences(perswitch2, 0);
									SharedPreferences.Editor editor = settings.edit();
									editor.putBoolean("switchkey2", true);
									editor.commit();
								}else{
									SharedPreferences settings = getSharedPreferences(perswitch2, 0);
									SharedPreferences.Editor editor = settings.edit();
									editor.putBoolean("switchkey2", false);
									editor.commit();
								}

							}
						});

					WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
					lp.copyFrom(dialog.getWindow().getAttributes());
					lp.width = WindowManager.LayoutParams.WRAP_CONTENT;
					lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
					((RelativeLayout) dialog.findViewById(R.id.dimisdialog)).setOnClickListener(new View.OnClickListener() {
							@Override
							public void onClick(View v) {
								dialog.dismiss();
							}
						});
					dialog.show();
					dialog.getWindow().setAttributes(lp);

				}
			}, 200);
	}
	
	public void showsetting() {
		anim = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.up); 
		setting_main.startAnimation(anim); 
		anim = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.rorate_left); 
		hide_setting.startAnimation(anim); 
		setting_main.setVisibility(View.VISIBLE);
		hide_setting.setVisibility(View.VISIBLE);

		show_setting.setVisibility(View.GONE);
		issettingmain_show=true;
    }
	
	public void hidesetting() {
		if(setting_main.getVisibility()==View.VISIBLE){
			anim = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.bottom); 
			setting_main.startAnimation(anim); 
			setting_main.setVisibility(View.INVISIBLE);
			hide_setting.setVisibility(View.GONE);
			show_setting.setVisibility(View.VISIBLE);
			issettingmain_show=false;
		}
		
    }
	public void playscroll() {
		stopscroll.setVisibility(View.VISIBLE);
		playscroll.setVisibility(View.GONE);
		recyclerView.smoothScrollToPosition(feedsList.size());
		autoscroll=true;
    }

	public void stopscroll() {
		recyclerView.stopScroll();
		stopscroll.setVisibility(View.GONE);
		playscroll.setVisibility(View.VISIBLE);
		autoscroll=false;
    }
	private int getCurrentItem(){
		LinearLayoutManager lManager = (LinearLayoutManager) recyclerView.getLayoutManager();
		int firstElementPosition = lManager.findLastVisibleItemPosition();
		return firstElementPosition;
	}
	
			
	public static read_manga getInstance() {
        return instance;
    }
	public void nextep() {
		SharedPreferences mySharedPreferences = this.getSharedPreferences("goep", Context.MODE_PRIVATE);
		String goep = mySharedPreferences.getString("goep", "");
		new GetDataBinding().execute(goep);
    }
	public void backep() {
		SharedPreferences mySharedPreferences = this.getSharedPreferences("goep", Context.MODE_PRIVATE);
		String goep = mySharedPreferences.getString("goep", "");
		new GetDataBinding().execute(goep);
    }
    public void proMethod() {
		progress.setVisibility(View.GONE);
    }
	public void runa4() {
		SharedPreferences sh = read_manga.this.getSharedPreferences("goep", Context.MODE_PRIVATE);
		String goname = sh.getString("namemanga", "");
		SharedPreferences mySharedPreferences = this.getSharedPreferences("count_a4", Context.MODE_PRIVATE);
		String a4 = mySharedPreferences.getString("a4", "");
		if(!iscurent){
			tv_toast.setText(goname+" "+"หน้าที่ "+"0"+"/"+feedsList.size());
		}else{
			tv_toast.setText(goname+" "+"หน้าที่ "+a4+"/"+feedsList.size());
		}
			
		
    }

	public boolean isRecyclerScrollable(RecyclerView recyclerView) {
		return recyclerView.computeHorizontalScrollRange() > recyclerView.getWidth() || recyclerView.computeVerticalScrollRange() > recyclerView.getHeight();
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
				getWindow().getDecorView().setSystemUiVisibility(
					View.SYSTEM_UI_FLAG_FULLSCREEN
					| View.SYSTEM_UI_FLAG_LOW_PROFILE
					| View.SYSTEM_UI_FLAG_LAYOUT_STABLE
					| View.SYSTEM_UI_FLAG_IMMERSIVE
					| View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
					| View.SYSTEM_UI_FLAG_HIDE_NAVIGATION);	
				status = true;
                adapter = new adapter_readmanga(read_manga.this, feedsList);
				recyclerView.setHasFixedSize(true);
		        recyclerView.setAdapter(adapter);

				nointer.setVisibility(View.GONE);
				recyclerView.setVisibility(View.VISIBLE);
				adapter.notifyDataSetChanged();
				customtoast.setVisibility(View.VISIBLE);
				ppscroll.setVisibility(View.VISIBLE);
				SharedPreferences mySharedPreferences = read_manga.this.getSharedPreferences("goep", Context.MODE_PRIVATE);
				String goname = mySharedPreferences.getString("namemanga", "");
				if (feedsList !=null && !feedsList.isEmpty()) {
					tv_toast.setText(goname+" "+"หน้าที่ "+"0"+"/"+feedsList.size());
				}else{
					tv_toast.setText(goname+" "+"หน้าที่ "+"0"+"/"+"0");
				}
				
				refresh.setRefreshing(false);
				SharedPreferences settings2 = getSharedPreferences(perswitch2, 0);
				boolean ischeck2 = settings2.getBoolean("switchkey2", false);
				if(ischeck2){
				//	recyclerView.smoothScrollToPosition(feedsList.size());
				}
                adapter.setOnItemClickListener(new OnItemClickListener() {
						@Override
						public void onItemClick(JSONData item) {

						}
					});
					
            } else {
				status = true;
				progress.setVisibility(View.GONE);
				
                new Handler().postDelayed(new Runnable() {
						public void run() {
							refresh.setRefreshing(false);
							tvload.setText("แตะเพื่อลองใหม่");
						}
					}, 1000);
				if (feedsList !=null && !feedsList.isEmpty()) {
					
				}else{
					ppscroll.setVisibility(View.GONE);
					customtoast.setVisibility(View.GONE);
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
            for (int i = 0; i < posts.length(); i++) {
                JSONObject post = posts.optJSONObject(i);
                model_readmanga item = new model_readmanga();
                item.setView_a4(post.optString("view_manga_a4"));
				item.setView_manga(post.optString("view_manga"));
                feedsList.add(item);
				}


        } catch (JSONException e) {
            e.printStackTrace();
        }
		
		
		
    }

	@Override
	protected void onPause()
		{
		super.onPause ();
			SharedPreferences settings = read_manga.this.getSharedPreferences("goep", Context.MODE_PRIVATE);
			settings.edit().remove("goep").commit();
			stopscroll();
			}
	
	@Override
	protected void onResume() {
		super.onResume();
		if(istouch){
			if(status){
				getWindow().getDecorView().setSystemUiVisibility(
					View.SYSTEM_UI_FLAG_FULLSCREEN
					| View.SYSTEM_UI_FLAG_LOW_PROFILE
					| View.SYSTEM_UI_FLAG_LAYOUT_STABLE
					| View.SYSTEM_UI_FLAG_IMMERSIVE
					| View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
					| View.SYSTEM_UI_FLAG_HIDE_NAVIGATION);	
			}
			}
			
		}
}
	
