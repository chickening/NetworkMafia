package com.chickencode.networkmafia;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

public class GameRoomView extends JPanel
{;
	static private GameRoomView instance = null;
	static public GameRoomView getInstance()	//SingleTon
	{
		if(instance == null)
			instance = new GameRoomView();
		return instance;
	}
	
	JButton btnExit;
	JButton btnStartGame;
	JTextField inputChat;
	JList listChat;
	JScrollPane scrollChat;
	/* ���� ��� ��ư �߰� */
	
	/*
	 * Protocol
	 * 
	 * �ޱ�
	 * 
		 * ó���� ���� ���� : job : ����
		 * ä�� : chat : number :  content
		 * ���� ���� : die : number
		 * �������� : prove : number : yesorno
		 * �÷��̾� ���� playerinfo : id[�迭]
		 * ������
		 * ä�� chat : content
		 * ��ǥ vote : number
		 * ���� ���� : select : number
		 * �÷��̾� ���� : playerinfo
	 * 
	 * 
	 */
	private GameRoomView()
	{
		this.setBackground(new Color(0x22,0x22,0x22));
		this.setBounds(0,0,540,960);
		
		btnExit = new JButton("������");
		btnExit.setBackground(new Color(0xff,0x44,0x44));
		btnExit.setBorder(new EmptyBorder(0,0,0,0));
		btnExit.setFont(new Font("���� ���" , Font.PLAIN , 40)); 
		btnExit.setBounds(270,860,270,100);
		this.add(btnExit);
		
		btnStartGame = new JButton("���� ����");		//������ �ƴϰų� �������̸� ȸ������ �ٲ�� �ϱ�
		btnStartGame.setBackground(new Color(0xff,0xff,0x44));
		btnStartGame.setBorder(new EmptyBorder(0,0,0,0));
		btnStartGame.setBounds(0,860,270,100);
		btnStartGame.setFont(new Font("���� ���" , Font.PLAIN , 40)); 
		this.add(btnStartGame);
		
		String test[] = new String[100];
		for(int i = 0; i <test.length; i++)
			test[i] = "Test + " + i;
		listChat = new JList(test);
		listChat.setBackground(new Color(0x22,0x22,0x22));
		listChat.setBorder(new EmptyBorder(0,0,0,0));
		listChat.setForeground(Color.white);
		listChat.setFont(new Font("���� ���" , Font.PLAIN , 12)); 
		//listChat.setBounds(0,560,540,230);
		
		scrollChat  = new JScrollPane();
		scrollChat.setForeground(Color.white);
		scrollChat.setBorder(new LineBorder(new Color(0x33,0x33,0x33),2));
		scrollChat.setViewportView(listChat);
		scrollChat.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER); //���ι���å
		scrollChat.setBounds(0,560,540,230);
		this.add(scrollChat);
		
		inputChat = new JTextField();
		inputChat.setBackground(new Color(0x22,0x22,0x22));
		inputChat.setForeground(Color.white);
		inputChat.setBounds(70, 790,540, 70);
		inputChat.setBorder(new EmptyBorder(0, 00, 0, 0));
		inputChat.setFont(new Font("���� ���" , Font.PLAIN , 25)); 
		this.add(inputChat);
	}
	public void paint(Graphics g)
	{
		super.paint(g);
		g.setColor(new Color(0xFF,0xCC,0x44));
		g.fillRect(0, 790, 70, 70);
		g.drawImage(DataBase.getDataBase().getImage("img_chat"),10,800,50,50,this);
		
		for(int i  = 0; i < 4; i++)
		{
			//g.fillRect(16, i * 136 + 16, 120, 120);
			g.drawImage(DataBase.getDataBase().getImage("img_person"),17,i * 136 + 17 , 100 , 100,this);
		}
		for(int i  = 0; i < 4; i++)
		{
			//g.fillRect(404, i * 136 + 16, 120, 120);
			g.drawImage(DataBase.getDataBase().getImage("img_person"),405,i * 136 + 17 , 100 , 100,this);
		}
		
		/*���߿� ���̵� �߰� */
	}
}
class GameData
{
	
}
class PlayerData
{
	int number;
	String id;
	int 
}