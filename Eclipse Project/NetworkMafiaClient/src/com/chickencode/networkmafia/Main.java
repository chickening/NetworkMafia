package com.chickencode.networkmafia;import java.io.File;

import javax.xml.crypto.Data;

public class Main 
{
	public static void main(String args[])
	{
		if(args.length != 1)
		{
			System.out.println("java className password");
			return;
		}
		//System.setProperty("javax.net.debug","ssl");
		DataBase.getDataBase().setKeyStore("C:\\keystore\\clientKey");
		DataBase.getDataBase().setKeyPass(args[0]);
		System.setProperty("javax.net.ssl.trustStore", "C:\\keystore\\cacerts");
		System.setProperty("javax.net.ssl.keyStore", DataBase.getDataBase().getKeyStore());
		System.setProperty("javax.net.ssl.keyStorePassword", DataBase.getDataBase().getkeyPass());
		MainFrame.getInstance();

	}
}
