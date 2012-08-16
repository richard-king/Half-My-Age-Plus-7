package me.richardking.hmap7;

import android.app.Activity;
import android.os.Bundle;
import android.view.Window;
import android.widget.TextView;

public class ResultActivity extends Activity {

	private TextView _tv;
	
	/** Called when the activity is first created. */
    @Override public void onCreate(Bundle savedInstanceState)
    {
    	this.requestWindowFeature(Window.FEATURE_NO_TITLE);
    	
    	super.onCreate(savedInstanceState);
        setContentView(R.layout.result);
        
        Bundle extras = getIntent().getExtras();
        
        _tv = (TextView) findViewById(R.id.textView1);
        
        _tv.setText(_tv.getText() + Integer.toString(extras.getInt("minimum")) + "-" + Integer.toString(extras.getInt("maximum")));
    }

}
