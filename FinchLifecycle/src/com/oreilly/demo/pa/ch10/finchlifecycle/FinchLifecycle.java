package com.oreilly.demo.pa.ch10.finchlifecycle;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

public class FinchLifecycle extends Activity {

	// Make strings for logging
//	private final String TAG = this.getClass().getSimpleName();
	private final String TAG = "LifeCycle";
	private final String RESTORE = ", can restore state";

	// The string "fortytwo" is used as an example of state
	private final String state = "fortytwo";

	@Override
	public void onCreate(Bundle savedState) {
		super.onCreate(savedState);
		
		String answer = null;
		// savedState could be null
		if (null != savedState) {
			answer = savedState.getString("answer");
		}
		Log.i(TAG, "FinchLifecycle.onCreate"
				+ (null == savedState ? "" : (RESTORE + " " + answer)));
		
		setContentView(R.layout.main);
		Log.i(TAG, "FinchLifecycle.onCreate returned");
	}

	@Override
	protected void onRestart() {
		super.onRestart();
		// Notification that the activity will be started
		Log.i(TAG, "FinchLifecycle.onRestart");
	}

	@Override
	protected void onStart() {
		super.onStart();
		// Notification that the activity is starting
		Log.i(TAG, "FinchLifecycle.onStart");
	}

	@Override
	protected void onResume() {
		super.onResume();
		// Notification that the activity will interact with the user
		Log.i(TAG, "FinchLifecycle.onResume");
	}

	protected void onPause() {
		super.onPause();
		// Notification that the activity will stop interacting with the user
		Log.i(TAG, "FinchLifecycle.onPause" + (isFinishing() ? " Finishing" : ""));
	}

	@Override
	protected void onStop() {
		super.onStop();
		// Notification that the activity is no longer visible
		Log.i(TAG, "FinchLifecycle.onStop");
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		// Notification the activity will be destroyed
		Log.i(TAG,
				"FinchLifecycle.onDestroy "
						// Log which, if any, configuration changed
						+ Integer.toString(getChangingConfigurations(), 16));
	}
	
	// ////////////////////////////////////////////////////////////////////////////
	// Called during the lifecycle, when instance state should be saved/restored
	// ////////////////////////////////////////////////////////////////////////////

	@Override
	protected void onSaveInstanceState(Bundle outState) {
		// Save instance-specific state
		outState.putString("answer", state);
		super.onSaveInstanceState(outState);
		Log.i(TAG, "FinchLifecycle.onSaveInstanceState");

	}

	@Override
	public Object onRetainNonConfigurationInstance() {
		Log.i(TAG, "FinchLifecycle.onRetainNonConfigurationInstance");
		return new Integer(getTaskId());
	}

	@Override
	protected void onRestoreInstanceState(Bundle savedState) {
		super.onRestoreInstanceState(savedState);
		// Restore state; we know savedState is not null
		String answer = null != savedState ? savedState.getString("answer") : "";
		Object oldTaskObject = getLastNonConfigurationInstance();
		if (null != oldTaskObject) {
			int oldtask = ((Integer) oldTaskObject).intValue();
			int currentTask = getTaskId();
			// Task should not change across a configuration change
			assert oldtask == currentTask;
		}
		Log.i(TAG, "FinchLifecycle.onRestoreInstanceState"
				+ (null == savedState ? "" : RESTORE) + " " + answer);
	}

	// ////////////////////////////////////////////////////////////////////////////
	// These are the minor lifecycle methods, you probably won't need these
	// ////////////////////////////////////////////////////////////////////////////

	@Override
	protected void onPostCreate(Bundle savedState) {
		super.onPostCreate(savedState);
		String answer = null;
		// savedState could be null
		if (null != savedState) {
			answer = savedState.getString("answer");
		}
		Log.i(TAG, "FinchLifecycle.onPostCreate"
				+ (null == savedState ? "" : (RESTORE + " " + answer)));

	}

	@Override
	protected void onPostResume() {
		super.onPostResume();
		Log.i(TAG, "FinchLifecycle.onPostResume");
	}

	@Override
	protected void onUserLeaveHint() {
		super.onUserLeaveHint();
		Log.i(TAG, "FinchLifecycle.onUserLeaveHint");
	}

}