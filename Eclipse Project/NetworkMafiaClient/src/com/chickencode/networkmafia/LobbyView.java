package com.chickencode.networkmafia;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class LobbyView extends JPanel
{
	private static LobbyView instance;
	private static boolean haveInstance = false;
	private JPanel roomPane;
	private JButton btnPrev;
	private JButton btnNext;
	private JButton btnEntirely;	//����
	private JButton btnMakeRoom;	//�游���
	private ArrayList<Room> roomList;
	private int page = 0;
	public static LobbyView getInstance()
	{
		if(!haveInstance)
		{
			haveInstance = true;
			instance = new LobbyView();
		}
		return instance;
	}
	
	
	public LobbyView()
	{
		this.setBounds(0, 0, 540, 960);
		this.setBackground(new Color(0X11 ,0X11, 0X11));
		roomList = new ArrayList<Room>();
		roomPane = new JPanel();
		roomPane.setBounds(0,200,560,660);
		roomPane.setBackground(new Color(0x33,0x33,0x33));
		this.add(roomPane);
		btnPrev = new JButton();
		btnPrev.setBackground(new Color(0xff,0xff,0xaa));
		btnPrev.setBounds(0, 860, 270, 100);
		btnPrev.setBorder(new EmptyBorder(0,0,0,0));
		btnPrev.setFont(new Font("���� ���" , Font.PLAIN , 100));
		btnPrev.setText("<");
		btnPrev.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				page = Math.max(0,page - 1);
				refreshLobby();
			}
		});
		this.add(btnPrev);
		

		btnNext = new JButton();
		btnNext.setBackground(new Color(0xff,0xff,0xaa));
		btnNext.setBounds(270, 860, 270, 100);
		btnNext.setBorder(new EmptyBorder(0,0,0,0));
		btnNext.setFont(new Font("���� ���" , Font.PLAIN , 100));
		btnNext.setText(">");
		btnNext.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				page = Math.min((roomList.size() - 1) / 6,page + 1);
				refreshLobby();
			}
		});
		this.add(btnNext);
		
		btnMakeRoom = new JButton();
		btnMakeRoom.setBackground(new Color(0xff,0x77,0x77));
		btnMakeRoom.setBounds(0, 100, 540, 100);
		btnMakeRoom.setBorder(new EmptyBorder(0,0,0,0));
		btnMakeRoom.setFont(new Font("���� ���" , Font.PLAIN , 40));
		btnMakeRoom.setText("�� �����");
		btnMakeRoom.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				MainFrame.getInstance().changeView(MakeRoomView.getInstance());
			}
		});
		this.add(btnMakeRoom);
		
		for(int i = 0; i < 35; i++)	// test
		{
			roomList.add(new Room(10,"���� ����" ,"ġŲ", i,10));
		}
		refreshLobby();
	}
	public void addLobby(Room lobby)
	{
		roomList.add(lobby);
	}
	/*
	 * 
	 * �������� �κ� ���� �ҷ����� �Լ� �߰�
	 */
	public void refreshLobby()	// �κ�� ���� �ʱ�ȭ	��Ʈ��ũ �䱸
	{
		if((roomList.size() - 1) / 6 < page)
			page = Math.max(0, (roomList.size() - 1) / 6);
		System.out.println(page);
		roomPane.removeAll();
		for(int i = page * 6; i < Math.min(roomList.size(),(page + 1) * 6); i++)
		{
			Room room = roomList.get(i);
			JButton button = new JButton()
			{
				public void paint(Graphics g)
				{
					super.paint(g);
					g.setColor(Color.white);
					g.setFont(new Font("���� ���" , Font.PLAIN , 17));
					g.drawString(room.getName(), 10, 30);
					g.drawString(room.getOwner(), 10,70);
					g.drawString(room.getNowNumber() + " / " + room.getMaxNumber(), 400, 70);
				}
			};
			button.setBounds(0,i % 6 * 110, 540, 110);
			button.setBorder(new EmptyBorder(0, 0, 0, 0));
			button.setBackground(new Color(0x22,0x22,0x22));
			roomPane.add(button);
		}
		roomPane.repaint();
	}
	
	public void paint(Graphics g)
	{
		super.paint(g);
		g.setColor(new Color(0xdd,0x55,0x55));
		g.setFont(new Font("���� ���" , Font.BOLD , 77));
		g.drawString("LOBBY", 140 , 77);
	}
}
class Room
{
	private int id;
	private String name;
	private int nowNumber;
	private int maxNumber;
	private String owner;           
	Room(int id ,String name,String owner , int nowNumber, int maxNumber)
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
	public String getName()
	{
		return name;
	}
	public int getNowNumber()
	{
		return nowNumber;
	}
	public int getMaxNumber()
	{
		return maxNumber;
	}
	public String getOwner()
	{
		return owner;
	}
}