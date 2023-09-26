package com.mstudio.android.doome.app.activity;

import android.support.v7.app.*;
import android.os.*;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.LinearLayout;
import android.view.View.OnClickListener;
import android.content.SharedPreferences;
import android.app.Activity;
import android.content.res.Configuration;
import com.mstudio.android.doome.app.R;
public class about extends AppCompatActivity 
{ 

    private LinearLayout back;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
		setTheme(R.style.AppTheme);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.about);
		back=findViewById(R.id.back);
		back.setOnClickListener(new OnClickListener(){

				@Override
				public void onClick(View p1) {
					finish();
				}
			});
	}

}
