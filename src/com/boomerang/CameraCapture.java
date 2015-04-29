package com.boomerang;


import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import boomerang.util.MyLocationListener;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.graphics.PixelFormat;
import android.hardware.Camera;
import android.hardware.Camera.AutoFocusCallback;
import android.hardware.Camera.PictureCallback;
import android.hardware.Camera.ShutterCallback;
import android.os.Bundle;
import android.os.Environment;
import android.view.LayoutInflater;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.WindowManager;
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.Toast;
import android.widget.ToggleButton;

public class CameraCapture extends Activity implements SurfaceHolder.Callback {
	Camera camera;
	SurfaceView surfaceView;
	SurfaceHolder surfaceHolder;
	boolean previewing = false;
	LayoutInflater controlInflater = null;

	Button buttonTakePicture;
	ToggleButton tb;
	ProgressBar pb;
	
	static double iplat = 0.0;
	static double iplon = 0.0;

	final int RESULT_SAVEIMAGE = 0;

	public void flashon() {
		try {
			if (getPackageManager().hasSystemFeature(
					PackageManager.FEATURE_CAMERA_FLASH)) {

				tb.setTextSize(13.0f);
				tb.setText("Flash On");
				android.hardware.Camera.Parameters p = camera.getParameters();
				p.setFlashMode(android.hardware.Camera.Parameters.FLASH_MODE_TORCH);
				camera.setParameters(p);

			}
		} catch (Exception e) {
			e.printStackTrace();
			Toast.makeText(getBaseContext(), "Exception flashLightOn",
					Toast.LENGTH_SHORT).show();
		}
	}

	public void flashoff() {
		try {

			if (getPackageManager().hasSystemFeature(
					PackageManager.FEATURE_CAMERA_FLASH)) {

				tb.setTextSize(13.0f);
				tb.setText("Flash Off");
				android.hardware.Camera.Parameters p = camera.getParameters();
				p.setFlashMode(android.hardware.Camera.Parameters.FLASH_MODE_OFF);
				camera.setParameters(p);

			}

		} catch (Exception e) {
			e.printStackTrace();
			Toast.makeText(getBaseContext(), "Exception flashLightOff",
					Toast.LENGTH_SHORT).show();
		}
	}

	/** Called when the activity is first created. */
	@SuppressWarnings("static-access")
	
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.camera_layout);
		setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);

		// to make screen on
		getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);

		getWindow().setFormat(PixelFormat.UNKNOWN);
		surfaceView = (SurfaceView) findViewById(R.id.camerapreview);
		surfaceHolder = surfaceView.getHolder();
		surfaceHolder.addCallback(this);
		surfaceHolder.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);

		controlInflater = LayoutInflater.from(getBaseContext());
		View viewControl = controlInflater.inflate(R.layout.control, null);
		LayoutParams layoutParamsControl = new LayoutParams(
				LayoutParams.FILL_PARENT, LayoutParams.FILL_PARENT);
		this.addContentView(viewControl, layoutParamsControl);

		// Tagging progress bar

		pb = (ProgressBar) findViewById(R.id.progressBar1);
		pb.setVisibility(View.GONE);

		// Flash Button initialization

		tb = (ToggleButton) findViewById(R.id.toggleButton1);
		tb.setOnClickListener(new View.OnClickListener() {

			
			public void onClick(View v) {
				if (tb.isChecked()) {
					flashon();
				} else {
					flashoff();
				}
			}
		});

		buttonTakePicture = (Button) findViewById(R.id.takepicture);
		buttonTakePicture.setOnClickListener(new Button.OnClickListener() {

			
			public void onClick(View arg0) {
				// TODO Auto-generated method stub

				camera.takePicture(myShutterCallback, myPictureCallback_RAW,
						myPictureCallback_JPG);

			}
		});

		LinearLayout layoutBackground = (LinearLayout) findViewById(R.id.background);
		layoutBackground.setOnClickListener(new LinearLayout.OnClickListener() {

			
			public void onClick(View arg0) {
				// TODO Auto-generated method stub

				buttonTakePicture.setEnabled(true);
				camera.autoFocus(myAutoFocusCallback);

			}
		});

	}

	AutoFocusCallback myAutoFocusCallback = new AutoFocusCallback() {

		
		public void onAutoFocus(boolean arg0, Camera arg1) {
			// TODO Auto-generated method stub
			buttonTakePicture.setEnabled(true);
			pb.setVisibility(View.VISIBLE);
			camera.takePicture(myShutterCallback, myPictureCallback_RAW,
					myPictureCallback_JPG);

		}
	};

	ShutterCallback myShutterCallback = new ShutterCallback() {

		
		public void onShutter() {
			// TODO Auto-generated method stub

		}
	};

	PictureCallback myPictureCallback_RAW = new PictureCallback() {

		@SuppressWarnings("static-access")
		
		public void onPictureTaken(byte[] arg0, Camera arg1) {
			// TODO Auto-generated method stub
			pb.setVisibility(View.VISIBLE);
		}
	};

	PictureCallback myPictureCallback_JPG = new PictureCallback() {

		
		public void onPictureTaken(byte[] arg0, Camera arg1) {
			// TODO Auto-generated method stub

			// Uri uriTarget =
			// getContentResolver().insert(Media.EXTERNAL_CONTENT_URI, new
			// ContentValues());
			File fi = Environment.getExternalStorageDirectory();
			
			
			String filename = TagKeyword.keyword;
			
			File file = new File(fi+File.separator +filename+".png");
			flashoff();
			OutputStream imageFileOS;
			try {
				// imageFileOS =
				// getContentResolver().openOutputStream(uriTarget);
				imageFileOS = new FileOutputStream(file);
				imageFileOS.write(arg0);
				imageFileOS.flush();
				imageFileOS.close();

				Toast.makeText(CameraCapture.this,
						"Image saved: " + file.getAbsolutePath(),
						Toast.LENGTH_LONG).show();

	         	//new Client().sendCamPicture();
				Search.click = "Iupload";
				iplat = MyLocationListener.firstlat;
				iplon = MyLocationListener.firstlong;
				
				Intent in = new Intent(CameraCapture.this,SDcardExplorer.class);
				startActivity(in);

			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			camera.startPreview();
		}
	};

	
	public void surfaceChanged(SurfaceHolder holder, int format, int width,
			int height) {
		// TODO Auto-generated method stub
		if (previewing) {
			camera.stopPreview();
			previewing = false;
		}

		if (camera != null) {
			try {
				camera.setPreviewDisplay(surfaceHolder);
				camera.startPreview();
				previewing = true;
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	
	public void surfaceCreated(SurfaceHolder holder) {
		// TODO Auto-generated method stub
		camera = Camera.open();
	}

	
	public void surfaceDestroyed(SurfaceHolder holder) {
		// TODO Auto-generated method stub
		camera.stopPreview();
		camera.release();
		camera = null;
		previewing = false;
	}
}