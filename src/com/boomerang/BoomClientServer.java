package com.boomerang;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;

public class BoomClientServer extends Thread {
	Socket soc = null;
	InputStream in = null;
	OutputStream out = null;
	DataInputStream din = null;
	PrintStream ps = null;
	static String fip, fn;
	public BoomClientServer(String ip, int port) {
		try {
			System.out.println("cons?????????????????????????????????????");
			soc = new Socket(ip, port);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void run() {
		try {
			System.out.println("thread");
			in = soc.getInputStream();
			System.out.println("in");
			out = soc.getOutputStream();
			System.out.println("out");
			din = new DataInputStream(in);
			System.out.println("din");
			ps = new PrintStream(out);
			System.out.println("ps");
			System.out.println("working");
			ps.println("client");
			String msg = "";
			while (true) {
				System.out.println("inside And client");
				msg = din.readLine();
				System.out.println("Andclient::::::::::::::::::" + msg);
				if (msg == null)
					break;
				if (msg.equals("exit"))
					break;
				
				if (msg.equals("fetchreply")) {
					fn = din.readLine();
					fip = din.readLine();
					System.out.println("filename>>" + fn);
					System.out.println("fetchip>>" + fip);
					// soc.close();
					new BoomClient().sendFile();
				}
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
}
