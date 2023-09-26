package com.mstudio.android.doome.app.activity;

import android.support.v7.app.*;
import android.os.*;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.LinearLayout;
import android.view.View.OnClickListener;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.Toast;
import android.content.SharedPreferences;
import android.app.Activity;
import android.content.res.Configuration;
import android.app.TaskStackBuilder;
import android.content.Intent;
import com.mstudio.android.doome.app.R;
import com.mstudio.android.doome.app.main;
import com.mstudio.android.doome.app.fragment.profile;
import android.Manifest;
import android.preference.PreferenceManager;
import android.content.Context;
public class theme extends AppCompatActivity 
{ 
    private RadioButton white;
	private RadioButton black;
	private RadioButton auto;
    private LinearLayout back;
	private RadioGroup themegroup;
	private LinearLayout tabauto;
	private LinearLayout tabwhite;
	private LinearLayout tabblack;
	SharedPreferences sharedpreferences;
	SharedPreferences.Editor editor;
	String checkedItem =null;

	private String stateexit ="1" ;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
		
		setTheme(R.style.AppTheme);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.theme);
	
		auto=findViewById(R.id.auto);
		white=findViewById(R.id.white);
		black=findViewById(R.id.black);
		tabauto=findViewById(R.id.tabauto);
		tabwhite=findViewById(R.id.tabwhite);
		tabblack=findViewById(R.id.tabblack);
		SharedPreferences prefss = getApplicationContext().
			getSharedPreferences(getPackageName(), Activity.MODE_PRIVATE);
		String ss = prefss.getString("item","");
		if(!ss.equals("")) {
			if(ss.contains("0")){
				auto.setChecked(true);
			}else{
				if(ss.contains("1")){
					white.setChecked(true);
				}else{
					black.setChecked(true);
				}
			}
		}else{
			white.setChecked(true);
		}
		
		tabauto.setOnClickListener(new OnClickListener(){

				
				@Override
				public void onClick(View p1) {
					checkedItem = "0";
					
					SharedPreferences prefs = getApplicationContext().
						getSharedPreferences(getPackageName(), Activity.MODE_PRIVATE);
					SharedPreferences.Editor edit = prefs.edit();
					edit.putString("item", checkedItem);
					edit.apply();
					auto.setChecked(true);
					white.setChecked(false);
					black.setChecked(false);
					startActivity(getIntent());
					finish();
					overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
					new Handler().postDelayed(new Runnable() {
							public void run() {
								main.getInstance().myMethod();
							}
						}, 500);
					
					
				}
		});
		tabwhite.setOnClickListener(new OnClickListener(){

				@Override
				public void onClick(View p1) {
					checkedItem = "1";
					
					
					SharedPreferences prefs = getApplicationContext().
						getSharedPreferences(getPackageName(), Activity.MODE_PRIVATE);
					SharedPreferences.Editor edit = prefs.edit();
					edit.putString("item", checkedItem);
					edit.apply();
					white.setChecked(true);
					black.setChecked(false);
					auto.setChecked(false);
					startActivity(getIntent());
					finish();
					overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
					
					new Handler().postDelayed(new Runnable() {
							public void run() {
								main.getInstance().myMethod();
							}
						}, 500);
					
				}
			});
		tabblack.setOnClickListener(new OnClickListener(){

				@Override
				public void onClick(View p1) {
					checkedItem = "2";
					
					
					SharedPreferences prefs = getApplicationContext().
						getSharedPreferences(getPackageName(), Activity.MODE_PRIVATE);
					SharedPreferences.Editor edit = prefs.edit();
					edit.putString("item", checkedItem);
					edit.apply();
					black.setChecked(true);
					white.setChecked(false);
					auto.setChecked(false);
					startActivity(getIntent());
					finish();
					overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
					
					new Handler().postDelayed(new Runnable() {
							public void run() {
								main.getInstance().myMethod();
							}
						}, 500);
					
					
				}
			});
		themegroup=findViewById(R.id.themeRadioGroup);
		
		
		back=findViewById(R.id.back);
		back.setOnClickListener(new OnClickListener(){

				@Override
				public void onClick(View p1) {
					finish();
				}
			});
	}

	@Override
	protected void onPause() {
		super.onPause();
		main.getInstance().setpro();
	}

	

	



	

	
}
