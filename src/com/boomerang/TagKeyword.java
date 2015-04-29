package com.boomerang;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;

import boomerang.util.MyLocationListener;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class TagKeyword extends Activity {
	static String keyword;
	static double iplat = 0.0;
	static double iplon = 0.0;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.tagkeyword);
		final EditText et1 = (EditText) findViewById(R.id.editText1);
		Button bt1 = (Button) findViewById(R.id.button1);
		Button cancelbut = (Button) findViewById(R.id.button2);
		bt1.setOnClickListener(new View.OnClickListener() {

			public void onClick(View v) {
				keyword = et1.getText().toString();
				if (Connection.ch == 1) {

					if (keyword.equals("")) {
						Toast.makeText(getApplicationContext(),
								"Enter keyword", Toast.LENGTH_SHORT).show();

					} else {
//						cameraCapture();
						Intent in = new Intent(TagKeyword.this,CameraCapture.class);
						startActivity(in);
						finish();
					}

				} else if (Connection.ch == 2) {

					if (keyword.equals("")) {
						Toast.makeText(getApplicationContext(),
								"Enter keyword", Toast.LENGTH_SHORT).show();

					} else {
						Intent i = new Intent(TagKeyword.this, News.class);
						startActivity(i);
						finish();
					}

				} else if (Connection.ch == 3) {
					if (keyword.equals("")) {
						Toast.makeText(getApplicationContext(),
								"Enter keyword", Toast.LENGTH_SHORT).show();

					} else {
						Intent f = new Intent(TagKeyword.this,
								VideoRecorder.class);
						startActivity(f);
						finish();
					}

				}

			}
		});
		cancelbut.setOnClickListener(new View.OnClickListener() {

			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				et1.setText("");
			}
		});
	}

	private static final int CAMERA_PIC_REQUEST = 1337;

	public void cameraCapture() {
		// TODO Auto-generated method stub
		Intent cameraIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);

		startActivityForResult(cameraIntent, CAMERA_PIC_REQUEST);
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		if (requestCode == CAMERA_PIC_REQUEST) {
			Bitmap thumbnail = (Bitmap) data.getExtras().get("data");
			String extStorageDirectory = Environment.getExternalStorageDirectory().toString();
			OutputStream outStream = null;
			File file = new File(extStorageDirectory + File.separator + keyword
					+ ".png");// errr.png
			try {
				outStream = new FileOutputStream(file);
				thumbnail.compress(Bitmap.CompressFormat.PNG, 100, outStream);
				outStream.flush();
				outStream.close();
				Search.click = "Iupload";
				iplat = MyLocationListener.firstlat;
				iplon = MyLocationListener.firstlong;
				Intent it = new Intent(TagKeyword.this, SDcardExplorer.class);
				startActivity(it);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}
