package com.chickencode.networkmafia;

import java.util.HashMap;
import java.util.HashSet;

public class DataBase
{
	private String sshKeyPassword;
	
	private HashMap<String, String> loginMap;
	private HashSet<String> loginUser;
	static private DataBase instance = null;
	static public DataBase getInstance()
	{
		if(instance == null)
			instance = new DataBase();
		return instance;
	}
	private DataBase()
	{
		/*
		 * ���̵� ��й�ȣ �������� �ҽ�
		 */
	}
	public boolean login(String id, String password)
	{
		String ps = loginMap.get(id);
		if(ps != null && ps.equals(password))
		{
			loginUser.add(id);
			return true;
		}
		return false;
	}
	public boolean signup(String id,String password)
	{
		loginMap.put(id, password);
		return true;
	}
	public boolean checkId(String id)
	{
		return loginMap.containsKey(id);
	}
}
