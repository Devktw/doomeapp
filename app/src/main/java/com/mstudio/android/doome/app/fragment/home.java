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
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import com.mstudio.android.doome.app.R;
import com.mstudio.android.doome.app.activity.anime_hot;

import android.support.v7.widget.CardView;
import com.mstudio.android.doome.app.model.JSONData;
import com.mstudio.android.doome.app.model.JsonData2;
import com.mstudio.android.doome.app.model.JSONData3;
import com.mstudio.android.doome.app.adapter.adapter;
import java.util.Collections;
import com.mstudio.android.doome.app.activity.search_activity;

import com.mstudio.android.doome.app.anime_formation.anime_fragment;
import com.mstudio.android.doome.app.anime_formation.anime_fragment_fight;
import com.mstudio.android.doome.app.anime_formation.anime_fragment_aventure;
import com.mstudio.android.doome.app.anime_formation.anime_fragment_drama;
import com.mstudio.android.doome.app.anime_formation.anime_fragment_school;
import com.mstudio.android.doome.app.anime_formation.anime_fragment_love;
import com.mstudio.android.doome.app.anime_formation.anime_fragment_funny;
import com.mstudio.android.doome.app.anime_formation.anime_fragment_fantasy;
import com.mstudio.android.doome.app.adapter.adapter_dinamic;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;





public class home extends Fragment {

	
	TabLayout tab; 
    ViewPager pager;
	private CardView search;
	private static final String TAG = "RecyclerViewJSON";
    private List<JSONData> feedsList;
	private List<JsonData2> feedsList2;
	private List<JSONData3> feedsList3;
    private RecyclerView recyclerView;
    private adapter_dinamic adapter;
	final String url = "https://github.com/Devktw/Doome/raw/main/doome.php";

	private SwipeRefreshLayout refresh;
	private LinearLayout nointer;
	private LinearLayout rebtn;
	private TextView searchtext;
	private CardView card;
	private Context mComtext;
	private static home instance;
	private LinearLayout go_search;
	private int overallXScroll = 0;
	private ProgressBar progress;
	
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		getActivity().setTheme(R.style.AppTheme);
		final View view = inflater.inflate(R.layout.home, container, false);
	
		
		
		searchtext=view.findViewById(R.id.searchtext);
		instance = this;
		tab = (TabLayout) view.findViewById(R.id.tab_layout); 

		pager = (ViewPager) view.findViewById(R.id.view_pager); 
		setUpViewPager(pager);
        tab.setupWithViewPager(pager);
		tab.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
				@Override
				public void onTabSelected(final TabLayout.Tab tab) {
					int position = tab.getPosition();

					SharedPreferences scrollto =getActivity().getSharedPreferences("tab", 0);
					SharedPreferences.Editor editorto = scrollto.edit();
					editorto.putInt("tabposition",position);
					editorto.commit();
					SharedPreferences settings =getActivity().getSharedPreferences("tab", 0);
					final int tabposition = settings.getInt("tabposition", 0); //0 is the default value
					new Handler().postDelayed(new Runnable() {
							public void run() {
								if(tabposition==1){
									anime_fragment_fight.getInstance().loaddata();
									
								}else{
									if(tabposition==2){
										anime_fragment_drama.getInstance().loaddata();
										
									}else{
										if(tabposition==3){
											anime_fragment_funny.getInstance().loaddata();
											
										}else{
											if(tabposition==4){
												anime_fragment_love.getInstance().loaddata();
												
											}else{
												if(tabposition==5){
													anime_fragment_fantasy.getInstance().loaddata();
													
												}else{
													if(tabposition==6){
														anime_fragment_aventure.getInstance().loaddata();
														
													}else{
														if(tabposition==7){
															anime_fragment_school.getInstance().loaddata();
															
														}
													}
												}
											}
										}
									}
								}
							}
						}, 500);
					
					
				}

				@Override
				public void onTabUnselected(TabLayout.Tab tab) {

				}

				@Override
				public void onTabReselected(TabLayout.Tab tab) {

				}

			});


		
		
		go_search=view.findViewById(R.id.go_search);
		go_search.setOnClickListener(new OnClickListener(){
				@Override
				public void onClick(View p1) {
					Intent i = new Intent(getActivity(),search_activity.class);
					startActivity(i);
				}
				});
		
		
        
		
					
		
        return view;

	}
	
	public static home getInstance() {
        return instance;
    }

    public void changtext(String s) {
		Animation animation = AnimationUtils.loadAnimation(getActivity(), R.anim.slide_in_bottom);
		searchtext.startAnimation(animation);
		
		searchtext.setText(s);
    }
	public class SectionPagerAdapter extends FragmentPagerAdapter {

		private List<Fragment> fragmentList = new ArrayList<>();
		private List<String> titleList = new ArrayList<>();

		public SectionPagerAdapter(FragmentManager fm) {
			super(fm);
		}

		@Override
		public Fragment getItem(int position) {
			return fragmentList.get(position);
		}

		@Override
		public int getCount() {
			return fragmentList.size();
		}

		@Nullable
		@Override
		public CharSequence getPageTitle(int position) {
			return titleList.get(position);
		}

		public void addFragment(Fragment fragment, String title)    {
			fragmentList.add(fragment);
			titleList.add(title);
		}
	}



	private void setUpViewPager(ViewPager viewPager) {
        SectionPagerAdapter adapter = new SectionPagerAdapter(getChildFragmentManager());

        adapter.addFragment(new anime_fragment(), "แนะนำ");
        adapter.addFragment(new anime_fragment_fight(), "ต่อสู้");
        adapter.addFragment(new anime_fragment_drama(), "ดราม่า");
        adapter.addFragment(new anime_fragment_funny(), "ตลก");
		adapter.addFragment(new anime_fragment_love(), "โรแมนติก");
        adapter.addFragment(new anime_fragment_fantasy(), "แฟนตาซี");
        adapter.addFragment(new anime_fragment_aventure(), "ผจญภัย");
        adapter.addFragment(new anime_fragment_school(), "โรงเรียน");
		

        viewPager.setAdapter(adapter);
		viewPager.setOffscreenPageLimit(8);

    }
	
	
}

