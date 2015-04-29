package com.boomerang;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;


public class SendByLocation extends Thread {
	
	InputStream in = null;
	OutputStream out = null;
	DataInputStream din = null;
	PrintStream ps = null;
	static String fip, fn;

	@Override
	public void run() {
		// TODO Auto-generated method stub
		try {

			// System.out.println("server waiting");
			ServerSocket srSoc = new ServerSocket(4445);
			System.out.println("server started");
			while (true) {
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
				System.out.println("thread");
				
				in = csoc.getInputStream();
				
				System.out.println("in");
				
				out = csoc.getOutputStream();
				
				System.out.println("out");
				
				din = new DataInputStream(in);
				
				System.out.println("din");
				
				ps = new PrintStream(out);
				
				System.out.println("ps");
				System.out.println("working");
				
				
				while (true) {
					String str = din.readLine();
					System.out.println("Client Messages>>" + str);
					if (str == null)
						break;
					if (str.equals("exit"))
						break;
					if (str.equals("fetchreply")) {
						fn = din.readLine();
						System.out.println("File name "+fn);
						fip = din.readLine();
						System.out.println("Client ip "+fip);
						BoomClientServer.fip = fip;
						BoomClientServer.fn = fn;
						
						new BoomClient().sendFile();
					}
					
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

}
