package com.example.testapp;

import java.io.File;
import java.util.Arrays;

import android.os.Bundle;
import android.os.Environment;
import android.os.StatFs;
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
    
/*
	boolean isRemoveableVolume(Context context, String volumeName)
	{
		boolean isRemovable = false;
		StorageManager storageManager = (StorageManager)context.getSystemService(Context.STORAGE_SERVICE);
		StorageVolume[] storageVolumes = storageManager.getVolumeList();
		 
		for(int index=0; index<storageVolumes.length; index++){
			  StorageVolume volume = storageVolumes[index];
			  String path = volume.getPath();
			  if(!volume.allowMassStorage())
				  continue;
			  
			  if(!path.equalsIgnoreCase(volumeName))
				  continue;
			  
			  isRemovable = volume.isRemovable();
			  break;
		 }
			  
		return isRemovable;		
	}
	*/
    public void onPathTest(View view){
    	String external_storage_direcotry = Environment.getExternalStorageDirectory().getPath();
    	Log.w(TAG, "Environment.getExternalStorageDirectory: "+ external_storage_direcotry);
    	
    	String root_directory = Environment.getRootDirectory().getPath();
    	Log.w(TAG, "Environment.getRootDirectory: "+ root_directory);
    	
    	String data_dir = Environment.getDataDirectory().getPath();
    	Log.w(TAG, "Environment.getDataDirectory: "+ data_dir);
    	
    	String file_dir = getFilesDir().getPath();
    	Log.w(TAG, "Context.getFilesDir: "+ file_dir);
    	
    	File file = new File("/sdcard/11.txt");
    	Log.w(TAG, "file.getName: "+file.getName() + " file.getParent(): " + file.getParent());

    	String sdcard0_prefix = System.getenv("EXTERNAL_STORAGE");//"/storage/sdcard0";
    	Log.w(TAG, "System.getenv(EXTERNAL_STORAGE): "+sdcard0_prefix);
		String sdcard1_prefix = System.getenv("SECONDARY_STORAGE");//"/storage/sdcard1";
		Log.w(TAG, "System.getenv(SECONDARY_STORAGE): "+sdcard1_prefix);
		
		String text = "String in String";
		String testString = String.format("%d, %s, %s\n", 100, "hello", text);
		Log.w(TAG, "fomart %d, %s\n returns: "+ testString);
		
		long size = getDataStorageSizeInKb();
		Log.w(TAG, "data storage size is: "+size);
    }
    
    public long getDataStorageSizeInKb(){
		StatFs volStatFs = new StatFs(Environment.getDataDirectory().getPath());
		//we use kb because this value can be very large to be hold in bytes
		long available_size_in_KB = volStatFs.getAvailableBlocks() * (volStatFs.getBlockSize()/1024);
		return available_size_in_KB;
	}
}
