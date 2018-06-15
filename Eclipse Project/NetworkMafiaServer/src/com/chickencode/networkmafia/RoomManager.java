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
	private static ArrayList roomList; // 방 list
	private static AtomicInteger atomicInteger;
	
		static {
			roomList = new ArrayList();
			atomicInteger = new AtomicInteger();
		}
		
		public RoomManager() {
			try {
				selector = Selector.open();
				serverChannel = ServerSocketChannel.open();
				serverChannel.configureBlocking(false); //Non blocking 만들기
				serverSocket = serverChannel.socket(); //channel 소켓이랑 server 소켓 내부적으로 연결
				InetSocketAddress isa = new InetSocketAddress(host,port); //Inet 생성
				serverSocket.bind(isa); //server socket 이랑 address 연결
				serverChannel.register(selector, SelectionKey.OP_ACCEPT); // 채널을 selector에 등록
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
			readyServer();
		}
	

		private void readyServer() {
			// TODO Auto-generated method stub
			
		}
		


	

		/* 빈 방 생성 */
//		public static GameRoom createRoom() {
//			int roomId = atomicInteger.incrementAndGet(); // room id 번호
//			
//		}
}
