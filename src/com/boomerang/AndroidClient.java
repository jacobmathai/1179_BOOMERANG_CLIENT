package com.boomerang;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintStream;
//import java.net.Socket;
import java.net.*;

import android.widget.Toast;

//import com.boomerang.*;
public class AndroidClient {

	static Socket soc = null;
	static InputStream in = null;
	static BufferedReader din = null;
	static OutputStream out = null;
	static PrintStream ps = null;

	public AndroidClient(String ip, int port) {
		try {
			System.out
					.println("and??????????????????????????????????????????????");
			soc = new Socket(ip, port);
			in = soc.getInputStream();
			din = new BufferedReader(new InputStreamReader(in));
			out = soc.getOutputStream();
			ps = new PrintStream(out);
			ps.println("android");
		} catch (Exception e) {
			System.out.println("Client Constructor Err >>" + e);
			e.printStackTrace();
		}
	}

	// public void sendFile(String ip,String path){
	// try{
	// System.out.println("ip "+ip+" and path "+path);
	//
	// ps.println("Keyword");
	// ps.println(ip);
	// ps.println(path);
	// ps.flush();
	//
	// }catch(Exception e){
	// e.printStackTrace();
	// }
	//
	// }

	public void sendFile(String ip, String path, String latlon) {
		try {
			System.out.println("ip " + ip + " and path " + path);

			ps.println("Keyword");
			ps.println(Login.uname);
			ps.println(ip);
			ps.println(path);
			ps.println(latlon);
			ps.flush();

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public String search(String ip, String sword) {
		String rep = "";
		try {
			System.out.println("ip " + ip + " and word " + sword);

			ps.println("search");
			ps.println(ip);
			ps.println(sword);
			rep = din.readLine();

			// String fname=din.readLine();
			// String fsz=din.readLine();
			// System.out.println("fname "+fname);
			// System.out.println("fsz "+fsz);
			// // pln("File Size==="+fsz);
			// int sz=Integer.parseInt(fsz);
			// int cnt=0;
			// int ch=0;
			// byte b[]=new byte[1024];
			// FileOutputStream fout=new
			// FileOutputStream(targetfile+File.separator+fname);
			// while((ch=in.read(b))!=-1){
			// cnt+=ch;
			// fout.write(b,0,ch);
			// if(cnt>=sz)break;
			// }
			// fout.close();
			// ps.println("exit");

		} catch (Exception e) {
			System.out.println("Fetch " + e);
		}
		return rep;
	}
	
	public String searchByLocation(String ip, String latlong) {
		String rep = "";
		try {
			ps.println("bylocation");
			ps.println(ip);
			ps.println(latlong);
			rep = din.readLine();
		} catch (Exception e) {
			// TODO: handle exception
		}
		return rep;
	}
	
}