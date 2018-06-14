package com.chickencode.networkmafia;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class SignUpView extends JPanel
{
	static private boolean haveInstance = false;
	static private SignUpView instance;
	private Color colorBackground = new Color(0X11 ,0X11, 0X11);
	
	private JTextField inputId;
	private JPasswordField inputPassword;
	private JPasswordField inputRecheckPassword;
	
	private JButton btnCheckIdOverlap;
	private JButton btnSignUp;
	private SignUpView()
	{
		this.setBounds(0,0,540,960);
		this.setLayout(null); 
		inputId = new JTextField();
		inputId.setBounds(150,300,240,70);
		inputId.setBorder(new EmptyBorder(0, 0, 0, 0));
		inputId.setFont(new Font("¸¼Àº °íµñ" , Font.PLAIN , 30));
		this.add(inputId);
		
		inputPassword = new JPasswordField();
		inputPassword.setBounds(150,400,340,70);
		inputPassword.setBorder(new EmptyBorder(0, 0, 0, 0));
		inputPassword.setFont(new Font("¸¼Àº °íµñ" , Font.PLAIN , 30));
		this.add(inputPassword);
		
		inputRecheckPassword = new JPasswordField();
		inputRecheckPassword.setBounds(150,500,340,70);
		inputRecheckPassword.setBorder(new EmptyBorder(0, 0, 0, 0));
		inputRecheckPassword.setFont(new Font("¸¼Àº °íµñ" , Font.PLAIN , 30));
		this.add(inputRecheckPassword);
		
		btnCheckIdOverlap = new JButton();
		btnCheckIdOverlap.setBounds(390, 300 ,100, 70);
		btnCheckIdOverlap.setBorder(new EmptyBorder(0, 0, 0, 0));
		btnCheckIdOverlap.setFont(new Font("¸¼Àº °íµñ" , Font.PLAIN , 30));
		btnCheckIdOverlap.setBackground(new Color(0xFF,0xCC,0x44));
		btnCheckIdOverlap.setText("°Ë»ç");
		this.add(btnCheckIdOverlap);
		
		btnSignUp = new JButton();
		btnSignUp.setBounds(50,750 ,440,120);
		btnSignUp.setFont(new Font("¸¼Àº °íµñ" , Font.PLAIN , 40));
		btnSignUp.setBackground(new Color(0xFF,0xCC,0x44));
		btnSignUp.setText("È¸¿ø °¡ÀÔ");
		this.add(btnSignUp);
	}
	static public SignUpView getInstance()
	{
		if(!haveInstance)
		{
			haveInstance = true;
			instance = new SignUpView();
		}
		return instance;
	}
	
	public void paint(Graphics g)
	{
		g.setColor(colorBackground);
		g.fillRect(0, 0, this.getWidth(),this.getHeight());
		
		g.setColor(Color.white);
		g.setFont(new Font("¸¼Àº °íµñ" ,Font.PLAIN , 100));
		//g.drawString("È¸¿ø °¡ÀÔ",50,100);
		
		g.setFont(new Font("¸¼Àº °íµñ" ,Font.PLAIN , 30));
		g.drawString("¾ÆÀÌµð" , 20,345);
		g.setFont(new Font("¸¼Àº °íµñ" ,Font.PLAIN , 30));
		g.drawString("ºñ¹Ð¹øÈ£" , 20,445);
		g.setFont(new Font("¸¼Àº °íµñ" ,Font.PLAIN , 30));
		g.drawString("ºñ¹Ð¹øÈ£" ,20,530);
		g.drawString("È®ÀÎ" ,20,570);
		this.paintComponents(g);
	}
	
}

