package com.example.testapp;

import java.util.Arrays;

import android.os.Bundle;
import android.app.Activity;
import android.util.Log;
import android.view.Menu;
import android.view.View;

public class MainActivity extends Activity {

	private static final String TAG = "Test";
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    
    
    public void onClick1(View view){
    	String args[] = {"11", "22"};
    	Log.w(TAG, "args.toString(): "+ args.toString());
    	Log.w(TAG, "Arrays.toString(args): " + Arrays.toString(args));
    }
}
