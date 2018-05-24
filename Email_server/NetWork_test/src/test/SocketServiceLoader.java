package test;

import javax.servlet.*;

/*
 * ������
 */

public class SocketServiceLoader implements ServletContextListener {
	private SocketThread socketThread; //socket server�߳�
	
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
			socketThread = new SocketThread(null); //�������߳�
			socketThread.start(); //�����߳�
		}
	}
}
