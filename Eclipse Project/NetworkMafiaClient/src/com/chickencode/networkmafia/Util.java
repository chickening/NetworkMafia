package com.chickencode.networkmafia;

import java.awt.Point;

public class Util 
{
	Point getPercentSize(Point target , double percentX ,double percentY)
	{
		Point r = new Point();
		r.setLocation(target.getX() * percentX / 100 , target.getY() * percentY / 100);
		return r;
	}
}
