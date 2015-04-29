package boomerang.util;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.StringTokenizer;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

import boomerang.util.Paths;

public class FileManipulator {

	public static byte[] readFile(String filepath) {
		byte b[] = "No data".getBytes();
		try {
			File f = new File(filepath);
			if (f.exists()) {
				FileInputStream fin = new FileInputStream(f);
				b = new byte[fin.available()];
				int ch = fin.read(b);
				fin.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		System.out.println(b);
		return b;
	}

	public static void deleteFile(String path){
		try {
			File f=new File(path);
			f.delete();
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	public static void writeToFile(String path,byte[] data){
		try {
			File f=new File(path);
			FileOutputStream fout=new FileOutputStream(f);
			fout.write(data);
			fout.close();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	public static void writeToFile(String path,String data){
		try {
			File f=new File(path);
			FileOutputStream fout=new FileOutputStream(f);
			fout.write(data.getBytes());
			fout.close();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

	public static String authenticateUser(String uname, String pass) {
		String patt=null;
		//String msg="invalid";
		try{
//			File f=new File(paths.registerd_user_file);
//			FileInputStream fin=new FileInputStream(f);
//			byte[] b=new byte[fin.available()];
//			boolean flag=false;
//			fin.read(b);
//			String data=new String(b);
			String data=getData();
			System.out.println(">>"+data);
			boolean flag=false;
			StringTokenizer st=new StringTokenizer(data, ":");
			while(st.hasMoreElements()){
				String [] ar=st.nextElement().toString().split(",");
				if(uname.equals(ar[1]) && pass.equals(ar[2]) ){
					
					System.out.println(ar[1]);
					System.out.println(ar[2]);
					flag=true;
					if(uname.equals("admin")){
						patt="admin";
					}
					else{
						patt=ar[0];
					}
					
				}
			}
			if(!flag){
				patt="invalid";
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}
		return patt;
	}

	public static String UserDetails(String uname) {
		String patt=null;
		//String msg="invalid";
		try{
//			File f=new File(paths.registerd_user_file);
//			FileInputStream fin=new FileInputStream(f);
//			byte[] b=new byte[fin.available()];
//			boolean flag=false;
//			fin.read(b);
//			String data=new String(b);
			String data=getData();
			System.out.println(">>"+data);
			
			StringTokenizer st=new StringTokenizer(data, ":");
			while(st.hasMoreElements()){
				String [] ar=st.nextElement().toString().split(",");
				if(uname.equals(ar[1])){					
					
						patt=ar[0]+","+ar[1]+","+ar[2];
				}
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return patt;
	}
	
	public static boolean changePass(String name, String uname, String oldp, String newp) {
		boolean flag=false;
		try{
//			File f=new File(paths.registerd_user_file);
//			FileInputStream fin=new FileInputStream(f);
//			byte[] b=new byte[fin.available()];
//			fin.read(b);
//			String data=new String(b);
			File f=new File(Paths.registered_user_file);
			String data=getData();
			System.out.println("Content>>"+data);
			String a=name+","+uname+","+oldp;
			
			if(data.contains(name+","+uname+","+oldp)){				 
				String d=name+","+uname+","+newp;
				String newdata=data.replace(a, d);
				byte[] bt=newdata.getBytes();
				FileOutputStream fout=new FileOutputStream(f);
				fout.write(bt);
				flag=true;
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return flag;
	}

	public static ArrayList<String> loadUserNames() {
		ArrayList<String> list=new ArrayList<String>();
		try{
//			File f=new File(paths.registerd_user_file);
//			FileInputStream fin=new FileInputStream(f);
//			byte []b=new byte[fin.available()];
//			fin.read(b);
//			String data=new String(b);
			String data=getData();
			StringTokenizer st=new StringTokenizer(data, ":");
			while(st.hasMoreTokens()){
				String[] ar=st.nextToken().split(",");
				if(!ar[1].equals("admin")){
				list.add(ar[1]);
				}
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}
		return list;
	}
	public static String getData(){
		String data="";
		try{
			File f=new File(Paths.registered_user_file);
			FileInputStream fin=new FileInputStream(f);
			byte[] b=new byte[fin.available()];
			fin.read(b);
			data=new String(b);
		}catch(Exception e){
			e.printStackTrace();
		}
		return data;
	}
	
	public static void compress(String dir,String zipfile){
		try{
		OutputStream os = new FileOutputStream(zipfile);
		 ZipOutputStream zos = new ZipOutputStream(new BufferedOutputStream(os));
		 try {
			 File f=new File(dir);
			 File fl[]=f.listFiles();
		     for (int i = 0; i < fl.length; ++i) {
		         if(!fl[i].isFile())continue;
		    
		         String filename = fl[i].getName();
		    	 FileInputStream fin=new FileInputStream(fl[i]);
		         byte[] bytes = new byte[fin.available()];
		         int ch=fin.read(bytes);
		         fin.close();
		         
		         ZipEntry entry = new ZipEntry(filename);
		         zos.putNextEntry(entry);
		         zos.write(bytes);
		         zos.closeEntry();
		     }
		     
		 } finally {
		     zos.close();
		 }
		}catch(Exception e){
			e.printStackTrace();
		}

	}
	public static void decompress(String zipfile,String dir){
		try {
			File fd=new File(dir);
			fd.mkdirs();
			InputStream is = new FileInputStream(zipfile);
			 ZipInputStream zis = new ZipInputStream(new BufferedInputStream(is));
			 try {
			     ZipEntry ze;
			     while ((ze = zis.getNextEntry()) != null) {
			         ByteArrayOutputStream baos = new ByteArrayOutputStream();
			         byte[] buffer = new byte[1024];
			         int count;
			         while ((count = zis.read(buffer)) != -1) {
			             baos.write(buffer, 0, count);
			         }
			         String filename = ze.getName();
			         byte[] bytes = baos.toByteArray();
			         // do something with 'filename' and 'bytes'...
			         File f=new File(dir,filename);
			         FileOutputStream fout=new FileOutputStream(f);
			         fout.write(bytes);
			         fout.close();
			     }
			 } finally {
			     zis.close();
			 }
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	
	}
