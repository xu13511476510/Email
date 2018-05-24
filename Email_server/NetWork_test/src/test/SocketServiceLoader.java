package test;

import javax.servlet.*;

/*
 * 监听类
 */

public class SocketServiceLoader implements ServletContextListener {
	private SocketThread socketThread; //socket server线程
	
	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
		if(socketThread != null && !socketThread.isInterrupted()) {
			socketThread.closeSocketServer();
			socketThread.interrupt();
		}
	}
	
	@Override
	public void contextInitialized(ServletContextEvent arg0) {
		if(socketThread == null) {
			socketThread = new SocketThread(null); //创建新线程
			socketThread.start(); //启动线程
		}
	}
}
