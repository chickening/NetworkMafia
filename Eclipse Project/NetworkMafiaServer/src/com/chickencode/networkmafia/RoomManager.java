package com.chickencode.networkmafia;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;


public class RoomManager {
	
	private String host = "localhost";
	private int port = 3000;
	private Selector selector;
	private ServerSocketChannel serverChannel;
	private ServerSocket serverSocket;
	private static ArrayList roomList; // �� list
	private static AtomicInteger atomicInteger;
	
		static {
			roomList = new ArrayList();
			atomicInteger = new AtomicInteger();
		}
		
		public RoomManager() {
			try {
				selector = Selector.open();
				serverChannel = ServerSocketChannel.open();
				serverChannel.configureBlocking(false); //Non blocking �����
				serverSocket = serverChannel.socket(); //channel �����̶� server ���� ���������� ����
				InetSocketAddress isa = new InetSocketAddress(host,port); //Inet ����
				serverSocket.bind(isa); //server socket �̶� address ����
				serverChannel.register(selector, SelectionKey.OP_ACCEPT); // ä���� selector�� ���
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
			readyServer();
		}
	

		private void readyServer() {
			// TODO Auto-generated method stub
			
		}
		


	

		/* �� �� ���� */
//		public static GameRoom createRoom() {
//			int roomId = atomicInteger.incrementAndGet(); // room id ��ȣ
//			
//		}
}
