package me.richardking.hmap7;

import android.app.Activity;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;

public class MainActivity extends Activity
{

	/** Called when the activity is first created. */
    @Override public void onCreate(Bundle savedInstanceState)
    {
    	
    	// We don't need no title bar. (Hey! Coder! Leave that status bar alone!) 
    	this.requestWindowFeature(Window.FEATURE_NO_TITLE);
    	
    	super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
    	
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
	
}
