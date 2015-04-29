package com.boomerang;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

public class Connection extends Activity {
    /** Called when the activity is first created. */
//	static String apppath ="/boom/"; 
	static int ch;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.connection);
    }
    public boolean onCreateOptionsMenu(Menu m1) {
    	m1.clear();
    	m1.add(0, 0, 0, "Image");
    	m1.add(0, 1, 0, "News feed");
    	m1.add(0, 2, 0, "Video");
    	m1.add(0, 3, 0, "Logout");
    	m1.add(0, 4, 0, "Home");
    	m1.add(0, 5, 0, "Search");
    	m1.add(0, 6, 0, "Search By Location");
    	return true;
    }
    public boolean onOptionsItemSelected(MenuItem j) {
    	switch (j.getItemId()) {
		case 0:
			ch=1;
			Intent a=new Intent(Connection.this,TagKeyword.class);
			startActivity(a);
			finish();
			break;
		case 1:
			ch=2;
			Intent b=new Intent(Connection.this,TagKeyword.class);
			startActivity(b);
			finish();
			break;	
		case 2:
			ch=3;
			Intent c=new Intent(Connection.this,TagKeyword.class);
			startActivity(c);
			finish();
			break;
		case 3:
			Intent d=new Intent(Connection.this,Login.class);
			startActivity(d);
			finish();
			break;
		case 4:
			Intent e=new Intent(Connection.this,Home.class);
			startActivity(e);
			finish();
			break;
		case 5:
			Intent f=new Intent(Connection.this,Search.class);
			startActivity(f);
			finish();
			break;
			
		case 6:
			Intent h=new Intent(Connection.this,SearchByLocation.class);
			startActivity(h);
			finish();
			
		default:
			break;
		}
    	return true;
    }
}
