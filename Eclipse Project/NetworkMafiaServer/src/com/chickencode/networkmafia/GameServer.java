package com.chickencode.networkmafia;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

import javax.swing.plaf.SliderUI;
public class GameServer implements Runnable
{
	GameServer instance;
	ServerSocket server;
	GameData gameData;
	Thread gameServerThread;
	boolean endServer = false;
	GameServer(int id ,int port)
	{
		instance = this;
		gameData = new GameData();
		gameData.port = port;
		gameData.roomid = id;
		try
		{
			server = new ServerSocket(gameData.port);
		}
		catch(Exception e)
		{
			
		}
		startServer();
	}
	public void startServer()
	{
		gameServerThread = new Thread(this);
		gameServerThread.start();
	}
	public void run()
	{
		new Thread(new GameConnnectThread()).start();
		while(!endServer)
		{
			try
			{
				while(gameData.players.size() != 8)
					gameServerThread.sleep(100);
				
				/*
				 * 게임이 시작 되엇습니다.
				 */
				System.out.println("[GameServer] : 게임시작");
				gameData.end = false;	// 게임 시작
				sendMessageAll("start");
				
				startGame();
				while(!gameData.end)
				{
					System.out.println("[GameServer] : 아침");
					gameData.time = 1;
					notifyTime();
					thinkResult();
					if(gameData.end)
						break;
					closeAct();
					gameServerThread.sleep(gameData.morning);
					System.out.println("[GameServer] : 투표");
					
					gameData.time = 2;
					notifyTime();
					openVote();
					gameServerThread.sleep(gameData.voteTime);
					System.out.println("[GameServer] : 저녁");
					
					thinkResult();
					if(gameData.end)
						break;
					openAct();
					voteResult();
					gameData.time = 3;
					notifyTime();
					gameServerThread.sleep(gameData.night);
				}
				gameServerThread.sleep(gameData.waitTime);	//기다리는시간
			}catch(Exception e) {}
		
		}
	}
	public void createTestPlayer()
	{
		for(int i = 0; i < 7; i++)
		{
			PlayerData p = new PlayerData();
			p.id = ""+(int)(Math.random() * 1000 + 1);
			p.input = new BufferedReader(new InputStreamReader(System.in));
			p.output = new BufferedWriter(new OutputStreamWriter(System.out));
			gameData.players.add(p);
		}
		reviewPlayer();
	}
	public void closeThread()
	{
		gameData.end = true;
		endServer = true;
	}
	public void startGame()
	{
		for(int i = 0; i < gameData.players.size() ;i++)
			gameData.players.get(i).alive = true;
		int randomJob[] = {1,1,1,1,1,2,2,3};
		for(int i = 0; i < 1000; i++)
		{
			int a = (int) (Math.random() * 8);
			int b = (int) (Math.random() * 8);
			int t = randomJob[a];
			randomJob[a] = randomJob[b];
			randomJob[b] = t;
		}
		for(int i = 0; i < gameData.players.size() ;i++)
			gameData.players.get(i).job = randomJob[i];
		int team = -1;
		for(int i = 0; i < 8; i++)
		{
			if(randomJob[i] == 2 && team == -1)
				team = i;
			else if(randomJob[i] == 2 && team != -1)
			{
				sendMessage(team, "job:"+ randomJob[i] + ":" +i);
				sendMessage(i, "job:"+ randomJob[i] + ":" +team);
			}
			else
				sendMessage(i, "job:"+ randomJob[i]);
		}
		gameData.end = false;
		gameData.chooseMapia = -1;
		gameData.choosePolice = -1;
	}
	public void thinkResult()
	{
		int numberMapia = 0;
		int numberSimin = 0;
		for(int i = 0; i < gameData.players.size(); i++)
			if(gameData.players.get(i).alive)
			{
				if(gameData.players.get(i).job == 2)
					++numberMapia;
				else
					++numberSimin;
			}
		System.out.println("남은 마피아 " + numberMapia);
		System.out.println("남은 시민  " + numberSimin);
		if(numberMapia == numberSimin)
			winMapia();
		else if(numberMapia == 0)
			winSimin();
			
	}
	
