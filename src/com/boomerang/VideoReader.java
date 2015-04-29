package com.boomerang;

import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.net.Uri;
import android.os.Bundle;
import android.view.Window;
import android.widget.MediaController;
import android.widget.VideoView;

public class VideoReader extends Activity{

	VideoView vw;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
		setContentView(R.layout.videoplayer);
		 vw = (VideoView)findViewById(R.id.videoView1);
		 
		  MediaController mc = new MediaController(this);
	      vw.setMediaController(mc);
	        
	        //Set the path of Video or URI
	      System.out.println("pathhhhhhhhhhhhhhhh>>>>>>>"+SDcardExplorer.st);
	      vw.setVideoURI(Uri.parse(SDcardExplorer.st));
	        
	      //Set the focus
	      vw.requestFocus();
	      vw.start();
	}

}
