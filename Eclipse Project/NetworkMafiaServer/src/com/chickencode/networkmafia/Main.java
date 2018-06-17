package com.chickencode.networkmafia;

import com.chickencode.networkmafia.GameServer.PlayerData;

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
		new Thread(server).start();
		new Thread(new LobbyServer()).start();;
		GameServer testServer = new GameServer(99, 7000);
		DataBase.getDataBase().putGameServer(99, testServer);
		testServer.createTestPlayer();
	}
}
