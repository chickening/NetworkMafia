package com.chickencode.networkmafia;

import java.awt.Color;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class MainFrame extends JFrame
{
	JPanel front;
	MainFrame()
	{
		this.setResizable(false);
		this.setBounds(0,0,540,960);
		this.setLayout(null);
		this.setVisible(true);
		front = LoginView.getInstance();
		this.add(front);
		this.repaint();
	}
}
 