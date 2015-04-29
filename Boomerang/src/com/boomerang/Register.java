package com.boomerang;

import java.io.File;
import java.io.IOException;
//import java.io.FileNotFoundException;
import java.io.FileOutputStream;

import com.boom.db.DBConnection;

import boomerang.util.Paths;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class Register extends Activity {
    /** Called when the activity is first created. */
	  

     static String name=null;
	static String uname=null;
	static String pass=null;
	String co_pass;
	FileOutputStream fout=null;
	EditText et4;
	
   @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register);
        System.out.println("before");
        final EditText et1=(EditText)findViewById(R.id.editText1);
        final EditText et2=(EditText)findViewById(R.id.editText2);
        final EditText et3=(EditText)findViewById(R.id.editText3);
        et4=(EditText)findViewById(R.id.editText4);
        //System.out.println("after declare");
        Button bt1=(Button)findViewById(R.id.submit);
        Button bt2=(Button)findViewById(R.id.clearreg);
        bt1.setOnClickListener(new View.OnClickListener() {
			
			public void onClick(View v) {
				// TODO Auto-generated method stub
				System.out.println("inside btn");
				name=et1.getText().toString().trim();
				uname=et2.getText().toString().trim();
				pass=et3.getText().toString().trim();
				co_pass=et4.getText().toString().trim();
				if(name.equals("")||uname.equals("")||pass.equals("")||co_pass.equals("")){
					Toast.makeText(getApplicationContext(), "All Fields are mandatory", Toast.LENGTH_LONG).show();
					return;
				}
				else if(!pass.equals(co_pass)){
					Toast.makeText(getApplicationContext(), "Password mismatch", Toast.LENGTH_LONG).show();
					return;
				}
				
//				File f1=new File(Paths.apppath);
//			System.out.println(Paths.apppath);
//			
//				f1.mkdirs();
//				File f=new File(Paths.registered_user_file);
//				try {
//					f.createNewFile();
//				} catch (IOException e1) {
//					// TODO Auto-generated catch block
//					e1.printStackTrace();
//				}
//				String str=name+","+uname+","+pass+":";
//				
//				byte[] b=str.getBytes();
//				try {
//					fout=new FileOutputStream(f,true);
//					fout.write(b);
//				} catch (Exception e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}
				DBConnection db=new DBConnection();
				int stat=db.registration(name, uname, pass);
				if(stat==1){
					Toast.makeText(getApplicationContext(), "Registration Succesful", Toast.LENGTH_SHORT).show();
					Intent j=new Intent(Register.this,Login.class);
					startActivity(j);
				}
				else
					Toast.makeText(getApplicationContext(), "Registration Failed", Toast.LENGTH_SHORT).show();
					
			}
		});
        bt2.setOnClickListener(new View.OnClickListener() {
			
			public void onClick(View v) {
				// TODO Auto-generated method stub
				et1.setText("");
				et2.setText("");
				
			}
		});
        
    }
}