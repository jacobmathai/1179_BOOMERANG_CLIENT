package com.boomerang;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Hashtable;
public class BCServer extends Thread {
	static int c=0;
	ArrayList<Socket> clsoc = new ArrayList<Socket>();
	Hashtable<String, Socket> clientHT = new Hashtable<String, Socket>();
	Hashtable<Socket, Socket> jobHt = new Hashtable<Socket, Socket>();
	int jobId=0;
	File f;
	File f1;
	public void run() {
		try {
			
		
			//System.out.println("server waiting");
			ServerSocket srSoc = new ServerSocket(4444);
			System.out.println("server started");
			while(true) {
				Socket soc = srSoc.accept();
				System.out.println(soc);
				new Child(soc).start();
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	class Child extends Thread {
		Socket csoc = null;
		public Child(Socket s) {
			csoc = s;
		}
		public void run() {
			try {
				InputStream in = csoc.getInputStream();
				BufferedReader br = new BufferedReader(new InputStreamReader(in));
				String type = br.readLine();
				System.out.println("Type  "+type);
//				br.readLine();
				while(true) {
					String str=br.readLine();
					System.out.println("Client Messages>>"+str);
                    if(str==null)break;
					if(str.equals("exit"))break;
					if(str.equals("filesend"))
					{
						String fname=br.readLine();
						System.out.println("File name recieved "+fname );
//						System.out.println(fname);
						int fsz=Integer.parseInt(br.readLine());
						System.out.println(fsz);
						String content = br.readLine();
						int ch=0;
						int cnt=0;
						File f=new File(Home.appinbox+fname);
						FileOutputStream fout=new FileOutputStream(f);
						byte[] b= content.getBytes();
						fout.write(b);
//						if(cnt>fsz)
//							{
//								fout.close();
//								break;
//							}
						fout.close();
//						String sss= new String(b);
//						System.out.println("stringgggg>>>>>>>>>"+sss);
//						while((ch=in.read(b))!=-1)
//							
//						{
//							fout.write(b, 0, ch);
//							cnt++;
//							if(cnt>fsz)
//							{
//								fout.close();
//								break;
//							}
//						}
						
					}
					if(str.equals("imagesend"))
					{
						String fname=br.readLine();
						System.out.println(fname);
						int fsz=Integer.parseInt(br.readLine());
						System.out.println(fsz);
					
						int ch=0;
						int cnt=0;
						File f=new File(Home.appinbox+fname);
						FileOutputStream fout=new FileOutputStream(f);
					Thread.sleep(1000);
					while((ch=in.read())!=-1)
					{
						fout.write(ch);
					cnt++;
						
						if(cnt>=fsz)
						{
							fout.close();
							System.out.println("dddddsdsd"+cnt);
							break;
					
						}
					
						
					}
					System.out.println(cnt);
					}
					
}
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
		}
	}
}