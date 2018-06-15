package com.chickencode.networkmafia;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.ArrayList;
import java.util.Iterator;

public class LobbyServer {
	private String host = "localhost";
	private int port = 3000;
	private Selector selector;
	private ServerSocketChannel serverChannel;
	private ServerSocket serverSocket;
	private ArrayList roomList = new ArrayList();
	private ArrayList list = new ArrayList();
	public LobbyServer() {
		
			try { //채널 관리 하는 곳 = Selector
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
	
	
	
	
	public void readyServer() {
		// TODO Auto-generated method stub
		
		System.out.println("요청을 기다리는 중...");
		try {
			while(true) {
			selector.select();
			Iterator iterator = selector.selectedKeys().iterator();
			while(iterator.hasNext()) {
				SelectionKey key = (SelectionKey)iterator.next();
				if(key.isAcceptable()) {
					accept(key);
				}else if(key.isReadable()) {
					read(key);
				}
				iterator.remove();
			}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
	}
	private void accept(SelectionKey key) {
		// TODO Auto-generated method stub
		ServerSocketChannel serverChannel = (ServerSocketChannel)key.channel();
		SocketChannel socketChannel = null;
		try {
			socketChannel = serverChannel.accept();
			if(socketChannel==null)
				return;
			socketChannel.configureBlocking(false);
			socketChannel.register(selector, port, SelectionKey.OP_READ);
			list.add(socketChannel);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void read(SelectionKey key) {
		// TODO Auto-generated method stub
		SocketChannel socketChannel = (SocketChannel)key.channel();
		ByteBuffer buffer = ByteBuffer.allocateDirect(1024);
		try {
			int read = socketChannel.read(buffer);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			try {
				socketChannel.close();
			} catch (IOException e1) {
			}
			list.remove(socketChannel);
		}
			try {
				broadCasting(buffer);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		buffer.clear();
	}
		
	private void broadCasting(ByteBuffer buffer) throws IOException {
		// TODO Auto-generated method stub
		buffer.flip();
		for(int i=0;i<list.size();i++) {
			SocketChannel sc = (SocketChannel)list.get(i);
			sc.write(buffer);
			buffer.rewind();
		}
	}

	

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new LobbyServer();
	}

}
