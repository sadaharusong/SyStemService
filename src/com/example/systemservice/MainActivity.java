package com.example.systemservice;

import android.R.integer;
import android.app.Activity;
import android.app.ActivityManager;
import android.content.Context;
import android.media.AudioManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		LayoutInflater inflater =(LayoutInflater) MainActivity.this.getSystemService(LAYOUT_INFLATER_SERVICE);
		View view = inflater.inflate(R.layout.activity_main, null);
		setContentView(view);
	}

	public void doClick(View v)
	{
		switch (v.getId()) {
		case R.id.network:
			if (isNetWorkConnected(MainActivity.this) == true) {
				Toast.makeText(MainActivity.this, "网络已经打开", Toast.LENGTH_SHORT).show();
			}else {
				Toast.makeText(MainActivity.this, "网络没有打开", Toast.LENGTH_SHORT).show();
			}
			break;
		case R.id.enableOrDisable_WIFI:
			WifiManager wifiManager = (WifiManager) MainActivity.this.getSystemService(WIFI_SERVICE);
			if (wifiManager.isWifiEnabled()) {
				wifiManager.setWifiEnabled(false);
				Toast.makeText(MainActivity.this, "WIFI已经关闭", Toast.LENGTH_SHORT).show();
			}else {
				wifiManager.setWifiEnabled(true);
				Toast.makeText(MainActivity.this, "WIFI已经打开", Toast.LENGTH_SHORT).show();
			}
			break;
		case R.id.getvoice:
			AudioManager mAudioManager = (AudioManager) MainActivity.this.getSystemService(AUDIO_SERVICE);
			int max = mAudioManager.getStreamMaxVolume(AudioManager.STREAM_SYSTEM);
			int current = mAudioManager.getStreamVolume(AudioManager.STREAM_RING);
			Toast.makeText(MainActivity.this, "系统的最大音量为:"+max+"当前音量是:"+current, Toast.LENGTH_SHORT).show();
			break;
		
		case R.id.getPackagename:
			ActivityManager mActivityManager = (ActivityManager) MainActivity.this.getSystemService(ACTIVITY_SERVICE);
			String packagename= mActivityManager.getRunningTasks(1).get(0).topActivity.getPackageName();
			Toast.makeText(MainActivity.this, "当前运行的Activity的包名"+packagename, Toast.LENGTH_SHORT).show();
		}
	}
	
	public boolean isNetWorkConnected(Context context){
		if (context != null) {
			ConnectivityManager mConnectivityManager = (ConnectivityManager) context.getSystemService(CONNECTIVITY_SERVICE);
			NetworkInfo mNetworkInfo = mConnectivityManager.getActiveNetworkInfo();
			if (mNetworkInfo != null) {
				return mNetworkInfo.isAvailable();
			}
		}
		return false;
	}
}
