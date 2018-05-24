package test;

import java.io.*;
import java.net.*;
import javax.servlet.*;
/*
 * 生成socket线程类
 */
public class SocketThread extends Thread {
	private ServerSocket serverSocket = null;
	
	public SocketThread(ServerSocket serverSocket) {
		try {
			if(serverSocket == null) {
				this.serverSocket = new ServerSocket(8877);
				System.out.println("socket start");
			}
		} catch (Exception e) {
			System.out.println("SocketThread创建socket服务出错");
			e.printStackTrace();
		}
	}
	
	public void run() {
		while(!this.isInterrupted()) {
			try {
				Socket socket = serverSocket.accept();
				if(socket != null && !socket.isClosed()) {
					new SocketOperate(socket).start();
				}
				socket.setSoTimeout(30000);
			} catch(Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	public void closeSocketServer() {
		try {
			if(serverSocket != null && !serverSocket.isClosed()) {
				serverSocket.close();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
