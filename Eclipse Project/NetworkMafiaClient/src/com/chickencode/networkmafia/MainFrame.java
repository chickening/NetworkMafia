package com.chickencode.networkmafia;

import java.awt.Color;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.text.View;

public class MainFrame extends JFrame
{
	private JPanel visiableView;
	private static MainFrame instance;
	private static boolean haveInstance = false;
	
	static public MainFrame getInstance()
	{
		if(!haveInstance)
		{
			haveInstance = true;
			instance = new MainFrame();
		}
		return instance;
	}
	private MainFrame()
	{
		this.setResizable(false);
		this.setBounds(0,0,540,960);
		this.setLayout(null);
		this.setVisible(true);
		visiableView = LoginView.getInstance();
		this.add(visiableView);
		this.repaint();
	}
	public void changeView(JPanel view)
	{
		 this.remove(visiableView);
		 visiableView = view;
		 this.add(view);
		 this.repaint();
	}
}
 