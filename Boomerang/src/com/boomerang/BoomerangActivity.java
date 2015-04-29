package com.boomerang;




import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;


public class BoomerangActivity extends Activity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        Button BAbt1=(Button)findViewById(R.id.mbt1);
       // Button BAbt2=(Button)findViewById(R.id.mbt2);
        BAbt1.setOnClickListener(new View.OnClickListener() {
			
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent ii=new Intent(BoomerangActivity.this, Login.class);
				startActivity(ii);
				
				
			}
		});
//        BAbt2.setOnClickListener(new View.OnClickListener() {
//			
//			public void onClick(View v) {
//				// TODO Auto-generated method stub
//				Intent ij=new Intent(BoomerangActivity.this, GLogin.class);
//				startActivity(ij);
//			}
//		});
//        
    }
}