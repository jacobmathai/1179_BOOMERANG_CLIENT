package com.boomerang;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import boomerang.util.MyLocationListener;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class News extends Activity {
    static String news;
    static double nplat=0.0;
    static double nplon=0.0;
 
    
	/** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.news);
        final EditText et1=(EditText)findViewById(R.id.editText1);
        Button bt1=(Button)findViewById(R.id.button1);
        bt1.setOnClickListener(new View.OnClickListener() {  
			
			public void onClick(View v) {
				news = et1.getText().toString();
				nplat = MyLocationListener.firstlat;
				nplon=MyLocationListener.firstlong;
				String path1 = Environment.getExternalStorageDirectory()+"/News/";
				File f1= new File(path1);
				if(!f1.exists()) {
					
					
						f1.mkdirs();
						
				}
				String path = path1 + TagKeyword.keyword + ".txt";
				File f = new File(path);
				if(!f.exists()) {
					try {
						f.createNewFile();
						FileOutputStream fout=new FileOutputStream(f);
						byte[] bt = news.getBytes();
						fout.write(bt);
					    fout.close();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				else {
					Toast.makeText(getApplicationContext(), "File already exist", Toast.LENGTH_SHORT).show();
				}
				Search.click="Nupload";
				Intent i=new Intent(News.this, SDcardExplorer.class);
				startActivity(i);
	
	
			}
        });
    }
}
