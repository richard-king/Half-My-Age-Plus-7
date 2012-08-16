package me.richardking.hmap7;

import android.app.Activity;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.Window;
import android.widget.TextView;

public class ResultActivity extends Activity {

	private TextView _tv1;
	private TextView _tv2;
	private TextView _tv3;
	private TextView _tv4;
	private TextView _header;
	
	/** Called when the activity is first created. */
    @Override public void onCreate(Bundle savedInstanceState)
    {
    	this.requestWindowFeature(Window.FEATURE_NO_TITLE);
    	
    	super.onCreate(savedInstanceState);
        setContentView(R.layout.result);
        
        _tv1 = (TextView) findViewById(R.id.lower_limit);
        _tv2 = (TextView) findViewById(R.id.lower_val);
        _tv3 = (TextView) findViewById(R.id.upper_limit);
        _tv4 = (TextView) findViewById(R.id.upper_val);
        
        Bundle extras = getIntent().getExtras();
        
        String minimum = Integer.toString(extras.getInt("minimum"));
        String maximum = Integer.toString(extras.getInt("maximum"));
        
        _tv2.setText(minimum);
        _tv4.setText(maximum);
        
        _header = (TextView) findViewById(R.id.maintitle2);
        
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
