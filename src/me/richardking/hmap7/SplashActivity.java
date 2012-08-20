/*
 * SplashActivity.java
 * Created by: Richard King (richard@richardking.me)
 * Created on: August 15th, 2012
 * Last edited: August 20th, 2012
 * Last edited by: Richard King (richard@richardking.me)
 */

package me.richardking.hmap7;

import me.richardking.hmap7.R;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import com.google.android.apps.analytics.GoogleAnalyticsTracker;

/*
 * Splash screen to run on first launch. Redirects to the main Activity
 * after the number of ms specified by the splashTime variable (int)
 */

public class SplashActivity extends Activity
{
	
	protected boolean _active = true; // Whether or not we're at 1st load, which we will be initially. Also, tautologies are tautologous...
	protected final int SPLASH_TIME = 1000; // The length of time, in milliseconds, to display the splash screen.
	private final String ANALYTICS_ACCOUNT_ID = "UA-34204718-1";
	private GoogleAnalyticsTracker _tracker;
	
    /** Called when the activity is first created. */
    @Override public void onCreate(Bundle savedInstanceState)
    {
    	
    	// Going full screen!
    	this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN); // This is for the bar with the clock and battery status.
    	this.requestWindowFeature(Window.FEATURE_NO_TITLE); // This is for the bar with the title in it.
    	
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash); // Set the layout to the splash one.
        
        _tracker = GoogleAnalyticsTracker.getInstance();
        _tracker.startNewSession(ANALYTICS_ACCOUNT_ID, 10, this);
        _tracker.trackPageView("/SplashActivity");
        
        
        /*
         *  Create a thread to run for the duration of _spashTime, then set the _active
         *  parameter to false and finally, after killing the thread, start the MainActivity
         */
        
        Thread splashTread = new Thread()
        {
            @Override public void run() // Overriding the default function of the Thread (which is called automatically by the Thread's start() function
            {
                try // needed for the sleep(Integer) function.
                {
                	int waited = 0; // We haven't waited at all up to this point
                    while(_active && (waited < SPLASH_TIME)) // keep going so long as we haven't waited _splashTime ms or more...
                    {
                    	sleep(100); // Pause for 100ms
                        waited += 100; // We have waited (another)? 100ms
                        _active = (waited < SPLASH_TIME); // Finished our first load stuff
                    }
                }
                catch(InterruptedException e)
                {
                    // do nothing in the event that the sleeping in interrupted (not gonna happen *GULP*)...
                }
                finally
                {
                    finish(); // Close up the thread
                    Intent i = new Intent("me.richardking.hmap7.MainActivity"); // Create the new intent
                    startActivity(i); // Start a new activity from the intent
                }
            }
        };
        
        splashTread.start(); // Go for the looping thread!
        
    }
    
}