package com.chickencode.networkmafia;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

import javax.net.ssl.SSLServerSocket;
import javax.net.ssl.SSLServerSocketFactory;
import javax.net.ssl.SSLSocket;

public class LoginServer implements Runnable
{
	public void run()
	{
		try
		{
			System.setProperty("javax.net.ssl.keyStore", "경로");
			System.setProperty("javax.net.ssl.keyStorePassword","비밀번호");
			System.setProperty("javax.net.debug","ssl");
			SSLServerSocketFactory factory = (SSLServerSocketFactory)SSLServerSocketFactory.getDefault();
			SSLServerSocket serverSocket = (SSLServerSocket)factory.createServerSocket(14444);
			while(true)
			{
				SSLSocket clientSocket = (SSLSocket)serverSocket.accept();
				ReadWatingServer ws = new ReadWatingServer(clientSocket);
				ws.run();
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
	}
}
class ReadWatingServer implements Runnable
{
	private SSLSocket clientSocket;
	private final long timeout = 3000;	// 3초
	ReadWatingServer(SSLSocket clientSocket)
	{
		this.clientSocket = clientSocket;
	}
	public void run()
	{
		try
		{
			long firstTime = System.currentTimeMillis();	// timeOut 구현
			BufferedReader input = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
			BufferedWriter output = new BufferedWriter(new OutputStreamWriter(clientSocket.getOutputStream()));
			while(true)
			{
					String info = input.readLine();
					if(info == null)
						continue;
					String args[] = info.split(":");
					/*
					 * 아이디 비밀번호 처리
					 * 
					 * protocol
					 * 
					 * 로그인
					 * Login:id:password 성공 1 실패 0
					 *
					 * 회원가입
					 * SignUp:id:password 성공 1 실패 0
					 * 
					 * 아이디체크
					 * Check:id	중복 : 1 고유 : 0
					 */
					boolean service = true;
					if(args[0].startsWith("Login"))
					{
						String id = args[1];
						String ps = args[2];
						boolean success = DataBase.getInstance().login(id,ps);
						if(success)
							output.write("1");
						else
							output.write("0");
					}
					else if(args[0].startsWith("SignUp"))
					{
						String id = args[1];
						String ps = args[2];
						boolean success = DataBase.getInstance().signup(id, ps);
						if(success)
							output.write("1");
						else
							output.write("0");
					}
					else if(args[0].startsWith("Check"))
					{
						String id = args[1];
						boolean overlap = DataBase.getInstance().checkId(id);
						if(overlap)
							output.write("1");
						else
							output.write("0");
					}
					else
						service = false;
					if(System.currentTimeMillis() - firstTime >= timeout || service)
						break;
			}
			input.close();
			output.close();
			clientSocket.close();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
}