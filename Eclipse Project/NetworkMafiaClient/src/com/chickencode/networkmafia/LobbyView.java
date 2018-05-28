package com.chickencode.networkmafia;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.JPanel;

public class LobbyView extends JPanel
{
	private static LobbyView instance;
	private static boolean haveInstance = false;
	public static LobbyView getInstance()
	{
		if(!haveInstance)
		{
			haveInstance = true;
			instance = new LobbyView();
		}
		return instance;
	}
	
	private HashMap<Integer,Lobby> lobbyMap;
	
	private LobbyView()
	{
		this.setBounds(0, 0, 540, 960);
		lobbyMap = new HashMap<Integer,Lobby>();
	}
	public void addLobby(Lobby lobby)
	{
		lobbyMap.put(lobby.getId(),lobby);
	}
	public Lobby getLobby(int id)
	{
		return lobbyMap.get(id);
	}
	public void refreshLobby()	// 로비방 정보 초기화	네트워크 요구
	{
		
	}
	
	public void paint(Graphics g)
	{
		
	}
}
class Lobby
{
	private int id;
	private String name;
	private int nowNumber;
	private int maxNumber;
	private String owner;           
	Lobby(int id ,String name,String owner , int nowNumber, int maxNumber)
	{
		this.id = id;
		this.name = name;
		this.owner = owner;
		this.nowNumber = nowNumber;
		this.maxNumber = maxNumber;
	}
	public int getId()
	{
		return id;
	}
}