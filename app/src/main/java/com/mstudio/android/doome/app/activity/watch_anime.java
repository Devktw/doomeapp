package com.mstudio.android.doome.app.activity;


import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomSheetBehavior;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.WindowManager;
import android.webkit.WebView;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.MediaController;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;
import com.google.android.exoplayer2.DefaultLoadControl;
import com.google.android.exoplayer2.ExoPlaybackException;
import com.google.android.exoplayer2.ExoPlayer;
import com.google.android.exoplayer2.ExoPlayerFactory;
import com.google.android.exoplayer2.LoadControl;
import com.google.android.exoplayer2.PlaybackParameters;
import com.google.android.exoplayer2.Player;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.Timeline;
import com.google.android.exoplayer2.extractor.DefaultExtractorsFactory;
import com.google.android.exoplayer2.extractor.ExtractorsFactory;
import com.google.android.exoplayer2.source.ExtractorMediaSource;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.source.TrackGroupArray;
import com.google.android.exoplayer2.trackselection.AdaptiveTrackSelection;
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector;
import com.google.android.exoplayer2.trackselection.TrackSelectionArray;
import com.google.android.exoplayer2.trackselection.TrackSelector;
import com.google.android.exoplayer2.ui.AspectRatioFrameLayout;
import com.google.android.exoplayer2.ui.PlayerControlView;
import com.google.android.exoplayer2.ui.SimpleExoPlayerView;
import com.google.android.exoplayer2.upstream.BandwidthMeter;
import com.google.android.exoplayer2.upstream.DefaultAllocator;
import com.google.android.exoplayer2.upstream.DefaultBandwidthMeter;
import com.google.android.exoplayer2.upstream.DefaultHttpDataSourceFactory;
import com.mstudio.android.doome.app.R;
import com.mstudio.android.doome.app.adapter.adapter_nes_ep;
import com.mstudio.android.doome.app.fragment.formation_anime;

import com.mstudio.android.doome.app.model.JSONEp;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import com.squareup.picasso.Picasso;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.animation.Animation;
import android.view.animation.AlphaAnimation;
import android.view.animation.AnimationSet;
import android.os.Build;
import android.graphics.PixelFormat;
import android.view.Gravity;
import android.view.ViewGroup.MarginLayoutParams;
import android.widget.Toast;
import android.widget.Toast;
import android.text.TextUtils;
import android.view.ViewGroup;
import java.time.Duration;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.text.ParseException;
import java.util.concurrent.TimeUnit;
import com.google.android.exoplayer2.source.hls.HlsMediaSource;
import android.widget.FrameLayout.LayoutParams;
import android.preference.PreferenceManager;
import android.widget.Toast;
import android.view.ViewOutlineProvider;
import android.graphics.Outline;
import android.app.Dialog;
import android.view.Window;
import android.graphics.drawable.ColorDrawable;
import android.animation.ValueAnimator;
import android.animation.PropertyValuesHolder;
import android.animation.ObjectAnimator;
import com.mstudio.android.doome.app.LinearLayoutManagerWithSmoothScroller;
import android.view.animation.OvershootInterpolator;
import android.view.Display;
import android.graphics.Point;


public class watch_anime extends AppCompatActivity  {




    ImageView imageView;
    private Handler mHandler = new Handler();

    private static final String TAG = "watch_anime";
	private WebView webview;

    String  image_anime, name_anime,year,formation,valus,seson,mini_story,urlep,package1,status;
	View testview;
    private Toolbar toolbar;
	final Fragment fragment1 = new formation_anime();
	final FragmentManager fm = getSupportFragmentManager();
	Fragment active = fragment1;
	boolean isfull;
	TextView name_watch;
	LinearLayout back;
	ImageView setfull;

	
	private MediaController ctlr;
	private static watch_anime instance;

	@SuppressLint("SetJavaScriptEnabled")

	final String videoURL = "https://mrjovpn.tk/cd.mp4";

// private static final DefaultBandwidthMeter BANDWIDTH_METER = new DefaultBandwidthMeter();

	private int MIN_BUFFER_DURATION = 100000;
//Max Video you want to buffer during PlayBack
	private int MAX_BUFFER_DURATION = 100000;
//Min Video you want to buffer before start Playing it
	private int MIN_PLAYBACK_START_BUFFER = 2000;
//Min video You want to buffer when user resumes video
	private int MIN_PLAYBACK_RESUME_BUFFER = 2000;

	private ImageView play2;
	private ImageView pause2;
	int flag;
    private ProgressBar progress;
	private MediaPlayer mediaPlayer;
	private int lengthOfAudio;
	private SeekBar seekBar;
	SimpleExoPlayerView exoPlayerView;
	ImageView exitfull;
	ImageView play_again;
	private LinearLayout control;
    // creating a variable for exoplayer
    SimpleExoPlayer exoPlayer;
	Context mContext;
	final	boolean isplay = false;

	boolean fullscreen = false;
	private RelativeLayout playcenter;
	private ImageView goten;
	private ImageView backten;
	private ProgressBar progressBar;
	private ImageView play;
	private ImageView pause;
	Boolean isbuf=false; 
	private RelativeLayout playnotfull;
	private Handler handler;
	private Runnable runnable;
	private LinearLayout maincontrol;
	AspectRatioFrameLayout asf;

	boolean isend ;
	View line_formation;
	FrameLayout content_formation;
	RelativeLayout layoutBottomSheet; 
    BottomSheetBehavior sheetBehavior; 
	RelativeLayout layoutBottomSheet_seemore; 
    BottomSheetBehavior sheetBehavior_seemore; 
	private ImageView hide;
	private ImageView hide_seemore;
    private static final String ep = "RecyclerViewJSON";
    private List<JSONEp> feedsList;
    private RecyclerView recyclerView;

	final String url = "https://mrjovpn.tk/animerecom.php";
	private adapter_nes_ep adapter;

