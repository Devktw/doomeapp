package com.mstudio.android.doome.app.adapter;

import android.content.SharedPreferences;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.mstudio.android.doome.app.R;
import android.content.Context;
import android.app.Activity;
import com.mstudio.android.doome.app.LinearLayoutManagerWithSmoothScroller;
import android.widget.ImageView;
import com.xeoh.android.texthighlighter.TextHighlighter;
import java.util.Locale;
import java.util.List;
import com.mstudio.android.doome.app.model.JSONData;
import java.util.ArrayList;

public class ViewHolderHot extends RecyclerView.ViewHolder {


    protected ImageView imageView_hot;
    protected TextView anime_name;
    protected TextView year;
    protected TextView formation;
    protected TextView valus;
    protected TextView seson;
    protected TextView subuse;
    private Context mContext;
    private static ViewHolderHot instance;
    private List<JSONData> feedItemList;
    
	private List<JSONData> array=null;
    public ViewHolderHot(View item,List<JSONData> feedItemList ) {
        super(item);
        instance=this;
		mContext=itemView.getContext();
        
        this.feedItemList = feedItemList;
        this.array = new ArrayList<JSONData>();
		this.array.addAll(feedItemList);
        
        
        this.imageView_hot = (ImageView) item.findViewById(R.id.image_hot);
        this.anime_name = (TextView) item.findViewById(R.id.name_anime);
        this.year = (TextView) item.findViewById(R.id.year);
        this.formation = (TextView) item.findViewById(R.id.formation);
        this.valus = (TextView) item.findViewById(R.id.valus);
        this.seson = (TextView) item.findViewById(R.id.seson);
        this.subuse = (TextView) item.findViewById(R.id.subuse);

    }
    public static ViewHolderHot getInstance() {
        return instance;
    }
    public void filter(String charText) {
        charText = charText.toLowerCase(Locale.getDefault());
        new TextHighlighter() 
            .setBackgroundColor(mContext.getResources().getColor(R.color.coloraccent)) /**Highlighted Text Background Color**/ 
            .setForegroundColor(mContext.getResources().getColor(R.color.hintc)) /**Highlighted Text Color**/ 
            .addTarget(anime_name) /**Target View Where You want to Highlight, In our case its TextView**/ 
            .highlight(charText, TextHighlighter.CASE_INSENSITIVE_MATCHER); 

        feedItemList.clear();
        if (charText.length() == 0) {
            feedItemList.addAll(array);
        }
        else
        {
            for (JSONData wp : array) {
                if (wp.getName_anime().toLowerCase(Locale.getDefault()).contains(charText)) {
                    feedItemList.add(wp);
                }
                if (wp.getName_manga().toLowerCase(Locale.getDefault()).contains(charText)) {
                    feedItemList.add(wp);
                }
            }
        }
        adapter_animehot.getInstance().update();
	}
    
}
