/*
 * MainActivity.java
 * Created by: Richard King (richard@richardking.me)
 * Created on: August 16th, 2012
 * Last edited: August 20th, 2012
 * Last edited by: Richard King (richard@richardking.me)
 */

package me.richardking.hmap7;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.google.ads.*;
import com.google.android.apps.analytics.GoogleAnalyticsTracker;

public class MainActivity extends Activity
{

	private TextView _actionButton;
	private TextView _enter;
	private EditText _input;
	private TextView _header;
	private AdView _ad;
	private final String PUBLISHER_ID = "a150316b6775e11";
	private final String ANALYTICS_ACCOUNT_ID = "UA-34204718-1";
	private GoogleAnalyticsTracker _tracker;
	
	/** Called when the activity is first created. */
    @Override public void onCreate(Bundle savedInstanceState)
    {
    	
    	// We don't need no title bar. (Hey! Coder! Leave that status bar alone!) 
    	this.requestWindowFeature(Window.FEATURE_NO_TITLE);
    	
    	super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        _tracker = GoogleAnalyticsTracker.getInstance();
        _tracker.startNewSession(ANALYTICS_ACCOUNT_ID, 10, this);
        _tracker.trackPageView("/MainActivity");
        
        _ad = new AdView(this, AdSize.BANNER, PUBLISHER_ID);
        
        LinearLayout layout = (LinearLayout) findViewById(R.id.ad_bar);
        layout.addView(_ad);
        _ad.loadAd(new AdRequest());
        
        _actionButton = (TextView) findViewById(R.id.button1);
        _input = (EditText) findViewById(R.id.editText1);
        _header = (TextView) findViewById(R.id.maintitle);
        _enter = (TextView) findViewById(R.id.tv1);
        
        Typeface unicorn = Typeface.createFromAsset(getAssets(), "fonts/UNICORN.TTF");
        _header.setTypeface(unicorn);
        _header.setTextColor(Color.WHITE);
        _header.setTextSize(30.0f);
        
        _input.setText("18");
        _input.setTypeface(unicorn);
        _input.setTextSize(100.0f);
        _input.setTextColor(Color.parseColor("#8C006C"));
        
        _enter.setTypeface(unicorn);
        _enter.setTextSize(25.0f);
        _enter.setTextColor(Color.parseColor("#8C006C"));
        
        _actionButton.setTypeface(unicorn);
        _actionButton.setTextSize(35.0f);
        _actionButton.setTextColor(Color.WHITE);
        
        _actionButton.setOnClickListener(
        	new View.OnClickListener()
        	{
	            @SuppressWarnings("deprecation")
				public void onClick(View view)
	            {
	            	String s = _input.getText().toString(); // Grab the input.
	            	
	            	_tracker.trackEvent("Clicks", "Calculate", "MainActivity", s.equals("") ? -1 : Integer.parseInt(s));
	            	
	            	if(s.equals(""))
	            	{
	            		// Make sure they enter _SOME_ data.
	            		
	            		AlertDialog alertDialog = new AlertDialog.Builder(MainActivity.this).create();
	        			alertDialog.setMessage("Hold on a sec there, tiger! You're gonna need to enter your age first!");

	        			alertDialog.setButton("Got it!", new DialogInterface.OnClickListener()
	        			{
	        	           public void onClick(DialogInterface dialog, int which)
	        	           {
	        	              // nothing
	        	           }
	        	        });
	        			
	        			alertDialog.show();
	            	}
	            	else
	            	{
		            	int age = Integer.parseInt(s); // Parse input to an int (we know we're safe as they can only input 0123456789).
		            	
		            	if( age < 18 )
		            	{
		            		// Generate a warning if the user is under 18.
		            		AlertDialog alertDialog = new AlertDialog.Builder(MainActivity.this).create();
		        			alertDialog.setMessage("You're a bit young for this game, no?");

		        			alertDialog.setButton("I guess...", new DialogInterface.OnClickListener()
		        			{
		        	           public void onClick(DialogInterface dialog, int which)
		        	           {
		        	              // nothing
		        	           }
		        	        });
		        			
		        			alertDialog.show();
		            	}
		            	else
		            	{
		            		// Proof of concept more than anything else.
		            		Intent i = new Intent("me.richardking.hmap7.ResultActivity");
		            		i.putExtra("minimum", calculateLower(age));
		            		i.putExtra("maximum", calculateUpper(age));
		            		startActivity(i);
		            	}
	            	}
	            }
        	}
        );
    	
    }
    
    /** Calculate the lower limit based on an integer age */
    private int calculateLower(int myAge)
    {
    	if( myAge < 23 )
    		return 18; // We don't want THE LAW a-comin' after us...
    	
    	return (int) ( Math.ceil(myAge/2d) + 7 );
    }
    
    /** Calculate the upper limit based on an integer age */
    private int calculateUpper(int myAge)
    {
    	return 2 * ( myAge - 7 );
    }
    
    @Override public void onDestroy()
    {
    	if (_ad != null)
    	{
    		_ad.destroy();
    	}
    	super.onDestroy();
    }

	
}