	private TextView tvload;
	private ProgressBar progressep;
	RelativeLayout Rl;
	Boolean isbottom1show=false;
	Boolean isbottom2show=false;
	private ImageView imageView_hot;
	private TextView textView_hot;
	private TextView yearr;
	private TextView formationn;
	private TextView valuss;
	private TextView sesonn;
	private TextView mini_storyy;
	boolean ishidefull;
	Boolean isbottomlab1=false;
	Boolean isbottomlab2=false;
	private TextView tv_count;
	private int overallXScroll = 0;
	private ImageView open_window;
	public static Handler myHandler = new Handler();
	private static final int TIME_TO_WAIT = 2000;
	Boolean isplaymini =false;
	Boolean isgotimesucces=false;
	ProgressBar bufferbar;
	private LinearLayout layout_premium;
	private RelativeLayout control_bottom;
	private boolean isresume;
	private boolean iscolsewindow=false;
	private boolean isoncreate;
	boolean exoisshowcontroler;
	private TextView tv_count_fullscreen;
	private TextView formation_watch;
    @Override
    public void onCreate(Bundle savedInstanceState) {
		instance = this;
		setTheme(R.style.WatchDark);
        super.onCreate(savedInstanceState);

        setContentView(R.layout.watch_anime);

		exoPlayerView=findViewById(R.id.video_player);
		tv_count_fullscreen=findViewById(R.id.tv_count_fullscreen);
		name_watch=findViewById(R.id.name_watch);
		control_bottom=findViewById(R.id.control_bottom);
		back=findViewById(R.id.back);
		exitfull=findViewById(R.id.exitfull);
		playcenter=findViewById(R.id.playcenter);
		play2=findViewById(R.id.play2);
		pause2=findViewById(R.id.pause2);
		goten=findViewById(R.id.goten);
		backten=findViewById(R.id.backten);
		play_again=findViewById(R.id.play_again);
		layout_premium=findViewById(R.id.layout_premium);
		progressBar=findViewById(R.id.progressBar);
		play=findViewById(R.id.play);
		pause=findViewById(R.id.pause);
		formation_watch=findViewById(R.id.formation_watch);
		playnotfull=findViewById(R.id.playnotfull);
		maincontrol=findViewById(R.id.maincontrol);
		line_formation=findViewById(R.id.line_formation);
		content_formation=findViewById(R.id.content_formation);
		layoutBottomSheet = (RelativeLayout) findViewById(R.id.bottom_ep); 
		layoutBottomSheet_seemore = (RelativeLayout) findViewById(R.id.bottom_seemore); 
		hide=findViewById(R.id.hide);
		hide_seemore=findViewById(R.id.hide_seemore);
		Intent intent = getIntent();
        image_anime = intent.getStringExtra("image_anime");
        name_anime = intent.getStringExtra("name_anime");
		year = intent.getStringExtra("year");
		formation = intent.getStringExtra("formation");
		valus = intent.getStringExtra("valus");
		seson = intent.getStringExtra("seson");
		mini_story = intent.getStringExtra("mini_story");
		urlep=intent.getStringExtra("url_ep");
		package1=intent.getStringExtra("package1");
		status=intent.getStringExtra("status");
		tvload=findViewById(R.id.textre);
		progressep=findViewById(R.id.progressep);
		bufferbar=findViewById(R.id.bufferbar);

		recyclerView = findViewById(R.id.ep_all);
		imageView_hot = (ImageView)findViewById(R.id.image_hot);
		textView_hot = (TextView) findViewById(R.id.title_hot);

		yearr = (TextView) findViewById(R.id.year_about);
		formationn = (TextView) findViewById(R.id.formation_about);
		valuss = (TextView) findViewById(R.id.valus_about);
		sesonn = (TextView) findViewById(R.id.seson_about);
		mini_storyy = (TextView) findViewById(R.id.mini_story);
		open_window=findViewById(R.id.open_window);

		mini_storyy.setText(mini_story);
		textView_hot.setText(name_anime);
		yearr.setText("ปี: "+year);
		formationn.setText("แนว: "+formation);
		valuss.setText("จำนวนตอน: "+valus);
		sesonn.setText("ซีซี่น: "+seson);
		Picasso.with(mContext).load(image_anime)
			.into(imageView_hot);
		tv_count = (TextView) findViewById(R.id.tv_count);

		recyclerView.setLayoutManager(new LinearLayoutManagerWithSmoothScroller(watch_anime.this,LinearLayoutManagerWithSmoothScroller.VERTICAL, false));
		//String url = "http://stacktips.com/?json=get_category_posts&slug=news&count=30";
		name_watch.setSelected(true);
		LinearLayout.LayoutParams paramss = (LinearLayout.LayoutParams) name_watch.getLayoutParams();
		paramss.height=60;
		paramss.width=500;
		name_watch.setLayoutParams(paramss);
		isoncreate=true;
	
		
		
		
		
		
		
		
		SharedPreferences sh5 =getSharedPreferences("is5s", MODE_PRIVATE);
		SharedPreferences.Editor ett = sh5.edit();
		ett.putBoolean("is5s", false);
		ett.commit();
		
		SharedPreferences shwatch =getSharedPreferences("iswatchexit", MODE_PRIVATE);
		SharedPreferences.Editor ew = shwatch.edit();
		ew.putBoolean("iswatchexit", false);
		ew.commit();
		open_window.setOnClickListener(new OnClickListener(){

				@Override
				public void onClick(View p1) {
					if (!android.provider.Settings.canDrawOverlays(watch_anime.this)) {
						Intent intent = new Intent(android.provider.Settings.ACTION_MANAGE_OVERLAY_PERMISSION,
												   Uri.parse("package:" + getPackageName()));
						startActivity(intent);
					}else{
						if(isfull){
							getWindow().clearFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);

							getWindow().getDecorView().setSystemUiVisibility(
								View.SYSTEM_UI_FLAG_LAYOUT_STABLE);		

							setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
							RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) exoPlayerView.getLayoutParams();
							params.width = params.MATCH_PARENT;
							params.height = (int) ( 200 * getApplicationContext().getResources().getDisplayMetrics().density);
							exoPlayerView.setLayoutParams(params);
							exoPlayerView.setResizeMode(AspectRatioFrameLayout.RESIZE_MODE_ZOOM);

							playcenter.setVisibility(View.GONE);
							playnotfull.setVisibility(View.VISIBLE);
							control_bottom.setVisibility(View.GONE);
							isfull=false;
							new Handler().postDelayed(new Runnable() {
									public void run() {
										SharedPreferences sp =getSharedPreferences("isopenwindow", MODE_PRIVATE);
										SharedPreferences.Editor et = sp.edit();
										et.putBoolean("isopenwindow", true);
										et.commit();
										showFloatingWindow();
										finish();
									}
								}, 500);

						}else{
							SharedPreferences sp =getSharedPreferences("isopenwindow", MODE_PRIVATE);
							SharedPreferences.Editor et = sp.edit();
							et.putBoolean("isopenwindow", true);
							et.commit();

							showFloatingWindow();
							finish();
						}

					}

				}


			});

		recyclerView.setOnScrollListener(new RecyclerView.OnScrollListener() {
				@Override
				public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
					super.onScrolled(recyclerView, dx, dy);

					overallXScroll = overallXScroll + dx;
					Log.i("check","overall X  = " + overallXScroll);



					LinearLayoutManager lManager = (LinearLayoutManager) recyclerView.getLayoutManager();





					if (overallXScroll==0) {

						recyclerView.setNestedScrollingEnabled(true);
					}else{
						recyclerView.setNestedScrollingEnabled(false);

					}


				}
			});
        sheetBehavior = BottomSheetBehavior.from(layoutBottomSheet); 
		sheetBehavior_seemore = BottomSheetBehavior.from(layoutBottomSheet_seemore); 
		sheetBehavior.setBottomSheetCallback(new BottomSheetBehavior.BottomSheetCallback() { 
				@Override 
				public void onStateChanged(@NonNull View bottomSheet, int newState) { 

					switch (newState) { 

						case BottomSheetBehavior.STATE_HIDDEN: 
							isbottom1show=false;

							break; 
						case BottomSheetBehavior.STATE_EXPANDED: { 
								isbottom1show=true;

							} 
							break; 
						case BottomSheetBehavior.STATE_COLLAPSED: { 
								isbottom1show=true;

							} 
							break; 
						case BottomSheetBehavior.STATE_DRAGGING: 

							break; 
						case BottomSheetBehavior.STATE_SETTLING: 
							break; 
					} 
				} 

				@Override 
				public void onSlide(@NonNull View bottomSheet, float slideOffset) { 

				} 
			}); 
		sheetBehavior_seemore.setBottomSheetCallback(new BottomSheetBehavior.BottomSheetCallback() { 
				@Override 
				public void onStateChanged(@NonNull View bottomSheet, int newState) { 
					switch (newState) { 
						case BottomSheetBehavior.STATE_HIDDEN: 
							isbottom1show=false;


							break; 
						case BottomSheetBehavior.STATE_EXPANDED: { 
								isbottom1show=true;

							} 
							break; 
						case BottomSheetBehavior.STATE_COLLAPSED: { 
								isbottom1show=true;
							} 
							break; 
						case BottomSheetBehavior.STATE_DRAGGING: 
							break; 
						case BottomSheetBehavior.STATE_SETTLING: 
							break; 
					} 
				} 

				@Override 
				public void onSlide(@NonNull View bottomSheet, float slideOffset) { 

				} 
			}); 

		fm.beginTransaction().add(R.id.content_formation, fragment1, "1").commitAllowingStateLoss();
		hide.setOnClickListener(new OnClickListener(){

				@Override
				public void onClick(View p1) {
					sheetBehavior.setState(BottomSheetBehavior.STATE_HIDDEN);
				}


			});
		hide_seemore.setOnClickListener(new OnClickListener(){

				@Override
				public void onClick(View p1) {
					sheetBehavior_seemore.setState(BottomSheetBehavior.STATE_HIDDEN);
				}


			});




		exoPlayerView.setControllerVisibilityListener(new PlayerControlView.VisibilityListener(){

				@Override
				public void onVisibilityChange(int show) {
					if(show==View.VISIBLE){
						progressBar.setVisibility(View.GONE);
						bufferbar.setVisibility(View.GONE);
						line_formation.setVisibility(View.GONE);
						exoisshowcontroler=true;
					}else{
						exoisshowcontroler=false;
						if(!isfull){
							progressBar.setVisibility(View.VISIBLE);
							bufferbar.setVisibility(View.VISIBLE);
							line_formation.setVisibility(View.VISIBLE);

						}else{
							line_formation.setVisibility(View.GONE);
						}

					}
				}
			});


		play_again.setOnClickListener(new OnClickListener(){

				@Override
				public void onClick(View p1) {
					exoPlayer.setPlayWhenReady(true);
					if (exoPlayer.getPlaybackState() == Player.STATE_ENDED){
						exoPlayer.seekTo(0);
						exoPlayer.setPlayWhenReady(true);

					}else{

					}


				}

			});
		play2.setOnClickListener(new OnClickListener(){

				@Override
				public void onClick(View p1) {
					exoPlayer.setPlayWhenReady(true);

					if (exoPlayer.getPlaybackState() == Player.STATE_ENDED){
						exoPlayer.seekTo(0);
						exoPlayer.setPlayWhenReady(true);

					}else{

					}


				}

			});
		play.setOnClickListener(new OnClickListener(){

				@Override
				public void onClick(View p1) {
					exoPlayer.setPlayWhenReady(true);

					if (exoPlayer.getPlaybackState() == Player.STATE_ENDED){
						exoPlayer.seekTo(0);
						exoPlayer.setPlayWhenReady(true);

					}else{

					}


				}

			});


		goten.setOnClickListener(new OnClickListener(){

				@Override
				public void onClick(View p1) {

					if (exoPlayer.getPlaybackState()== Player.STATE_ENDED){
						goten.setClickable(false);
					}else{
						exoPlayer.seekTo(exoPlayer.getCurrentPosition() + 10000);

					}
				}

			});
		backten.setOnClickListener(new OnClickListener(){

				@Override
				public void onClick(View p1) {

					if(exoPlayer.getCurrentPosition()==0){

					}else{
						exoPlayer.seekTo(exoPlayer.getCurrentPosition() - 10000);

					}



				}

			});

		pause2.setOnClickListener(new OnClickListener(){

				@Override
				public void onClick(View p1) {

					if (exoPlayer.getPlaybackState() == Player.STATE_ENDED){
						exoPlayer.setPlayWhenReady(true);
					}else{
						exoPlayerView.setControllerShowTimeoutMs(0);
						exoPlayerView.setControllerHideOnTouch(false);
						exoPlayer.setPlayWhenReady(false);
					}

				}

			});
		pause.setOnClickListener(new OnClickListener(){

				@Override
				public void onClick(View p1) {

					if (exoPlayer.getPlaybackState() == Player.STATE_ENDED){
						exoPlayer.setPlayWhenReady(true);
					}else{
						exoPlayerView.setControllerShowTimeoutMs(0);
						exoPlayerView.setControllerHideOnTouch(false);
						exoPlayer.setPlayWhenReady(false);
					}

				}

			});
		if(isfull){
			playcenter.setVisibility(View.VISIBLE);
			control_bottom.setVisibility(View.VISIBLE);
			formation_watch.setVisibility(View.VISIBLE);
		}else{
			exoPlayerView.setResizeMode(AspectRatioFrameLayout.RESIZE_MODE_ZOOM);
			control_bottom.setVisibility(View.GONE);
			playcenter.setVisibility(View.GONE);
			formation_watch.setVisibility(View.GONE);
		}
		exitfull.setOnClickListener(new OnClickListener(){

				@Override
				public void onClick(View p1) {
					name_watch.setSelected(true);
					formation_watch.setVisibility(View.GONE);
					LinearLayout.LayoutParams paramss = (LinearLayout.LayoutParams) name_watch.getLayoutParams();
					paramss.height=60;
					paramss.width=500;
					name_watch.setLayoutParams(paramss);

					getWindow().clearFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);

					getWindow().getDecorView().setSystemUiVisibility(
						View.SYSTEM_UI_FLAG_LAYOUT_STABLE);		

					setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
					RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) exoPlayerView.getLayoutParams();
					params.width = params.MATCH_PARENT;
					params.height = (int) ( 200 * getApplicationContext().getResources().getDisplayMetrics().density);
					exoPlayerView.setLayoutParams(params);
					exoPlayerView.setResizeMode(AspectRatioFrameLayout.RESIZE_MODE_ZOOM);

					playcenter.setVisibility(View.GONE);
					playnotfull.setVisibility(View.VISIBLE);
					control_bottom.setVisibility(View.GONE);
					isfull=false;



				}


			});
		setfull = findViewById(R.id.setfull);

		setfull.setOnClickListener(new OnClickListener(){

				@Override
				public void onClick(View p1) {
					line_formation.setVisibility(View.GONE);
					name_watch.setSelected(true);
					formation_watch.setVisibility(View.VISIBLE);
					LinearLayout.LayoutParams paramss = (LinearLayout.LayoutParams) name_watch.getLayoutParams();
					paramss.height=60;
					paramss.width=1000;
					name_watch.setLayoutParams(paramss);
					if(isbottom1show){



						sheetBehavior.setState(BottomSheetBehavior.STATE_HIDDEN);
						sheetBehavior_seemore.setState(BottomSheetBehavior.STATE_HIDDEN);
						new Handler().postDelayed(new Runnable() {
								public void run() {
									setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
									RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) exoPlayerView.getLayoutParams();
									params.width = params.WRAP_CONTENT;

									params.height = params.MATCH_PARENT;
									exoPlayerView.setLayoutParams(params);
									exoPlayerView.setResizeMode(AspectRatioFrameLayout.RESIZE_MODE_FIT);
									getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_FULLSCREEN
																					 |View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
																					 |View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY|View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION);
									playcenter.setVisibility(View.VISIBLE);
									control_bottom.setVisibility(View.VISIBLE);
									playnotfull.setVisibility(View.GONE);
									isfull=true;

								}
							}, 500);
					}else{
						setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
						RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) exoPlayerView.getLayoutParams();
						params.width = params.WRAP_CONTENT;

						params.height = params.MATCH_PARENT;
						exoPlayerView.setLayoutParams(params);
						exoPlayerView.setResizeMode(AspectRatioFrameLayout.RESIZE_MODE_FIT);
						getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_FULLSCREEN
																		 |View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
																		 |View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY|View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION);
						playcenter.setVisibility(View.VISIBLE);
						control_bottom.setVisibility(View.VISIBLE);
						playnotfull.setVisibility(View.GONE);
						isfull=true;
						formation_watch.setVisibility(View.VISIBLE);

					}





				}
			});

		progress=findViewById(R.id.progress);


		back.setOnClickListener(new OnClickListener(){

				@Override
				public void onClick(View p1) {

					if(fullscreen){
						name_watch.setSelected(true);
						formation_watch.setVisibility(View.GONE);
						LinearLayout.LayoutParams paramss = (LinearLayout.LayoutParams) name_watch.getLayoutParams();
						paramss.height=60;
						paramss.width=500;
						name_watch.setLayoutParams(paramss);

						getWindow().clearFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);

						getWindow().getDecorView().setSystemUiVisibility(
							View.SYSTEM_UI_FLAG_LAYOUT_STABLE);		

						setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
						RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) exoPlayerView.getLayoutParams();
						params.width = params.MATCH_PARENT;
						params.height = (int) ( 200 * getApplicationContext().getResources().getDisplayMetrics().density);
						exoPlayerView.setLayoutParams(params);
						exoPlayerView.setResizeMode(AspectRatioFrameLayout.RESIZE_MODE_ZOOM);

						playcenter.setVisibility(View.GONE);
						playnotfull.setVisibility(View.VISIBLE);
						control_bottom.setVisibility(View.GONE);
						isfull=false;




					}else{
						finish();
						new Handler().postDelayed(new Runnable() {
								public void run() {
									ExoPlayer player = (ExoPlayer) exoPlayerView.getPlayer();
									if (player != null) {
										player.stop();
									}
								}
							}, 200);
					}

				}
			});

	}




	private WindowManager windowManager;

	private WindowManager.LayoutParams layoutParams;

	private View displayView;
	Boolean onopen=false;
	private ImageView close;
	private ImageView playmini;
	private ImageView pausemini;
	private ImageView maximize;
	private RelativeLayout mini_control;
	private RelativeLayout mini_vis;
	private RelativeLayout playnotfull2;
	private SimpleExoPlayerView exoPlayerView2;
	private ProgressBar progressmini;
	private ImageView openfull;
	private ImageView playagain_mini;
	boolean isend2 =false;
	boolean ismaximize1=true;
	boolean ismaximize2;
	boolean ismaximize3;
	private int marginX = 20; // กำหนดระยะห่างจากขอบด้านซ้ายและขวา
    private int marginY = 20; // กำหนดระยะห่างจากขอบด้านบนและด้านล่าง
    private int screenWidth, screenHeight;
	private void showFloatingWindow() {

		windowManager = (WindowManager) getSystemService(WINDOW_SERVICE); 
		layoutParams = new WindowManager.LayoutParams();

		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) { 
			layoutParams.type = WindowManager.LayoutParams.TYPE_APPLICATION_OVERLAY; 
		} else { 
			layoutParams.type = WindowManager.LayoutParams.TYPE_PHONE; 

		}

		layoutParams.flags = 
			WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE|WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS;

		


		LayoutInflater layoutInflater = LayoutInflater.from(this);

		displayView = layoutInflater.inflate(R.layout.player_layout, null); 
		close=displayView.findViewById(R.id.closewindow);
		mini_vis=displayView.findViewById(R.id.mini_vis);
		exoPlayerView2=displayView.findViewById(R.id.video_player);
		progressmini= displayView.findViewById(R.id.progressmini);
		playagain_mini= displayView.findViewById(R.id.play_again_mini);
		openfull=displayView.findViewById(R.id.openfull);
		bufferbar=displayView.findViewById(R.id.bufferbar);
		maximize=displayView.findViewById(R.id.maximize);
		
		openfull.setOnClickListener(new OnClickListener(){

				@Override
				public void onClick(View p1) {
					SharedPreferences sp = getSharedPreferences("isopenwindow", 0);
					boolean isopenwindow = sp.getBoolean("isopenwindow", false);
					
					SharedPreferences spp = getSharedPreferences("ismainexit", 0);
					boolean ismainexit = spp.getBoolean("ismainexit", false);
					
					SharedPreferences shwatch = getSharedPreferences("ismainexit", 0);
					boolean iswatchexit = shwatch.getBoolean("ismainexit", false);
					
					if(isopenwindow&&ismainexit&&iswatchexit){
						SharedPreferences sh5 = getSharedPreferences("is5s", 0);
						boolean is5s = sh5.getBoolean("is5s", false);
						if(is5s){
							closes();
							startActivity(getIntent());
							int i =(int) exoPlayer.getContentPosition();
							SharedPreferences scrollto =getApplicationContext().getSharedPreferences("gotime", 0);
							SharedPreferences.Editor editorto = scrollto.edit();
							editorto.putInt("gopositiontime",i);
							editorto.apply();
							new Handler().postDelayed(new Runnable() {
									public void run() {
										ExoPlayer player = (ExoPlayer) exoPlayerView.getPlayer();
										if (player != null) {
											player.stop();

										}
									}
								}, 400);
						}else{
							new Handler().postDelayed(new Runnable() {
									public void run() {
										if(!iscolsewindow){
											closes();
											startActivity(getIntent());
											int i =(int) exoPlayer.getContentPosition();
											SharedPreferences scrollto =getApplicationContext().getSharedPreferences("gotime", 0);
											SharedPreferences.Editor editorto = scrollto.edit();
											editorto.putInt("gopositiontime",i);
											editorto.apply();
											new Handler().postDelayed(new Runnable() {
													public void run() {
														ExoPlayer player = (ExoPlayer) exoPlayerView.getPlayer();
														if (player != null) {
															player.stop();

														}
													}
												}, 400);
										}
										
									}
								}, 5000);
						}
						
					}else{
						closes();
						
						startActivity(getIntent());
						int i =(int) exoPlayer.getContentPosition();
						SharedPreferences scrollto =getApplicationContext().getSharedPreferences("gotime", 0);
						SharedPreferences.Editor editorto = scrollto.edit();
						editorto.putInt("gopositiontime",i);
						editorto.apply();
						new Handler().postDelayed(new Runnable() {
								public void run() {
									ExoPlayer player = (ExoPlayer) exoPlayerView.getPlayer();
									if (player != null) {
										player.stop();

									}
								}
							}, 400);
					}
					
					
					
				}
			});
	    start();
		playmini=displayView.findViewById(R.id.playmini);
		pausemini=displayView.findViewById(R.id.pausemini);
		playmini.setOnClickListener(new OnClickListener(){

				@Override
				public void onClick(View p1) {
					exoPlayer.setPlayWhenReady(true);

					if (exoPlayer.getPlaybackState() == Player.STATE_ENDED){
						exoPlayer.seekTo(0);
						exoPlayer.setPlayWhenReady(true);

					}else{

					}


				}

			});
		playagain_mini.setOnClickListener(new OnClickListener(){

				@Override
				public void onClick(View p1) {
					restart();
					isend2=false;
					exoPlayer.setPlayWhenReady(true);
					if (exoPlayer.getPlaybackState() == Player.STATE_ENDED){
						exoPlayer.seekTo(0);
						exoPlayer.setPlayWhenReady(true);

					}else{

					}


				}

			});
		pausemini.setOnClickListener(new OnClickListener(){

				@Override
				public void onClick(View p1) {

					if (exoPlayer.getPlaybackState() == Player.STATE_ENDED){
						exoPlayer.setPlayWhenReady(true);
					}else{
						exoPlayerView.setControllerShowTimeoutMs(0);
						exoPlayerView.setControllerHideOnTouch(false);
						exoPlayer.setPlayWhenReady(false);
					}

				}

			});

		progressBar= displayView.findViewById(R.id.progressBar);

		close.setOnClickListener(new OnClickListener(){

				@Override
				public void onClick(View p1) {
					closes();

					SharedPreferences settings = getApplicationContext().getSharedPreferences("saveselect", Context.MODE_PRIVATE);
					settings.edit().clear().commit();
					SharedPreferences settingstime = getApplicationContext().getSharedPreferences("gotime", Context.MODE_PRIVATE);
					settingstime.edit().clear().commit();
					new Handler().postDelayed(new Runnable() {
							public void run() {
								ExoPlayer player = (ExoPlayer) exoPlayerView.getPlayer();
								if (player != null) {
									player.stop();
								}
							}
						}, 1000);

				}



			});

		if(isbuf){
			progressmini.setVisibility(View.VISIBLE);
		}else{
			progressmini.setVisibility(View.GONE);
		}
		if(isplaymini){
			if(isbuf){
				playmini.setVisibility(View.GONE);
				pausemini.setVisibility(View.GONE);
			}else{
				playmini.setVisibility(View.GONE);
				pausemini.setVisibility(View.VISIBLE);
			}

		}else{
			if(isbuf){
				playmini.setVisibility(View.GONE);
				pausemini.setVisibility(View.GONE);
			}else{
				playmini.setVisibility(View.VISIBLE);
				pausemini.setVisibility(View.GONE);
			}

		}
	 	exoPlayerView2.setPlayer(exoPlayer);
		exoPlayerView2.setUseController(false);
		exoPlayerView2.setResizeMode(AspectRatioFrameLayout.RESIZE_MODE_FILL);


		layoutParams.width = 600;
		layoutParams.height = 340;
		
		
		maximize.setOnClickListener(new OnClickListener(){

				@Override
				public void onClick(View p1) {
					if(ismaximize1){
						layoutParams.width = 750;
						layoutParams.height = 450;
						windowManager.updateViewLayout(displayView, layoutParams);
						ismaximize2=true;
						ismaximize1=false;
						ismaximize3=false;
					}else{
						if(ismaximize2){
							layoutParams.width = 950;
							layoutParams.height = 550;
							windowManager.updateViewLayout(displayView, layoutParams);
							ismaximize3=true;
							ismaximize2=false;
							ismaximize1=false;
						}else{
							if(ismaximize3){
								layoutParams.width = 650;
								layoutParams.height = 350;
								windowManager.updateViewLayout(displayView, layoutParams);
								ismaximize3=false;
								ismaximize2=false;
								ismaximize1=true;
							}
						}
						
					
					}
					
				}
				
		});
		displayView.setLayoutParams(layoutParams);
		
		windowManager.addView(displayView, layoutParams);
		
		exoPlayerView2.setOnTouchListener(new FloatingOnTouchListener2());
		exoPlayer.addListener(new ExoPlayer.EventListener() {

				@Override
				public void onPlayerError(ExoPlaybackException p1) {
				}

				@Override
				public void onPlaybackParametersChanged(PlaybackParameters p1) {
				}


				private boolean isbuf2 =false;
				private boolean isplaymini=false;

				@Override
				public void onTimelineChanged(Timeline p1, Object p2, int p3) {
				}

				@Override
				public void onRepeatModeChanged(int p1) {
				}

				@Override
				public void onShuffleModeEnabledChanged(boolean p1) {
				}
				@Override
				public void onPositionDiscontinuity(int p1) {
				}

				@Override
				public void onSeekProcessed() {
				}

				@Override
				public void onTracksChanged(TrackGroupArray trackGroups, TrackSelectionArray trackSelections) {}

				@Override
				public void onLoadingChanged(boolean isLoading) {

				}

				@Override
				public void onPlayerStateChanged(boolean playWhenReady, int playbackState) {
					if(isend2){
						progressmini.setVisibility(View.GONE);
						playmini.setVisibility(View.GONE);
						pausemini.setVisibility(View.GONE);
						playagain_mini.setVisibility(View.VISIBLE);
						mini_vis.setVisibility(View.VISIBLE);
					}
					if(playWhenReady){
						if(isend2){
							progressmini.setVisibility(View.GONE);
							playmini.setVisibility(View.GONE);
						    pausemini.setVisibility(View.GONE);
							playagain_mini.setVisibility(View.VISIBLE);
							mini_vis.setVisibility(View.VISIBLE);
						}else{
							playmini.setVisibility(View.GONE);
							pausemini.setVisibility(View.VISIBLE);
						}
						isplaymini=true;

						playagain_mini.setVisibility(View.GONE);
					}else{
						if(isend2){
							progressmini.setVisibility(View.GONE);
							playmini.setVisibility(View.GONE);
							pausemini.setVisibility(View.GONE);
							playagain_mini.setVisibility(View.VISIBLE);
							mini_vis.setVisibility(View.VISIBLE);
						}else{
							playmini.setVisibility(View.VISIBLE);
							pausemini.setVisibility(View.GONE);
						}

						isplaymini=false;
						playagain_mini.setVisibility(View.GONE);
					}
					if(playbackState==Player.STATE_BUFFERING){
						progressmini.setVisibility(View.VISIBLE);
						playmini.setVisibility(View.GONE);
						pausemini.setVisibility(View.GONE);
						isbuf2=true;
						isbuf=true;
					}else{
						isbuf=false;
						isbuf2=false;
						progressmini.setVisibility(View.GONE);
						if(isplaymini){
							playmini.setVisibility(View.GONE);
							pausemini.setVisibility(View.VISIBLE);
						}else{
							playmini.setVisibility(View.VISIBLE);
							pausemini.setVisibility(View.GONE);
						}
					}
					if(playbackState==Player.STATE_ENDED){
						progressmini.setVisibility(View.GONE);
						playmini.setVisibility(View.GONE);
						pausemini.setVisibility(View.GONE);
						playagain_mini.setVisibility(View.VISIBLE);
						mini_vis.setVisibility(View.VISIBLE);
						isend2=true;
					}
					if(isbuf2){
						playmini.setVisibility(View.GONE);
						pausemini.setVisibility(View.GONE);
						progressmini.setVisibility(View.VISIBLE);
					}else{
						progressmini.setVisibility(View.GONE);
					}
					if(isplaymini){
						if(isend2){
							progressmini.setVisibility(View.GONE);
							playmini.setVisibility(View.GONE);
							pausemini.setVisibility(View.GONE);
							playagain_mini.setVisibility(View.VISIBLE);
							mini_vis.setVisibility(View.VISIBLE);
						}else{
							playmini.setVisibility(View.GONE);
							pausemini.setVisibility(View.VISIBLE);
						}

					}else{
						if(isend2){
							progressmini.setVisibility(View.GONE);
							playmini.setVisibility(View.GONE);
							pausemini.setVisibility(View.GONE);
							playagain_mini.setVisibility(View.VISIBLE);
							mini_vis.setVisibility(View.VISIBLE);
						}else{
							playmini.setVisibility(View.VISIBLE);
							pausemini.setVisibility(View.GONE);
						}

					}

				}});

	}


	
	Runnable myRunnable = new Runnable() {
		@Override
		public void run() {
			mini_vis.setVisibility(View.GONE);
			progressBar.setVisibility(View.VISIBLE);
			bufferbar.setVisibility(View.VISIBLE);

			stop();
		}
	};
	private class FloatingOnTouchListener2 implements View.OnTouchListener { 
		private int initialX;
		private int initialY;
		private float initialTouchX;
		private float initialTouchY;

		@Override public boolean onTouch(View view, MotionEvent event) {

			switch (event.getAction()) { 
				case MotionEvent.ACTION_UP:
					if(!isend2){

						if(mini_vis.getVisibility()==View.GONE){
							mini_vis.setVisibility(View.VISIBLE);
							progressBar.setVisibility(View.GONE);
							bufferbar.setVisibility(View.GONE);

							restart();
						}else{
							if(mini_vis.getVisibility()==View.VISIBLE){
								mini_vis.setVisibility(View.GONE);

								bufferbar.setVisibility(View.VISIBLE);
								progressBar.setVisibility(View.VISIBLE);
								stop();
							}
						}}
					Display display = windowManager.getDefaultDisplay();
					Point size = new Point();
					display.getSize(size);
					screenWidth = size.x;
					screenHeight = size.y;
					animateSpringBack();
					break;
				case MotionEvent.ACTION_DOWN: 
					onopen=true;
                    
                    
					initialX = layoutParams.x;
					initialY = layoutParams.y;
					initialTouchX = event.getRawX();
					initialTouchY = event.getRawY();
                    
					break;

				case MotionEvent.ACTION_MOVE: 
					onopen=true;
					int nowX = (int) event.getRawX(); 
					layoutParams.x = initialX + (int) (event.getRawX() - initialTouchX);
					layoutParams.y = initialY + (int) (event.getRawY() - initialTouchY);
                   
                    
					windowManager.updateViewLayout(displayView, layoutParams);
					break; 
				default: 
					break; 
			}
			return true;
		}
	}
	private void animateSpringBack() {
        int finalX;
        int finalY;

        // ตรวจสอบเพื่อให้ Window Floating ไม่เลยขอบจอ
        if (layoutParams.x < marginX) {
            finalX = marginX;
        } else if (layoutParams.x + displayView.getWidth() > screenWidth - marginX) {
            finalX = screenWidth - marginX - displayView.getWidth();
        } else {
            finalX = layoutParams.x;
        }

        if (layoutParams.y < marginY) {
            finalY = marginY;
        } else if (layoutParams.y + displayView.getHeight() > screenHeight - marginY) {
            finalY = screenHeight - marginY - displayView.getHeight();
        } else {
            finalY = layoutParams.y;
        }

        // ใช้ ValueAnimator เพื่อทำ Animation เพื่อให้กลับมาอยู่ในจอ
        final ValueAnimator animatorX = ValueAnimator.ofInt(layoutParams.x, finalX);
        final ValueAnimator animatorY = ValueAnimator.ofInt(layoutParams.y, finalY);

        // กำหนดเวลาและ interpolator ให้กับ Animation
        int animationDuration = 500; // ความยาวของ Animation (หน่วย: มิลลิวินาที)
        animatorX.setDuration(animationDuration);

        animatorY.setDuration(animationDuration);

        // กำหนด interpolator ให้กับ Animation เพื่อให้คล้ายกับการสริง
        animatorX.setInterpolator(new OvershootInterpolator()); // เพิ่ม OvershootInterpolator ที่นี่
        animatorY.setInterpolator(new OvershootInterpolator()); // เพิ่ม OvershootInterpolator ที่นี่

        // กำหนด Listener เพื่ออัปเดตตำแหน่งของ View ตาม Animation
        animatorX.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
				@Override
				public void onAnimationUpdate(ValueAnimator valueAnimator) {
					layoutParams.x = (int) valueAnimator.getAnimatedValue();
					windowManager.updateViewLayout(displayView, layoutParams);
				}
			});
        animatorY.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
				@Override
				public void onAnimationUpdate(ValueAnimator valueAnimator) {
					layoutParams.y = (int) valueAnimator.getAnimatedValue();
					windowManager.updateViewLayout(displayView, layoutParams);
				}
			});

        // เริ่ม Animation
        animatorX.start();
        animatorY.start();
		// ใช้ Handler ในการลดความเร็วของ Animation ตามที่คุณต้องการ


    }
	public void closes(){
		iscolsewindow=true;
		SharedPreferences sp =getSharedPreferences("isopenwindow", MODE_PRIVATE);
		SharedPreferences.Editor et = sp.edit();
		et.putBoolean("isopenwindow", false);
		et.commit();
		WindowManager wm = (WindowManager) getSystemService(WINDOW_SERVICE);
		try {
			displayView.setVisibility(View.INVISIBLE);

		} catch (Exception e) {}

	}


	@Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        View decorView = getWindow().getDecorView();
        if(hasFocus){
			if(isfull){
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
	@Override
	public void onSaveInstanceState(Bundle savedInstanceState)
	{



        Intent intent = getIntent();

        image_anime = intent.getStringExtra("image_anime");
        name_anime = intent.getStringExtra("name_anime");
		year = intent.getStringExtra("year");
		formation = intent.getStringExtra("formation");
		valus = intent.getStringExtra("valus");
		seson = intent.getStringExtra("seson");
		mini_story = intent.getStringExtra("mini_story");
		urlep = intent.getStringExtra("url_ep");

	}

	private class Getep extends AsyncTask<String, Void, Integer> {
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
				progressep.setVisibility(View.GONE);
                adapter = new adapter_nes_ep(watch_anime.this, feedsList);
				recyclerView.setHasFixedSize(true);
		        recyclerView.setAdapter(adapter);

				adapter.notifyDataSetChanged();
				if (feedsList !=null && !feedsList.isEmpty()) {
					if(status.equals("up")){
						tv_count_fullscreen.setText("อัปเดตถึงตอนที่ "+feedsList.size());
					}
					if(status.equals("end")){
						tv_count_fullscreen.setText("ทั้งหมด "+feedsList.size()+" ตอน");
					}

				}else{

				}
            } else {


				if (feedsList !=null && !feedsList.isEmpty()) {
				}else{
					progressep.setVisibility(View.GONE);
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
                JSONEp item = new JSONEp();
                item.setEp_name(post.optString("name_ep"));
                item.setEp_image(post.optString("image_ep"));
				item.setEp(post.optString("ep"));
                feedsList.add(item);
			}


        } catch (JSONException e) {
            e.printStackTrace();
        }

    }





	@Override
	public void onConfigurationChanged(Configuration newConfig)
	{
		Log.d("tag", "config changed");
		super.onConfigurationChanged(newConfig);
		int orientationx = newConfig.orientation;
		if (orientationx == Configuration.ORIENTATION_PORTRAIT){
			setfull.setVisibility(View.VISIBLE);
			exitfull.setVisibility(View.GONE);


			playcenter.setVisibility(View.GONE);
			fullscreen=false;		
		}

		if (orientationx == Configuration.ORIENTATION_LANDSCAPE){
			Log.d("tag", "Landscape");
			playcenter.setVisibility(View.VISIBLE);

			setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
			RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) exoPlayerView.getLayoutParams();
			params.width = params.MATCH_PARENT;

			params.height = params.MATCH_PARENT;
			exoPlayerView.setLayoutParams(params);

			getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_FULLSCREEN
															 |View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
															 |View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
															 |View.SYSTEM_UI_FLAG_LAYOUT_STABLE
															 |View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
															 |View.SYSTEM_UI_FLAG_HIDE_NAVIGATION);

			setfull.setVisibility(View.GONE);
			exitfull.setVisibility(View.VISIBLE);
			fullscreen=true;
			Log.w("tag", "other: " + orientationx);
		}



	}


	@Override 
	public boolean onKeyUp(int keyCode, KeyEvent event)
	{
		if (keyCode == KeyEvent.KEYCODE_BACK) {
			if(fullscreen){
				name_watch.setSelected(true);
				formation_watch.setVisibility(View.GONE);
				LinearLayout.LayoutParams paramss = (LinearLayout.LayoutParams) name_watch.getLayoutParams();
				paramss.height=60;
				paramss.width=500;
				name_watch.setLayoutParams(paramss);

				getWindow().clearFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);

				getWindow().getDecorView().setSystemUiVisibility(
					View.SYSTEM_UI_FLAG_LAYOUT_STABLE);		

				setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
				RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) exoPlayerView.getLayoutParams();
				params.width = params.MATCH_PARENT;
				params.height = (int) ( 200 * getApplicationContext().getResources().getDisplayMetrics().density);
				exoPlayerView.setLayoutParams(params);
				playcenter.setVisibility(View.GONE);
				playnotfull.setVisibility(View.VISIBLE);
				control_bottom.setVisibility(View.GONE);
				exoPlayerView.setResizeMode(AspectRatioFrameLayout.RESIZE_MODE_ZOOM);

				isfull=false;
				if(!exoisshowcontroler){
					progressBar.setVisibility(View.VISIBLE);
					bufferbar.setVisibility(View.VISIBLE);
					line_formation.setVisibility(View.VISIBLE);
				}else{
					progressBar.setVisibility(View.GONE);
					bufferbar.setVisibility(View.GONE);
					line_formation.setVisibility(View.GONE);
				}
					

			
				
			}else{

				if(isbottom1show){
					sheetBehavior.setState(BottomSheetBehavior.STATE_HIDDEN);
					sheetBehavior_seemore.setState(BottomSheetBehavior.STATE_HIDDEN);
					return true;
				}else{

					finish();
					new Handler().postDelayed(new Runnable() {
							public void run() {
								ExoPlayer player = (ExoPlayer) exoPlayerView.getPlayer();
								if (player != null) {
									player.stop();
								}
							}
						}, 200);


				}
			}


		}
		return true;
	}


	public void stop() {
		myHandler.removeCallbacks(myRunnable);
	}
	public void start() {
		myHandler.postDelayed(myRunnable, TIME_TO_WAIT);
	}
	public static watch_anime getInstance() {
        return instance;
    }
	public void restart() {
		myHandler.removeCallbacks(myRunnable);
		myHandler.postDelayed(myRunnable, TIME_TO_WAIT);
	}
	public void resetgotime() {

		SharedPreferences settingstime = getApplicationContext().getSharedPreferences("gotime", Context.MODE_PRIVATE);
		settingstime.edit().clear().commit();
    }

	public void readap() {
		if (feedsList !=null && !feedsList.isEmpty()) {
			
			SharedPreferences settings =watch_anime.this.getSharedPreferences("readap", 0);
			final int statese = settings.getInt("position", 0); //0 is the default value

			LinearLayoutManagerWithSmoothScroller lManager = (LinearLayoutManagerWithSmoothScroller) recyclerView.getLayoutManager();
			lManager.scrollToPositionWithOffset(statese, 0);
			
		}

		
	}
	public void resetbackground() {
		content_formation.setBackgroundColor(0xFF00FF00);
    }
	public void show_formation() {

		sheetBehavior_seemore.setState(BottomSheetBehavior.STATE_COLLAPSED);

    }
	public void refreshep() {
		Intent intent = getIntent();
        image_anime = intent.getStringExtra("image_anime");
        name_anime = intent.getStringExtra("name_anime");
		year = intent.getStringExtra("year");
		formation = intent.getStringExtra("formation");
		valus = intent.getStringExtra("valus");
		seson = intent.getStringExtra("seson");
		mini_story = intent.getStringExtra("mini_story");
		urlep=intent.getStringExtra("url_ep");
		new Getep().execute(urlep);

    }
	@Override
	protected void onStart() {
		super.onStart();

		sheetBehavior.setState(BottomSheetBehavior.STATE_HIDDEN);
		sheetBehavior_seemore.setState(BottomSheetBehavior.STATE_HIDDEN);

	}
	public void checkpackage() {
		if(package1.equals("premium")){
			
			layout_premium.setVisibility(View.VISIBLE);
			final Dialog dialog = new Dialog(watch_anime.this);
			dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
			dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
			dialog.setContentView(R.layout.dialog_premium);
			dialog.setCancelable(false);




			WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
			lp.copyFrom(dialog.getWindow().getAttributes());
			lp.width = WindowManager.LayoutParams.WRAP_CONTENT;
			lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
			((ImageView) dialog.findViewById(R.id.closepremium)).setOnClickListener(new View.OnClickListener() {
					@Override
					public void onClick(View v) {
						dialog.dismiss();
					}
				});
			dialog.show();
			dialog.getWindow().setAttributes(lp);

		}

    }
	
	public void stopexo() {
		SharedPreferences mySharedPreferences = this.getSharedPreferences("myep_name", Context.MODE_PRIVATE);
		String epname = mySharedPreferences.getString("epname", "");
		final String ep = mySharedPreferences.getString("ep", "");
		exoPlayerView.hideController();
		exoPlayerView.setUseController(false);
		progressBar.setVisibility(View.GONE);
		line_formation.setVisibility(View.GONE);
		bufferbar.setVisibility(View.GONE);
		SharedPreferences.Editor editor = mySharedPreferences.edit();
		editor.putString("save_epname",epname);
		editor.putString("save_ep",ep);
		editor.commit();
		name_watch.setText(epname);
		ExoPlayer player = (ExoPlayer) exoPlayerView.getPlayer();
		if (player != null) {
			player.stop();
			player.release();
			player=null;
		}
		}
	
	


    public void proMethod() {
		sheetBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
	    readap();

    }


	public void playurl() {
		exoPlayerView.setUseController(true);
		layout_premium.setVisibility(View.GONE);
		SharedPreferences mySharedPreferences = this.getSharedPreferences("myep_name", Context.MODE_PRIVATE);
		String epname = mySharedPreferences.getString("epname", "");
		final String ep = mySharedPreferences.getString("ep", "");


		SharedPreferences.Editor editor = mySharedPreferences.edit();
		editor.putString("save_epname",epname);
		editor.putString("save_ep",ep);
		editor.commit();
		name_watch.setText(epname);
		formation_watch.setText(formation+" • "+year);


		final LoadControl loadControl = new DefaultLoadControl.Builder()
			.setAllocator(new DefaultAllocator(true, 16))
			.setBufferDurationsMs(MIN_BUFFER_DURATION,
								  MAX_BUFFER_DURATION,
								  MIN_PLAYBACK_START_BUFFER,
								  MIN_PLAYBACK_RESUME_BUFFER)
			.setTargetBufferBytes(-1)
			.setPrioritizeTimeOverSizeThresholds(true).createDefaultLoadControl();

		// bandwisthmeter is used for 
		// getting default bandwidth
		BandwidthMeter bandwidthMeter = new DefaultBandwidthMeter();

		// track selector is used to navigate between 
		// video using a default seekbar.
		final TrackSelector trackSelector = new DefaultTrackSelector(new AdaptiveTrackSelection.Factory(bandwidthMeter));

		// we are adding our track selector to exoplayer.
		exoPlayer = ExoPlayerFactory.newSimpleInstance(this, trackSelector);

		// we are parsing a video url
		// and parsing its video uri.


		final Uri videouri = Uri.parse(ep);

		// we are creating a variable for datasource factory
		// and setting its user agent as 'exoplayer_view'
		final DefaultHttpDataSourceFactory dataSourceFactory = new DefaultHttpDataSourceFactory("exoplayer-codelab");
		// we are creating a variable for extractor factory
		// and setting it to default extractor factory.
		final ExtractorsFactory extractorsFactory = new DefaultExtractorsFactory();

		// we are creating a media source with above variables 
		// and passing our event handler as null,


		// inside our exoplayer view 
		// we are setting our playe

		// we are preparing our exoplayer
		new Handler().postDelayed(new Runnable() {
				public void run() {
					ExoPlayer player = (ExoPlayer) exoPlayerView.getPlayer();
					if (player != null) {
						player.stop();

					}

					exoPlayerView.setPlayer(exoPlayer);
					if(ep.contains("mp4")){
						final MediaSource mp4 = new ExtractorMediaSource(videouri, dataSourceFactory, extractorsFactory, null, null);


						exoPlayer.prepare(mp4);
					}
					if(ep.contains("m3u8")){
						final MediaSource m3u8 = new HlsMediaSource.Factory(dataSourceFactory).createMediaSource(videouri);
						exoPlayer.prepare(m3u8);
					}




					SharedPreferences settings =watch_anime.this.getSharedPreferences("gotime", 0);

					final int gotime = settings.getInt("gopositiontime", 0);

					if(gotime!=0){
						exoPlayer.seekTo(exoPlayer.getCurrentPosition() +gotime);


						isgotimesucces=true;

					}else{

						isgotimesucces=false;
					}

					progress.setVisibility(View.VISIBLE);

					play_again.setVisibility(View.GONE);
					maincontrol.setVisibility(View.GONE);
					line_formation.setVisibility(View.GONE);



					// we are setting our exoplayer
					exoPlayer.setPlayWhenReady(true);

				}
			}, 50);






		exoPlayer.addListener(new ExoPlayer.EventListener() {

				private boolean isbuf2 =false;
				private boolean isplay=false;
				@Override
				public void onTimelineChanged(Timeline p1, Object p2, int p3) {
				}

				@Override
				public void onRepeatModeChanged(int p1) {
				}

				@Override
				public void onShuffleModeEnabledChanged(boolean p1) {
				}

				@Override
				public void onPositionDiscontinuity(int p1) {
				}

				@Override
				public void onSeekProcessed() {
				}

				@Override
				public void onTracksChanged(TrackGroupArray trackGroups, TrackSelectionArray trackSelections) {}

				@Override
				public void onLoadingChanged(boolean isLoading) {

				}

				@Override
				public void onPlayerStateChanged(boolean playWhenReady, int playbackState) {
					if (playbackState == Player.STATE_READY) {
						//creating a method for seekBar progress
						final Toast toast = new Toast(getApplicationContext());

				        if(isgotimesucces){
							SharedPreferences settings =watch_anime.this.getSharedPreferences("gotime", 0);

							final int gotime = settings.getInt("gopositiontime", 0);
							LayoutInflater inflater = getLayoutInflater();

							View layout = inflater.inflate(R.layout.custom_toast,
														   (ViewGroup) findViewById(R.id.customtoast));


							TextView tv =(TextView)layout.findViewById(R.id.tv_toast);



							long timeSec= gotime/1000;// Json output
							int hours = (int) timeSec/ 3600;
							int temp = (int) timeSec- hours * 3600;
							int mins = temp / 60;
							temp = temp - mins * 60;
							int secs = temp;

							String time = hours+ ":"+mins+":"+secs;//hh:mm:ss formatted string


							tv.setText("ข้ามไปที่ "+ time+" แล้ว");


							/* And now you can get the TextView of the default View of the Toast. */
							toast.setGravity(Gravity.TOP|Gravity.LEFT, 80, 480);

							toast.setDuration(Toast.LENGTH_LONG);
							toast.setView(layout);
							toast.show();
							SharedPreferences settingstime = getApplicationContext().getSharedPreferences("gotime", Context.MODE_PRIVATE);
							settingstime.edit().clear().commit();
							isgotimesucces=false;
						}


				        maincontrol.setVisibility(View.VISIBLE);
						exoPlayerView.showController();
						if(!isbuf2){

							exoPlayerView.setControllerShowTimeoutMs(2500);
							exoPlayerView.setControllerHideOnTouch(true);

						}
						handler = new Handler();
						runnable = new Runnable() {
							@Override
							public void run() {

								progressBar.setProgress((int) ((exoPlayer.getCurrentPosition()*100)/exoPlayer.getDuration()));
								bufferbar.setProgress((int) ((exoPlayer.getBufferedPosition()*100/exoPlayer.getDuration())));

								handler.postDelayed(runnable, 1000);
							}
						};
						handler.postDelayed(runnable, 0);

						pause2.setVisibility(View.VISIBLE);
						play2.setVisibility(View.GONE);
						pause.setVisibility(View.VISIBLE);
						play.setVisibility(View.GONE);
						getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
						isbuf=false;
						if(exoPlayer.getCurrentPosition()==0){
							backten.setClickable(false);
						}else{
							backten.setClickable(true);
						}
					} else{

						isbuf=true;

						if(isbuf){
							if(!isbuf2){

							}else{
								maincontrol.setVisibility(View.GONE);
							}
						}else{
							maincontrol.setVisibility(View.GONE);
						}
					}

					if (playWhenReady) {
						isplaymini=true;
						exoPlayerView.setControllerShowTimeoutMs(2500);
						exoPlayerView.setControllerHideOnTouch(true);
						if(!isbuf){
							pause2.setVisibility(View.VISIBLE);
							play2.setVisibility(View.GONE);
							pause.setVisibility(View.VISIBLE);
							play.setVisibility(View.GONE);
							getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);

						}else{
							pause.setVisibility(View.GONE);
							play.setVisibility(View.GONE);
							play2.setVisibility(View.GONE);
							pause2.setVisibility(View.GONE);
						}



					} else {

						isplaymini=false;
						if(!isbuf){
							play2.setVisibility(View.VISIBLE);
							pause2.setVisibility(View.GONE);
							pause.setVisibility(View.GONE);
							play.setVisibility(View.VISIBLE);

							getWindow().clearFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);

						}else{
							pause.setVisibility(View.GONE);
							play.setVisibility(View.GONE);
							play2.setVisibility(View.GONE);
							pause2.setVisibility(View.GONE);
						}




					}


					if (playbackState == Player.STATE_BUFFERING){

						isbuf2=true;
						isbuf=true;
						pause.setVisibility(View.GONE);
						play.setVisibility(View.GONE);
						play2.setVisibility(View.GONE);
						pause2.setVisibility(View.GONE);
						progress.setVisibility(View.VISIBLE);
						play_again.setVisibility(View.GONE);
						exoPlayerView.setControllerShowTimeoutMs(2500);
						exoPlayerView.setControllerHideOnTouch(true);

					} else {
						isbuf2=false;
						progress.setVisibility(View.GONE);
					}
					if (playbackState == Player.STATE_ENDED) {
						goten.setClickable(false);
						maincontrol.setVisibility(View.VISIBLE);
						progress.setVisibility(View.GONE);
						play2.setVisibility(View.GONE);
						pause2.setVisibility(View.GONE);
						pause.setVisibility(View.GONE);
						play.setVisibility(View.GONE);
						play_again.setVisibility(View.VISIBLE);
						exoPlayerView.setControllerShowTimeoutMs(0);
						exoPlayerView.setControllerHideOnTouch(false);


					}else{

						goten.setClickable(true);
					}

				}
				@Override
				public void onPlayerError(ExoPlaybackException error) {
					switch (error.type) {
						case ExoPlaybackException.TYPE_SOURCE:
							Log.e(TAG, "TYPE_SOURCE: " + error.getSourceException().getMessage());

							break;

						case ExoPlaybackException.TYPE_RENDERER:
							Log.e(TAG, "TYPE_RENDERER: " + error.getRendererException().getMessage());
							Toast.makeText(watch_anime.this,"การเชื่อมต่อล่าช้า2",Toast.LENGTH_SHORT).show();

							break;

						case ExoPlaybackException.TYPE_UNEXPECTED:
							Log.e(TAG, "TYPE_UNEXPECTED: " + error.getUnexpectedException().getMessage());
							Toast.makeText(watch_anime.this,"การเชื่อมต่อล่าช้า3",Toast.LENGTH_SHORT).show();

							break;
					}
				}




				@Override
				public void onPlaybackParametersChanged(PlaybackParameters playbackParameters) {}
			});
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		SharedPreferences sp = getSharedPreferences("isopenwindow", 0);
		boolean isopenwindow = sp.getBoolean("isopenwindow", false);
		new Handler().postDelayed(new Runnable() {
				public void run() {
					SharedPreferences sp =getSharedPreferences("is5s", MODE_PRIVATE);
					SharedPreferences.Editor et = sp.edit();
					et.putBoolean("is5s", true);
					et.commit();
				}
			}, 5000);
		if(!isopenwindow){
			SharedPreferences settings = getApplicationContext().getSharedPreferences("saveselect", Context.MODE_PRIVATE);
			settings.edit().clear().commit();
			SharedPreferences settingstime = getApplicationContext().getSharedPreferences("gotime", Context.MODE_PRIVATE);
			settingstime.edit().clear().commit();
		}else{
			SharedPreferences sh5 =getSharedPreferences("iswatchexit", MODE_PRIVATE);
			SharedPreferences.Editor ett = sh5.edit();
			ett.putBoolean("iswatchexit", true);
			ett.commit();
		}
		
	}

	@Override
	protected void onStop() {
		super.onStop();
		SharedPreferences sp = getSharedPreferences("isopenwindow", 0);
		boolean isopenwindow = sp.getBoolean("isopenwindow", false);
		new Handler().postDelayed(new Runnable() {
				public void run() {
					SharedPreferences sp =getSharedPreferences("is5s", MODE_PRIVATE);
					SharedPreferences.Editor et = sp.edit();
					et.putBoolean("is5s", true);
					et.commit();
				}
			}, 5000);
		if(!isopenwindow){
			SharedPreferences settings = getApplicationContext().getSharedPreferences("saveselect", Context.MODE_PRIVATE);
			settings.edit().clear().commit();
			SharedPreferences settingstime = getApplicationContext().getSharedPreferences("gotime", Context.MODE_PRIVATE);
			settingstime.edit().clear().commit();
		}else{
			SharedPreferences sh5 =getSharedPreferences("iswatchexit", MODE_PRIVATE);
			SharedPreferences.Editor ett = sh5.edit();
			ett.putBoolean("iswatchexit", true);
			ett.commit();
		}
	}

	

	@Override
	protected void onPause()
	{
		super.onPause();
		SharedPreferences sp = getSharedPreferences("isopenwindow", 0);
		boolean isopenwindow = sp.getBoolean("isopenwindow", false);
		
		ExoPlayer player = (ExoPlayer) exoPlayerView.getPlayer();

		if (player != null) {
			if(isopenwindow){
				exoPlayer.setPlayWhenReady(true);

			}else{
				exoPlayer.setPlayWhenReady(false);
			}


		}
	}

	@Override
	protected void onResume() {
		super.onResume();
		
		if(isfull){

			getWindow().setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
								 WindowManager.LayoutParams.FLAG_LAYOUT_IN_SCREEN);

			getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_FULLSCREEN
															 |View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
															 |View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY|View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION);

		}
		ExoPlayer player = (ExoPlayer) exoPlayerView.getPlayer();
        if (player != null) {
			exoPlayer.setPlayWhenReady(true);

        }

	}


}
	

