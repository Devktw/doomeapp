package com.mstudio.android.doome.app;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.support.design.widget.BottomNavigationView;
import android.view.MenuItem;
import android.support.annotation.NonNull;

import android.support.design.internal.BottomNavigationMenuView;
import java.lang.reflect.Field;
import android.support.design.internal.BottomNavigationItemView;
import android.content.SharedPreferences;
import android.app.Activity;
import android.content.res.Configuration;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.Fragment;
import android.view.View;
import android.os.Handler;
import com.mstudio.android.doome.app.fragment.home;

import com.mstudio.android.doome.app.fragment.profile;
import com.mstudio.android.doome.app.fragment.manga;
import android.widget.Toast;
import android.app.TaskStackBuilder;
import android.content.Intent;
import android.content.Context;
import android.preference.PreferenceManager;
import android.os.Build;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.support.v4.app.NotificationManagerCompat;
import android.widget.LinearLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.view.View.OnClickListener;

public class main extends AppCompatActivity {
    private static main instance;
	
	final Fragment fragment1 = new home();
	//final Fragment fragment2 = new manga();
	final Fragment fragment3 = new profile(); 
	final FragmentManager fm = getSupportFragmentManager();
	Fragment active = fragment1;
	private LinearLayout setfrag_anime;
	//private LinearLayout setfrag_manga;
	private LinearLayout setfrag_profile;
	private ImageView im_anime;
	//private ImageView im_manga;
	private ImageView im_profile;
	private TextView tv_anime;
	//private TextView tv_manga;
	private TextView tv_profile;
    @Override
	private String checkedItem;
	boolean istheme;
	private String stateexit="state";
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
		instance = this;
		setTheme(R.style.AppTheme);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
		SharedPreferences sp =getSharedPreferences("ismainexit", MODE_PRIVATE);
		SharedPreferences.Editor et = sp.edit();
		et.putBoolean("ismainexit", false);
		et.commit();
		SharedPreferences sh5 =getSharedPreferences("is5s", MODE_PRIVATE);
		SharedPreferences.Editor ett = sh5.edit();
		ett.putBoolean("is5s", false);
		ett.commit();
		
		
		setfrag_anime=findViewById(R.id.setfrag_anime);
		//setfrag_manga=findViewById(R.id.setfrag_manga);
		setfrag_profile=findViewById(R.id.setfrag_profile);
		im_anime=findViewById(R.id.im_anime);
		//im_manga=findViewById(R.id.im_manga);
		im_profile=findViewById(R.id.im_profile);
		tv_anime=findViewById(R.id.tv_anime);
		//tv_manga=findViewById(R.id.tv_manga);
		tv_profile=findViewById(R.id.tv_profile);
		
		
		
		fm.beginTransaction().add(R.id.content, fragment3, "3").hide(fragment3).commitAllowingStateLoss();
		//fm.beginTransaction().add(R.id.content, fragment2, "2").hide(fragment2).commitAllowingStateLoss();
		fm.beginTransaction().add(R.id.content,fragment1, "1").hide(fragment1).commitAllowingStateLoss();
		
		SharedPreferences prefss = getApplicationContext().
			getSharedPreferences(getPackageName(), Activity.MODE_PRIVATE);
		String ss = prefss.getString("bottom","");

		if(ss.contains("0")){
			setanime();
			fm.beginTransaction().hide(active).show(fragment1).commitAllowingStateLoss();
			active = fragment1;
		}else{
			//if(ss.contains("1")){
				//setmanga();
			//	fm.beginTransaction().hide(active).show(fragment2).commitAllowingStateLoss();
			//	active = fragment2;

			//}else{
				setanime();
				fm.beginTransaction().hide(active).show(fragment1).commitAllowingStateLoss();
				active = fragment1;
				
			//}


		}
		
		
		
		setfrag_anime.setOnClickListener(new OnClickListener(){

				@Override
				public void onClick(View p1) {
					setanime();
				}
			});
		//setfrag_manga.setOnClickListener(new OnClickListener(){

