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
			System.setProperty("javax.net.ssl.keyStore", "C:\\Users\\chickening\\Documents\\GitHub\\NetworkMafia\\Eclipse Project\\NetworkMafiaServer\\bin\\.keystore\\SSLSocketServerKey");
			System.setProperty("javax.net.ssl.keyStorePassword","123456");
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
	private final long timeout = 3000;	// 3��
	ReadWatingServer(SSLSocket clientSocket)
	{
		this.clientSocket = clientSocket;
	}
	public void run()
	{
		try
		{
			long firstTime = System.currentTimeMillis();	// timeOut ����
			BufferedReader input = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
			BufferedWriter output = new BufferedWriter(new OutputStreamWriter(clientSocket.getOutputStream()));
			while(!input.ready())
			{
				if(System.currentTimeMillis() - firstTime >= timeout)
				{
					input.close();
					output.close();
					clientSocket.close();
					System.out.println("TimeOut");
					return;
				}
			}
					String info = input.readLine();
					System.out.println("���� ���� : " + info);
					String args[] = info.split(":");
					/*
					 * ���̵� ��й�ȣ ó��
					 * 
					 * protocol
					 * 
					 * �α���
					 * Login:id:password ���� 1 ���� 0
					 *
					 * ȸ������
					 * SignUp:id:password ���� 1 ���� 0
					 * 
					 * ���̵�üũ
					 * Check:id	�ߺ� : 1 ���� : 0
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