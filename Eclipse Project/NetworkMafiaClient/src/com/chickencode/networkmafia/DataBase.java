package com.chickencode.networkmafia;

import java.awt.Image;
import java.awt.Toolkit;
import java.util.HashMap;

public class DataBase 
{
	private HashMap<String , Image> imgData;
	private static boolean haveInstance = false;
	private static DataBase instance;
	private  DataBase()
	{
		imgData = new HashMap<String , Image>();
		init();
	}
	public static DataBase getDataBase()
	{
		if(!haveInstance)
		{
			haveInstance = false;
			instance = new DataBase();
		}
		return instance;
	}
	void init()
	{
		addImage("img_logo","img//logo.png"); 
		addImage("img_mafia","img//mafia.png");
		addImage("img_key","img//key.png");
		addImage("img_lock","img//lock.png");
		addImage("img_chat","img//chat.png");
	}
	void addImage(String name , String src)
	{
		Image img = Toolkit.getDefaultToolkit().getImage(src);
		imgData.put(name, img);
	}
	Image getImage(String name)
	{
		return imgData.get(name);
	}
}
