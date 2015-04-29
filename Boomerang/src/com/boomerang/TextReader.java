package com.boomerang;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

public class TextReader extends Activity{
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.textreader);
		TextView tv = (TextView)findViewById(R.id.tvreader);
		tv.setText(SDcardExplorer.text);
	}

}
