package com.chickencode.networkmafia;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.util.ArrayList;

public class LobbyServer implements Runnable
{
	public void run()
	{
		try 
		{
			ServerSocket server = new ServerSocket(1116);
			while(true)
			{
				Socket client = server.accept();
				new LobbyServiceThread(client).run();
			}
		} 
		catch (IOException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
}
class LobbyServiceThread implements Runnable
{
	Socket client;
	LobbyServiceThread(Socket client)
	{
		this.client = client;
	}
	public void run()
	{
		try
		{
			BufferedReader input = new BufferedReader(new InputStreamReader(client.getInputStream()));
			BufferedWriter output = new BufferedWriter(new OutputStreamWriter(client.getOutputStream()));
			String info;
			while((info = input.readLine()) != null);
			if(info.startsWith("refresh"))
			{
				ArrayList<GameServer> gameServers = DataBase.getDataBase().getAllGameServer();	//방아이디:방이름:방장:플레이어수
				for(int i = 0; i < gameServers.size(); i++)
				{
					GameServer gs = gameServers.get(i);
					//보내기
				}
				output.newLine();
				output.flush();
			}
			else if(info.startsWith("join"))	//join:roomid:id		return 1:port 성공  2 : 플레이어 꽉참 3 : 서버에러
			{
				/*
				 * 게임서버랑 연결작업
				 */
			}
			else if(info.startsWith("makeroom"))	//makeroom:roomname:id
			{
				
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
}