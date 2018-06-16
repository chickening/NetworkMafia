package com.chickencode.networkmafia;

import java.awt.Image;
import java.awt.Toolkit;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.util.HashMap;

import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;

public class DataBase 
{
	private HashMap<String , Image> imgData;
	private static DataBase instance = null;
	private String keyStore = "";
	private String keyPass = "";
	private String id = "";
	public final int loginServerPort = 1115;
	public final int lobbyServerPort = 1116;
	public void setKeyStore(String keyStore)
	{
		this.keyStore = keyStore;
	}
	public void setKeyPass(String keyPass)
	{
		this.keyPass = keyPass;
	}
	public String getKeyStore()
	{
		return keyStore;
	}
	public String getkeyPass()
	{
		return keyPass;
	}
	public String getId()
	{
		return id;
	}
	public void setId(String id)
	{
		this.id = id;
	}
	public SSLSocket connectToLoginServer() throws Exception
	{
		SSLSocketFactory factory = (SSLSocketFactory)SSLSocketFactory.getDefault();
		SSLSocket socket = (SSLSocket)factory.createSocket("127.0.0.1", loginServerPort);
		String [] supported = socket.getSupportedCipherSuites();
		socket.setEnabledCipherSuites(supported);
		socket.startHandshake();
		return socket;
	}
	public Socket connectToLobbyServer() throws Exception
	{
		Socket socket = new Socket("127.0.0.1", lobbyServerPort);
		return socket;
	}
	private DataBase()
	{
		imgData = new HashMap<String , Image>();
		init();
	}
	public static DataBase getDataBase()
	{

		if(instance == null)
			instance = new DataBase();
		return instance;
	}
	void init()
	{
		addImage("img_logo","img//logo.png"); 
		addImage("img_mafia","img//mafia.png");
		addImage("img_key","img//key.png");
		addImage("img_lock","img//lock.png");
		addImage("img_chat","img//chat.png");
		addImage("img_person" ,"img//person.png");
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
