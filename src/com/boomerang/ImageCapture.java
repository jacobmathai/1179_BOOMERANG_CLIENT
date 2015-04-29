package com.boomerang;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;

import boomerang.util.MyLocationListener;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
//import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.Button;

public class ImageCapture extends Activity {
    /** Called when the activity is first created. */
	
	static double iplat=0.0;
    static double iplon=0.0;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.imagecapture);
        Button ICbt=(Button)findViewById(R.id.icbt);
        ICbt.setOnClickListener(new View.OnClickListener() {
			
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				cameraCapture();
				
			}
		});
        
        
    }  
    private static final int CAMERA_PIC_REQUEST = 1337;
	public  void cameraCapture() {
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
	    	    File file = new File(extStorageDirectory+File.separator+TagKeyword.keyword+".png");//errr.png
	    	    try {
	    	    	outStream = new FileOutputStream(file);
	    	    	thumbnail.compress(Bitmap.CompressFormat.PNG, 100, outStream);
	    	    	outStream.flush();
	    	    	outStream.close();
	    	    	Search.click="Iupload";
	    	    	iplat=MyLocationListener.firstlat;
	    	    	iplon=MyLocationListener.firstlong;
	    	    	Intent it=new Intent(ImageCapture.this,SDcardExplorer.class);
	    	    	startActivity(it);
	    	    }
	    	    catch(Exception e)
	    	    {
	    	    	e.printStackTrace();
	    	    }
	    } 
	}
}
