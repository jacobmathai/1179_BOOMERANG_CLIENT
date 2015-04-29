package com.boomerang;

import java.io.File;

import boomerang.util.MyLocationListener;
import boomerang.util.Paths;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.Environment;
import android.view.Menu;
import android.view.MenuItem;

public class Home extends Activity {
	/** Called when the activity is first created. */
	// static String apppath ="/boom/";
	LocationManager mlocManager = null;
	static String appinbox = Environment.getExternalStorageDirectory()
			+ "/Boomerang/inbox/";
	public boolean flag = false;

	@Override
	//
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.home);

		mlocManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
		LocationListener mlocListener = new MyLocationListener(this);
		mlocManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 2000,
				10, mlocListener);

		flag = true;

		new BoomClientServer(Paths.SERVER_IP, 1234).start();// ip of the phone
		new SendByLocation().start();

		try {
			File f1 = new File(appinbox);
			if (!f1.exists()) {
				boolean b = f1.mkdirs();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		// try
		// {
		// File f1= new File(Paths.apppath);
		// if(!f1.exists()){
		// boolean b= f1.mkdir();
		// }
		// }
		//
		// catch(Exception e)
		// {
		// e.printStackTrace();
		// }

	}

	public boolean onCreateOptionsMenu(Menu m) {
		m.clear();
		m.add(0, 0, 0, "Connect");

		m.add(0, 2, 0, "Logout");
		m.add(0, 3, 0, "Exit");
		return true;
	}

	public boolean onOptionsItemSelected(MenuItem i) {
		switch (i.getItemId()) {
		case 0:
			Intent a = new Intent(Home.this, Connection.class);
			startActivity(a);
			break;

		case 2:
			Intent c = new Intent(Home.this, Login.class);
			startActivity(c);
			finish();
			break;
		case 3:
			Intent d = new Intent(Intent.ACTION_MAIN);
			d.addCategory(Intent.CATEGORY_HOME);
			d.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
			startActivity(d);
			finish();
			break;
		default:
			break;
		}
		return true;
	}

}