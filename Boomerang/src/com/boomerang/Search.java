package com.boomerang;

import boomerang.util.Paths;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Search extends Activity {
	static String Searchword;
	static String click = "";

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.search);

		final EditText set = (EditText) findViewById(R.id.set1);

		Button bt1 = (Button) findViewById(R.id.button1);
		bt1.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				Searchword = set.getText().toString().trim();
				System.out.println("Searchword>>" + Searchword);

				String msg = new AndroidClient(Paths.SERVER_IP, 1234).search(Login.clip, Searchword);
				if ("ok".equals(msg)) {
					try {
						for (int i = 0; i < 5; i++) {
							Thread.sleep(1000);
						}
					} catch (Exception e) {
						// TODO: handle exception
					}
					Toast.makeText(getApplicationContext(),"Successfully downloaded to sdcard/Boomerang/inbox", Toast.LENGTH_SHORT).show();
					click = "SEARCH";
					Intent i = new Intent(Search.this, SDcardExplorer.class);
					startActivity(i);
					finish();
				} else
					Toast.makeText(getApplicationContext(),
							"Search unsuccessful", Toast.LENGTH_SHORT).show();
				}
		});
	}
}
