package com.boomerang;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.net.Socket;
import java.net.UnknownHostException;

public class BoomClient {
	Socket soc;
	InputStream im;
	OutputStream out;
	PrintStream ps;
	int flag = 0;

	public BoomClient() throws Exception {
		System.out.println("**" + BoomClientServer.fip.trim() + "**");
		// soc=new Socket("localhost"/*"BoomClientServer.fip.trim()*/,4444);
		soc = new Socket(BoomClientServer.fip.trim(), 4444);
		im = soc.getInputStream();
		out = soc.getOutputStream();

		ps = new PrintStream(out);
		ps.println("cccc");
		System.out.println("socket created");
	}

	public void sendFile() {

		System.out.println("inside BC sendfile");
		try {
			File f = new File("/mnt/sdcard/News");
			File f1[] = f.listFiles();
			for (int i = 0; i < f1.length; i++) {
				if (!f1[i].isDirectory()) {
					String s = f1[i].getName();
					String fname = s.substring(0, s.indexOf("."));
					if (fname.equals(BoomClientServer.fn)) {
						FileInputStream fin = new FileInputStream(
								f1[i].getAbsolutePath());
						byte[] b = new byte[fin.available()];
						fin.read(b);
					//	System.out.println("ddddddd");
						ps.println("filesend");
						ps.println(f1[i].getName());
						ps.println(b.length + "");
						String cont = new String(b);
						ps.println(cont);
					//	 int ch = 0;
						 
				        //    byte[] b = new byte[10000];
//				            while ((ch = fin.read(b)) != -1) {
//				                out.write(b);
//				            }
//
//						
						//out.write(b);
						ps.println("exit");

					} else {
						flag = 1;
					}
				}
			}
			if (flag == 1) {
				File fl = new File("/mnt/sdcard");
				File f2[] = fl.listFiles();
				for (int i = 0; i < f2.length; i++) {
					if (!f2[i].isDirectory()) {
						String s = f2[i].getName();
						String fname = s.substring(0, s.indexOf("."));
						if (fname.equals(BoomClientServer.fn)) {
							FileInputStream fin = new FileInputStream(
									f2[i].getAbsolutePath());
							byte[] b = new byte[fin.available()];
							System.out.println(fin.available());
						//	fin.read(b);
							System.out.println("ddddddd");
							ps.println("imagesend");
							ps.println(f2[i].getName());
							ps.println(b.length+"");
							//out.write(b);
							int ch=0;
							int cnt=0;
							Thread.sleep(1000);
							while((ch=fin.read())!=-1)
							{
								out.write(ch);
								cnt++;
								if(cnt>b.length)
								{
									break;
								}
							}
							System.out.println("ffffff"+b.length);
							out.flush();
							ps.println("exit");
							System.out.println("exit");

						} else {
							flag = 2;
						}
					}
				}
			}

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

	}

}