				//@Override
			//	public void onClick(View p1) {
				//	setmanga();
			//	}
			//});
		setfrag_profile.setOnClickListener(new OnClickListener(){

				@Override
				public void onClick(View p1) {
					setprofile();
				}
			});
	}




	public void setanime() {
		fm.beginTransaction().hide(active).show(fragment1).commitAllowingStateLoss();
		active = fragment1;
		String checkedItem = "0";

		SharedPreferences prefs = getApplicationContext().
			getSharedPreferences(getPackageName(), Activity.MODE_PRIVATE);
		SharedPreferences.Editor edit = prefs.edit();
		edit.putString("bottom", checkedItem);
		edit.apply();

		im_anime.setImageResource(R.drawable.ic_anime_tab);
		im_anime.setColorFilter(getResources().getColor(R.color.coloraccent));
		tv_anime.setTextColor(getResources().getColor(R.color.coloraccent));


		//im_manga.setImageResource(R.drawable.ic_manga_notab);
		//im_manga.setColorFilter(getResources().getColor(R.color.colornotab));
	//	tv_manga.setTextColor(getResources().getColor(R.color.colornotab));

		im_profile.setImageResource(R.drawable.ic_profile_notab);
		im_profile.setColorFilter(getResources().getColor(R.color.colornotab));
		tv_profile.setTextColor(getResources().getColor(R.color.colornotab));

    }
	//public void setmanga() {
		//fm.beginTransaction().hide(active).show(fragment2).commitAllowingStateLoss();
		//active = fragment2;
		//String checkedItem = "1";

		//SharedPreferences prefs = getApplicationContext().
		//	getSharedPreferences(getPackageName(), Activity.MODE_PRIVATE);
		//SharedPreferences.Editor edit = prefs.edit();
		//edit.putString("bottom", checkedItem);
		//edit.apply();

		//im_anime.setImageResource(R.drawable.ic_anime_notab);
		//im_anime.setColorFilter(getResources().getColor(R.color.colornotab));
		//tv_anime.setTextColor(getResources().getColor(R.color.colornotab));

		//im_manga.setImageResource(R.drawable.ic_manga_tab);
		//im_manga.setColorFilter(getResources().getColor(R.color.colorAccent));
		//tv_manga.setTextColor(getResources().getColor(R.color.colorAccent));

		//im_profile.setImageResource(R.drawable.ic_profile_notab);
		//im_profile.setColorFilter(getResources().getColor(R.color.colornotab));
		//tv_profile.setTextColor(getResources().getColor(R.color.colornotab));



 //   }
    public void setprofile() {
		fm.beginTransaction().hide(active).show(fragment3).commitAllowingStateLoss();
		active = fragment3;
		im_anime.setImageResource(R.drawable.ic_anime_notab);
		im_anime.setColorFilter(getResources().getColor(R.color.colornotab));
		tv_anime.setTextColor(getResources().getColor(R.color.colornotab));

		//im_manga.setImageResource(R.drawable.ic_manga_notab);
		//im_manga.setColorFilter(getResources().getColor(R.color.colornotab));
		//tv_manga.setTextColor(getResources().getColor(R.color.colornotab));

		im_profile.setImageResource(R.drawable.ic_profile_tab);
		im_profile.setColorFilter(getResources().getColor(R.color.coloraccent));
		tv_profile.setTextColor(getResources().getColor(R.color.coloraccent));



    }
    
	public static main getInstance() {
        return instance;
    }

    public void myMethod() {
		
		recreate();
		
    }
	public void setpro() {
		fm.beginTransaction().hide(active).show(fragment3).commitAllowingStateLoss();
		active = fragment3;
		

    }

	@Override
	protected void onDestroy() {
		super.onDestroy();
		SharedPreferences sp =getSharedPreferences("ismainexit", MODE_PRIVATE);
		SharedPreferences.Editor et = sp.edit();
		et.putBoolean("ismainexit", true);
		et.commit();
		new Handler().postDelayed(new Runnable() {
				public void run() {
					SharedPreferences sp =getSharedPreferences("is5s", MODE_PRIVATE);
					SharedPreferences.Editor et = sp.edit();
					et.putBoolean("is5s", true);
					et.commit();
				}
			}, 5000);
		
	}
	
	
	
	
}
