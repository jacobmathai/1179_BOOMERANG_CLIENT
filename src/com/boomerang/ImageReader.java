package com.boomerang;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.widget.ImageView;

public class ImageReader extends Activity{
	String [] s = new String[2];
	ImageView im;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.imageview);
		 im = (ImageView)findViewById(R.id.imgvw1);
		
        s[0]=SDcardExplorer.st;
        set();
        
	}
	private void set() {
		// TODO Auto-generated method stub
		try {
			final Bitmap b = BitmapFactory.decodeFile(s[0]);
			
			im.setImageBitmap(b);
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

}
