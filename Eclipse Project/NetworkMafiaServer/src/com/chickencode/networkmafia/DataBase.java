package com.chickencode.networkmafia;

import java.util.HashMap;
import java.util.HashSet;

public class DataBase
{
	private String sshKeyPassword;
	
	private HashMap<String, String> loginMap;
	private HashSet<String> loginUser;
	static private DataBase instance = null;
	private String keyStore = "";
	private String keyPass = "";
	public void setKeyStore(String keyStore)
	{
		this.keyStore = keyStore;
	}
	public void setKeyPass(String keyPass)
	{
		this.keyPass = keyPass;
	}
	public String getKeyStore()
	{
		return keyStore;
	}
	public String getkeyPass()
	{
		return keyPass;
	}
	static public DataBase getDataBase()
	{
		if(instance == null)
			instance = new DataBase();
		return instance;
	}
	private DataBase()
	{
		/*
		 * 아이디 비밀번호 가져오는 소스
		 */
		loginMap = new HashMap<>();
		loginUser = new HashSet<>();
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
