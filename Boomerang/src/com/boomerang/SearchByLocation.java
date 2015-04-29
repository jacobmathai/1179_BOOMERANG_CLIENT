package com.boomerang;

import boomerang.util.MyLocationListener;
import boomerang.util.Paths;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class SearchByLocation extends Activity {

	Button sbylocation;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.searchbylocation);
		sbylocation = (Button) findViewById(R.id.location_bt);

		sbylocation.setOnClickListener(new View.OnClickListener() {
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				double iplat = MyLocationListener.firstlat;
				double iplong = MyLocationListener.firstlong;
				String latlong = iplat + "," + iplong;
				String reply = new AndroidClient(Paths.SERVER_IP, 1234)
						.searchByLocation(Login.clip, latlong);
				if ("ok".equals(reply)) {
					try {
						for (int i = 0; i < 5; i++) {
							Thread.sleep(1000);
						}
					} catch (Exception e) {
						// TODO: handle exception
					}
					Toast.makeText(getApplicationContext(),
							"Successfully downloaded to sdcard/Boomerang/inbox", Toast.LENGTH_SHORT).show();
					Search.click = "SEARCH";
					Intent i = new Intent(SearchByLocation.this, SDcardExplorer.class);
					startActivity(i);
					finish();
				} else
					Toast.makeText(getApplicationContext(),	"Search unsuccessful", Toast.LENGTH_SHORT).show();

			}
		});
	}

}
