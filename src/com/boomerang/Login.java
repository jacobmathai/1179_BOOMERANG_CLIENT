package com.boomerang;

import java.io.File;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Enumeration;
import java.util.Locale;

import com.boom.db.DBConnection;

import boomerang.util.Paths;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Login extends Activity {
    private static final String TAG = "IP ADDRESS ";
	/** Called when the activity is first created. */
	
	static String clip=null;
	public static String uname = "";
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
         
        WifiManager wifi = (WifiManager)getSystemService(Context.WIFI_SERVICE);
        
        if(wifi.isWifiEnabled()){    		
//        	getLocalIpAddress();
        	String iP = getIP();
        	clip = iP;
        	if(iP!=null || iP!=""){
        		SharedPreferences preferences = getApplicationContext().getSharedPreferences("ipaddress", MODE_PRIVATE);
        		Editor editor = preferences.edit();
        		editor.putString("ip", iP);
        		editor.commit();
        	}
        }
 
        else {
        	Toast.makeText(getApplicationContext(), "Error in network connection", Toast.LENGTH_SHORT); 
        }
 
 
        final EditText let1=(EditText)findViewById(R.id.etulog);
        final EditText let2=(EditText)findViewById(R.id.etplog);
        DBConnection.context=getApplicationContext();
        DBConnection.init();
        DBConnection.display();
        try
        {
        	File f1= new File(Paths.apppath);
        	if(!f1.exists()){
         boolean b= f1.mkdir();         
        	}
        	File f11= new File(Paths.appSettingsPath);
        	if(!f11.exists()){
         boolean b= f11.mkdir();         
        	}
        	
        }
        
        catch(Exception e)
        {
        	e.printStackTrace();
        }
        
        Button lbt1=(Button)findViewById(R.id.login);
        Button lbt2=(Button)findViewById(R.id.clear);
        Button lbt3=(Button)findViewById(R.id.newuser);
        lbt1.setOnClickListener(new View.OnClickListener() {
			
			public void onClick(View v) {
				// TODO Auto-generated method stub
				uname= let1.getText().toString().trim();
				String pass= let2.getText().toString().trim();
				if(uname.equals("")||pass.equals("")){
					Toast.makeText(getApplicationContext(), "Enter Username and password", Toast.LENGTH_LONG).show();
					return;
				}
				//String reply=FileManipulator.authenticateUser(uname,pass);
				DBConnection db=new DBConnection();
				int stat=db.login(uname, pass);
				System.out.println(stat);
				if(stat==0) {
						Toast.makeText(getApplicationContext(), "Login Failed", Toast.LENGTH_SHORT).show();
				}
				
				else{
					Intent i=new Intent(Login.this,Home.class);
					startActivity(i);
					new BCServer().start();
					Toast.makeText(getApplicationContext(), "Login Sucess", Toast.LENGTH_SHORT).show();
					finish();
				}
				
				
			}
		});
        lbt2.setOnClickListener(new View.OnClickListener() {
			
			public void onClick(View v) {
				// TODO Auto-generated method stub
				let1.setText("");
				let2.setText("");
				
			}
		});
        lbt3.setOnClickListener(new View.OnClickListener() {
			
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent i=new Intent(Login.this,Register.class);
				startActivity(i);
				finish();				
			}
		});    
        
        
    }
    
    public void onBackPressed() {
		// TODO Auto-generated method stub
		//super.onBackPressed();
	}
    
    public String getLocalIpAddress() {
	    try {
	        for (Enumeration<NetworkInterface> en = NetworkInterface.getNetworkInterfaces(); en.hasMoreElements();) {
	            NetworkInterface intf = en.nextElement();
	            for (Enumeration<InetAddress> enumIpAddr = intf.getInetAddresses(); enumIpAddr.hasMoreElements();) {
	                InetAddress inetAddress = enumIpAddr.nextElement();
	                if (!inetAddress.isLoopbackAddress()) {
	                  //  Log.d("IPs", inetAddress.getHostAddress() );
         		//Toast.makeText(getApplicationContext(), "IPS::"+inetAddress.getHostAddress(),Toast.LENGTH_SHORT).show();
                 clip = inetAddress.getHostAddress();
                 Toast.makeText(getApplicationContext(), clip, Toast.LENGTH_LONG).show();
	                }
	            }
	        }
	    } catch (SocketException ex) {
	       ex.printStackTrace();
	    }
	    return null;
	}
    
    
    /**
	 * Get the IP of current Wi-Fi connection
	 * 
	 * @return IP as string
	 */
	private String getIP() {
		try {
			WifiManager wifiManager = (WifiManager) getSystemService(WIFI_SERVICE);
			WifiInfo wifiInfo = wifiManager.getConnectionInfo();
			int ipAddress = wifiInfo.getIpAddress();
			return String.format(Locale.getDefault(), "%d.%d.%d.%d",
					(ipAddress & 0xff), (ipAddress >> 8 & 0xff),
					(ipAddress >> 16 & 0xff), (ipAddress >> 24 & 0xff));
		} catch (Exception ex) {
			Log.e(TAG, ex.getMessage());
			return null;
		}
	}
}