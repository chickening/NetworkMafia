package com.chickencode.networkmafia;

public class Main
{
	static public void main(String args[])
	{
		/*if(args.length != 2)
		{
			
			System.out.println("java -Djavax.net.ssl.trustStore=trustedcerts className keystore keypasss");
			return;
		}*/
		System.setProperty("javax.net.ssl.trustStore", "C:\\Program Files\\Java\\jre1.8.0_161\\lib\\security\\cacerts");
		DataBase.getDataBase().setKeyStore("C:\\keystore\\ServerKey");
		DataBase.getDataBase().setKeyPass("123456");
		System.setProperty("javax.net.ssl.trustStore", "C:\\keystore\\cacerts");
		LoginServer server = new LoginServer();
		server.run();
	}
}
