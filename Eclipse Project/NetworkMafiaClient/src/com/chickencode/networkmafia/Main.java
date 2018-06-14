package com.chickencode.networkmafia;

public class Main 
{
	public static void main(String args[])
	{
		System.setProperty("javax.net.ssl.keyStore", "C:\\Users\\chickening\\Documents\\GitHub\\NetworkMafia\\Eclipse Project\\NetworkMafiaClient\\bin\\.keystore\\SSLSocketClient");
		System.setProperty("javax.net.ssl.keyStorePassword","123456");
		MainFrame.getInstance();

	}
}
