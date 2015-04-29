package com.boom.db;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class DBConnection {
	public static Context context;
	SQLiteDatabase sq;
	
	public DBConnection() {
		// TODO Auto-generated constructor stub
		sq=context.openOrCreateDatabase("boom", SQLiteDatabase.OPEN_READWRITE, null);
	}
	
	public static void init() {
		SQLiteDatabase sq = null;
		try {
			sq = context.openOrCreateDatabase("boom",
					SQLiteDatabase.OPEN_READWRITE, null);
			sq.execSQL("create table if not exists tb_user(name varchar(40),username varchar(40) PRIMARY KEY,password varchar(40))");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			sq.close();
		}
	}
	
	public int registration(String name,String username,String password) {
		int i=0;
		try {
			sq=context.openOrCreateDatabase("boom", SQLiteDatabase.OPEN_READWRITE, null);
			//System.out.println("insert into tb_login (name,username,password) values('"+name+"','"+username+"','"+password+"')");
			Cursor cu1=null;
			cu1=sq.rawQuery("select * from tb_user where username='"+username+"'", null);
			if(cu1.moveToNext()){
		     System.out.println(cu1.getString(0));
		     i=2;
		    }
			else{
				sq.execSQL("insert into tb_user (name,username,password) values('"+name+"','"+username+"','"+password+"')");
				i=1;
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		finally{
			sq.close();
		}
		return i;
	}
	
	public int login(String username, String password) {
		int stat = 0;
		Cursor cu = null;
		try {
			System.out.println("select * from tb_user where username='"+username+"' and password='"+password+"'");
			cu=sq.rawQuery("select * from tb_user where username='"+username+"' and password='"+password+"'",null);
			if(cu.moveToNext())
			{
				stat=1;
				System.out.println("Login ok.------->>>>>>>>");
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		finally{
			cu.close();
			sq.close();
		}
		return stat;
	}
	
	public static void display() {
		SQLiteDatabase sq1=context.openOrCreateDatabase("boom", SQLiteDatabase.OPEN_READWRITE, null);
		Cursor cu = null;
		try {
			
			cu=sq1.rawQuery("select * from tb_user",null);
			while(cu.moveToNext())
			{
				
				System.out.print(cu.getString(1));
				System.out.println(cu.getString(2));
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		finally{
			cu.close();
			sq1.close();
		}
	}

}
