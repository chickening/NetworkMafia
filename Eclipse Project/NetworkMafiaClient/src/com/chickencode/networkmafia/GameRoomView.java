package com.chickencode.networkmafia;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

public class GameRoomView extends JPanel
{
	LobbyView lv = new LobbyView();
	static private boolean haveInstance = false;
	static private GameRoomView instance;
	static public GameRoomView getInstance()	//SingleTon
	{
		if(!haveInstance)
		{
			haveInstance = false;
			instance = new GameRoomView();
		}
		return instance;
	}
	
	JButton btnExit;
	JButton btnStartGame;
	JTextField inputChat;
	JList listChat;
	JScrollPane scrollChat;
	
	
	private GameRoomView()
	{
		this.setBackground(new Color(0x22,0x22,0x22));
		this.setBounds(0,0,540,960);
		
		btnExit = new JButton("������");
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
//				MainFrame.getInstance().changeView(MakeRoomView.getInstance());
			}
		});

		btnExit.setBackground(new Color(0xff,0x44,0x44));
		btnExit.setBorder(new EmptyBorder(0,0,0,0));
		btnExit.setFont(new Font("���� ���" , Font.PLAIN , 40)); 
		btnExit.setBounds(270,800,270,160);
		this.add(btnExit);
		
		btnStartGame = new JButton("���� ����");		//������ �ƴϰų� �������̸� ȸ������ �ٲ�� �ϱ�
		btnStartGame.setBackground(new Color(0xff,0xff,0x44));
		btnStartGame.setBorder(new EmptyBorder(0,0,0,0));
		btnStartGame.setBounds(0,800,270,160);
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
		listChat.setBounds(0,500,540,230);
		
		scrollChat  = new JScrollPane();
		scrollChat.setForeground(Color.white);
		scrollChat.setBorder(new LineBorder(new Color(0x33,0x33,0x33),2));
		scrollChat.setViewportView(listChat);
		scrollChat.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER); //���ι���å
		scrollChat.setBounds(0,500,540,230);
		this.add(scrollChat);
		
		inputChat = new JTextField();
		inputChat.setBackground(new Color(0x22,0x22,0x22));
		inputChat.setForeground(Color.white);
		inputChat.setBounds(70, 730,540, 70);
		inputChat.setBorder(new EmptyBorder(0, 00, 0, 0));
		inputChat.setFont(new Font("���� ���" , Font.PLAIN , 25)); 
		this.add(inputChat);
	}
	
	public void paint(Graphics g)
	{
		super.paint(g);
		g.setColor(new Color(0xFF,0xCC,0x44));
		g.fillRect(0, 730, 70, 70);
		g.drawImage(DataBase.getDataBase().getImage("img_chat"),10,740,50,50,this);
	}
}
