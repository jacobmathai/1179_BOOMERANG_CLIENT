package com.boomerang;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;

//import com.kites.macloud.AndroidClient;



import boomerang.util.Paths;

import com.boomerang.R;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ListActivity;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
//import android.widget.ArrayAdapter;
import android.widget.Toast;

public class SDcardExplorer extends ListActivity {
	static String st;
	static String text = "";
	AlertDialog alert = null;
	private List<String> item = null;
	private List<String> path = null;
	private String root = "/sdcard";
	String ipaddress = "";

	private TextView myPath;

	public void onCreate(Bundle icicle) {
		super.onCreate(icicle);
		setContentView(R.layout.explorer);
		myPath = (TextView) findViewById(R.id.path);
		SharedPreferences preferences = getApplicationContext().getSharedPreferences("ipaddress", MODE_PRIVATE);
		ipaddress = preferences.getString("ip", null);
		getDir(root);
		File f1 = new File("/sdcard/abcd.txt");
		try {
			f1.createNewFile();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	// public void onBackPressed() {
	// // TODO Auto-generated method stub
	// //super.onBackPressed();
	// }
	public boolean onCreateOptionsMenu(Menu m) {
		m.clear();
		// m.add(0, 0, 0, "Connect to Network");
		// m.add(0, 0, 0, "Upload");
		m.add(0, 1, 0, "Logout");
		m.add(0, 2, 0, "Back");
		return true;
	}

	public boolean onOptionsItemSelected(MenuItem i) {

		switch (i.getItemId()) {
		// case 0:
		// Intent it3=new Intent(Home.this,NetworkHome.class);
		// startActivity(it3);
		// break;
		case 1:

			Intent it2 = new Intent(SDcardExplorer.this, Login.class);
			startActivity(it2);
			break;
		case 2:

			Intent it3 = new Intent(SDcardExplorer.this, Connection.class);
			startActivity(it3);
			break;
		}
		return true;
	}

	private void getDir(String dirPath) {
		myPath.setText("Location :" + dirPath);
		item = new ArrayList<String>();
		path = new ArrayList<String>();

		File f = new File(dirPath);
		File[] files = f.listFiles();
		if (!dirPath.equals(root)) {
			item.add(root);
			path.add(root);
			item.add("../");
			path.add(f.getParent());

		}
		for (int index = 0; index < files.length; index++) {

			File file = files[index];
			path.add(file.getPath());
			if (file.isDirectory()) {
				item.add(file.getName() + "/");
			} else {
				item.add(file.getName());
			}

		}
		ArrayAdapter<String> fileList = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1, item);
		setListAdapter(fileList);
	}

	protected void onListItemClick(ListView l, View v, int position, long id) {
		File file = new File(path.get(position));

		if (file.isDirectory()) {
			if (file.canRead())
				getDir(path.get(position));

			else {
				new AlertDialog.Builder(this)
						.setTitle("Info")
						.setMessage("Folder cannot be read")
						.setNeutralButton("Close",
								new DialogInterface.OnClickListener() {
									public void onClick(DialogInterface dlg,
											int sumthin) {
									}
								}).show();
			}
		} else {

			st = file.getPath();

			if (Search.click.equals("SEARCH")) {
				try {
					int i = st.lastIndexOf(".");
					String in = st.substring(i);

					if (in.equals(".txt")) {

						FileInputStream fin = new FileInputStream(st);
						byte b[] = new byte[fin.available()];
						fin.read(b);
						text = new String(b);
						System.out.println("text>>>>>>>>>>>>>>>" + text);
						fin.close();
						Intent inte = new Intent(SDcardExplorer.this,
								TextReader.class);
						startActivity(inte);

					} else if (in.equals(".mp4")) {
						Intent inte = new Intent(SDcardExplorer.this,
								VideoReader.class);
						startActivity(inte);
					} else if (in.equals(".png")) {
						Intent inte = new Intent(SDcardExplorer.this,
								ImageReader.class);
						startActivity(inte);
					}

				} catch (Exception e) {
					// TODO: handle exception
					e.printStackTrace();
				}

			}

			else if (Search.click.equals("Nupload")) {
				String nlatlon = News.nplat + "," + News.nplon;
				// new AndroidClient("192.168.43.1",
				// 1234).sendFile(Login.clip,file.getAbsolutePath());//1234;127.0.0.1;192.168.1.47
				new AndroidClient(Paths.SERVER_IP, 1234).sendFile(ipaddress,
						file.getAbsolutePath(), nlatlon);
				Toast.makeText(getApplicationContext(),
						"Succeessfully Uploaded", Toast.LENGTH_SHORT).show();

			}

			else if (Search.click.equals("Iupload")) {
				String ilatlon = CameraCapture.iplat + "," + CameraCapture.iplon;
				// new AndroidClient("192.168.43.1",
				// 1234).sendFile(Login.clip,file.getAbsolutePath());//1234;127.0.0.1;192.168.1.47
				new AndroidClient(Paths.SERVER_IP, 1234).sendFile(ipaddress,
						file.getAbsolutePath(), ilatlon);
				Toast.makeText(getApplicationContext(),
						"Succeessfully Uploaded", Toast.LENGTH_SHORT).show();

			}

			else if (Search.click.equals("Vupload")) {
				String vlatlon = VideoRecorder.vplat + ","
						+ VideoRecorder.vplon;
				// new AndroidClient("192.168.43.1",
				// 1234).sendFile(Login.clip,file.getAbsolutePath());//1234;127.0.0.1;192.168.1.47
				new AndroidClient(Paths.SERVER_IP, 1234).sendFile(ipaddress,
						file.getAbsolutePath(), vlatlon);
				Toast.makeText(getApplicationContext(),
						"Succeessfully Uploaded", Toast.LENGTH_SHORT).show();

			}

		}
	}
}