	public void winMapia()
	{
		for(int i = 0; i < gameData.players.size(); i++)
			if(gameData.players.get(i).job == 2)
				sendMessage(i, "end:1");
			else
				sendMessage(i, "end:0");
		gameData.end = true;
	}
	public void winSimin()
	{
		for(int i = 0; i < gameData.players.size(); i++)
			if(gameData.players.get(i).job != 2)
				sendMessage(i, "end:1");
			else
				sendMessage(i, "end:0");
		gameData.end = true;
	}
	public void notifyTime()
	{
		sendMessageAll("time:" + gameData.time);
	}
	public void openVote()
	{
		for(int i = 0 ; i < gameData.votes.length; i++)
			gameData.votes[i] = -1;
	}
	public void openAct()
	{
		gameData.chooseMapia = -1;
		gameData.choosePolice = -1;
	}
	public void closeAct()
	{
		if(gameData.chooseMapia != -1)
			kill(gameData.chooseMapia);
		if(gameData.choosePolice != -1)
			prove(gameData.choosePolice);
			
	}
	public void prove(int number)
	{
		boolean isMapia = gameData.players.get(number).job == 2; 
		for(int i = 0; i < gameData.players.size(); i++)
			if(gameData.players.get(i).job == 3)
				sendMessage(i,"prove:"+ (isMapia ? 1 : 0));
			
	}
	public void kill(int number)
	{
		sendMessageAll("die:" + number);
		gameData.players.get(number).alive = false;
	}
	public void voteResult()
	{
		int[] index = new int[8];
		for(int i = 0; i < 8; i++)
			index[i] = 0;
		for(int i = 0; i < 8; i++)
		{
			if(gameData.votes[i] == -1)
				continue;
			index[gameData.votes[i]]++;
		}
		int maxValue = 0;
		int maxIndex = -1;
		for(int i = 0; i < 8; i++)
		{
			if(maxValue < index[i])
			{
				maxValue = index[i];
				maxIndex = i;
			}
			else if(maxValue == index[i])
				maxIndex = -1;
		}
		if(maxIndex != -1)
		{
			kill(maxIndex);
		}
	}
	public void reviewPlayer()
	{
		String playerinfo = "playerinfo";
		for(int i = 0; i < gameData.players.size(); i++)
			playerinfo += (":" + gameData.players.get(i).id);
		sendMessageAll(playerinfo);
		for(int i = 0; i < gameData.players.size(); i++)	//숫자 재활당
			gameData.players.get(i).number = i;
	}
	public void sendMessageAll(String info)
	{
		System.out.println("[GameServer] all 보냄 : " + info);
		try
		{
			for(int i = 0; i < gameData.players.size(); i++)
			{
				PlayerData player = gameData.players.get(i);
				player.output.write(info);
				player.output.newLine();
				player.output.flush();
			}
		}catch(Exception e)
		{
			
		}
	}
	public void sendMessage(int number, String info)
	{
		System.out.println("[GameServer] 보냄 : " + info);
		try
		{
			PlayerData player = gameData.players.get(number);
			player.output.write(info);
			player.output.newLine();
			player.output.flush();
		}
		catch(Exception e)
		{
			
		}
	}
	class GameConnnectThread implements Runnable	// 플레이어 받는 그런 쓰레드
	{
		
		public void run()
		{
			try
			{
				while(!endServer)
				{
					while(gameData.players.size() == 8);	// 플레이어수가 꽉차면 무한대기
					Socket client = server.accept();
					System.out.println("새로운 플레이어 연결하는중....");
					PlayerData newPlayer = new PlayerData();
					newPlayer.client = client;
					newPlayer.input = new BufferedReader(new InputStreamReader(client.getInputStream()));
					newPlayer.output = new BufferedWriter(new OutputStreamWriter(client.getOutputStream()));
					newPlayer.connect = true;
					String info;
					while((info = newPlayer.input.readLine()) == null);
					
					System.out.println("[gameServer] 받음 : " + info);
					String args[] = info.split(":");
					newPlayer.id = args[1];
					newPlayer.runInputThread();
					gameData.players.add(newPlayer);
					reviewPlayer();
				}
			}
			catch(Exception e)
			{
				
			}
		}
	}
	class GameData
	{
		//final int morning = 60000;
		//final int voteTime = 10000;
		//final int night = 30000;
		//final int waitTime = 10000;
		final int morning = 5000;
		final int voteTime = 5000;
		final int night = 5000;
		final int waitTime = 5000;
		int chooseMapia = -1;
		int choosePolice = -1;
		int roomid;
		int port;
		int time;
		int votes[] = new int[8];
		int choose[] = new int[8];
		boolean end = true;
		String roomName;
		ArrayList<PlayerData> players;
		GameData()
		{
			players = new ArrayList<>();
		}
	}
	class PlayerData
	{
		String id;
		int job;
		boolean alive;
		Socket client;
		BufferedReader input;
		BufferedWriter output;
		int number;
		boolean connect;
		Thread inputThread;
		public void runInputThread()
		{
			inputThread = new Thread(new PlayerInputThread());
			inputThread.start();
		}
		class PlayerInputThread implements Runnable
		{
			public void run()
			{
				while(connect)
				{
					try
					{
						String info;
						while((info = input.readLine()) == null);
						String args[] = info.split(":");
						System.out.println("[GameServer] 받음 : " + info);
						if(args[0].equals("vote"))
						{
							if(gameData.time == 2 && alive)
								if(gameData.votes[number] == -1)
									if(gameData.players.get(Integer.parseInt(args[1])).alive)
										gameData.votes[number] = Integer.parseInt(args[1]);
								
									
							
						}
						else if(args[0].equals("select"))
						{
							if(gameData.time == 3 && alive)
							{
								if(job == 2 && gameData.chooseMapia == -1)
								{
									gameData.chooseMapia = Integer.parseInt(args[1]);
								}
							}
							else if(gameData.time == 3 && alive)
							{
								if(job == 3 && gameData.choosePolice == -1)
								{
									gameData.choosePolice = Integer.parseInt(args[1]);
								}
							}
						}
						else if(args[0].equals("close"))
						{
							instance.kill(number);
							gameData.players.remove(number);
							gameData.end = true;
							reviewPlayer();
						}
					}catch(Exception e)	// 연결 끊어짐
					{			
					}
				}
			}
		}
	}
}