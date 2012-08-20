/*
 * ResultActivity.java
 * Created by: Richard King (richard@richardking.me)
 * Created on: August 16th, 2012
 * Last edited: August 20th, 2012
 * Last edited by: Richard King (richard@richardking.me)
 */

package me.richardking.hmap7;

import android.app.Activity;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.Window;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.google.ads.*;
import com.google.android.apps.analytics.GoogleAnalyticsTracker;

public class ResultActivity extends Activity {

	private TextView _tv1;
	private TextView _tv2;
	private TextView _tv3;
	private TextView _tv4;
	private TextView _header;
	private AdView _ad;
	private final String PUBLISHER_ID = "a150316b6775e11";
	private final String ANALYTICS_ACCOUNT_ID = "UA-34204718-1";
	private GoogleAnalyticsTracker _tracker;
	
	/** Called when the activity is first created. */
    @Override public void onCreate(Bundle savedInstanceState)
    {
    	// Scrap the title bar.
    	this.requestWindowFeature(Window.FEATURE_NO_TITLE);
    	
    	super.onCreate(savedInstanceState);
        setContentView(R.layout.result);
        
        _tracker = GoogleAnalyticsTracker.getInstance();
        _tracker.startNewSession(ANALYTICS_ACCOUNT_ID, 10, this);
        _tracker.trackPageView("/ResultActivity");
        
        _ad = new AdView(this, AdSize.BANNER, PUBLISHER_ID);
        
        LinearLayout layout = (LinearLayout) findViewById(R.id.ad_bar2);
        layout.addView(_ad);
        _ad.loadAd(new AdRequest());
        
        _tv1 = (TextView) findViewById(R.id.lower_limit);
        _tv2 = (TextView) findViewById(R.id.lower_val);
        _tv3 = (TextView) findViewById(R.id.upper_limit);
        _tv4 = (TextView) findViewById(R.id.upper_val);
        
        // Reclaim the variables passed to this Activity
        Bundle extras = getIntent().getExtras();
        
        String minimum = Integer.toString(extras.getInt("minimum"));
        String maximum = Integer.toString(extras.getInt("maximum"));
        
        // Set the values to their correct places
        _tv2.setText(minimum);
        _tv4.setText(maximum);
        
        _header = (TextView) findViewById(R.id.maintitle2);
        
        // Bring in the external font & set fonts/colours sizes.
        Typeface unicorn = Typeface.createFromAsset(getAssets(), "fonts/UNICORN.TTF");
        _header.setTypeface(unicorn);
        _header.setTextColor(Color.WHITE);
        _header.setTextSize(30.0f);
        
        _tv1.setTypeface(unicorn);
        _tv1.setTextSize(25.0f);
        _tv1.setTextColor(Color.parseColor("#8C006C"));
        
        _tv2.setTypeface(unicorn);
        _tv2.setTextSize(100.0f);
        _tv2.setTextColor(Color.parseColor("#8C006C"));
        
        _tv3.setTypeface(unicorn);
        _tv3.setTextSize(25.0f);
        _tv3.setTextColor(Color.parseColor("#8C006C"));
        
        _tv4.setTypeface(unicorn);
        _tv4.setTextSize(100.0f);
        _tv4.setTextColor(Color.parseColor("#8C006C"));
    }

}
