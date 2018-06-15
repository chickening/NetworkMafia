package com.chickencode.networkmafia;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class MakeRoomView extends JPanel
{
	private static MakeRoomView instance;
	private static boolean haveInstance = false;
	static public MakeRoomView getInstance()	//SingleTon
	{
		if(!haveInstance)
		{
			haveInstance = false;
			instance = new MakeRoomView();
		}
		return instance;
	}
	
	JTextField inputName;
	JPasswordField inputPassword;
	JButton btnMakeRoom;
	
	private MakeRoomView()
	{
		this.setBounds(0,0,540,960);
		this.setBackground(new Color(0x22,0x22,0x22));
		
		inputName = new JTextField();
		inputName.setBounds(50, 230, 440, 70);
		inputName.setBorder(new EmptyBorder(0, 0, 0, 0));
		inputName.setFont(new Font("¸¼Àº °íµñ" , Font.PLAIN , 35));
		this.add(inputName);
		
		inputPassword = new JPasswordField();
		inputPassword.setBounds(50, 430, 440, 70);
		inputPassword.setBorder(new EmptyBorder(0, 0, 0, 0));
		inputPassword.setFont(new Font("¸¼Àº °íµñ" , Font.PLAIN , 35));
		this.add(inputPassword);
		
		btnMakeRoom = new JButton();
		btnMakeRoom.setBounds(50, 600,440, 100);
		btnMakeRoom.setBorder(new EmptyBorder(0, 0, 0, 0));
		btnMakeRoom.setFont(new Font("¸¼Àº °íµñ" , Font.PLAIN , 30));
		btnMakeRoom.setBackground(new Color(0xFF,0xCC,0x44));
		btnMakeRoom.setText("¹æ ¸¸µé±â");
		btnMakeRoom.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
					MainFrame.getInstance().changeView(GameRoomView.getInstance());
			}
		});
		this.add(btnMakeRoom);
	}
	public void paint(Graphics g)
	{
		super.paint(g);
		
		g.setColor(Color.WHITE);
		g.setFont(new Font("¸¼Àº °íµñ" , Font.PLAIN , 40));
		g.drawString("¹æ ÀÌ¸§" ,200, 190);
		g.drawString("ºñ¹Ð¹øÈ£" ,200, 390);
		
	}
}
