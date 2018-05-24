package test;

import java.io.*;
import java.net.*;
import java.sql.SQLException;

import javassist.util.proxy.MethodHandler;

import javax.servlet.*;
import javax.sql.rowset.spi.XmlReader;

/*
 * 多线程处理socket接收的数据
 */

public class SocketOperate extends Thread {
	private Socket socket;
	
	public SocketOperate(Socket socket) {
		this.socket = socket;
	}
	
	public void run() {
		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream(),"UTF-8"));
			while(true) {
				String strXML = "";
				String message = "";
				while((message = br.readLine()) != null){  
					    strXML+=message;  
					}  
				System.out.println(strXML.toString());
				mail eva=new mail();
				int i=0,k0=-1,k1=-1,t=0;
				/*while(i<strXML.length()){
					if(strXML.charAt(i)==':'&&k0!=-1){
						k1=i;
						switch(t){
						case 0:
							eva.setSender(strXML.substring(k0,k1));break;
						case 1:
							eva.setReceiver(strXML.substring(k0,k1));break;
						case 2:
							eva.setSubject(strXML.substring(k0,k1));break;
						case 3:
							eva.setContext(strXML.substring(k0,k1));break;
						case 4:
							eva.setData(strXML.substring(k0,k1));break;
						case 5:
							eva.setFlag(strXML.substring(k0,k1));break;
						}
						t++;k0=-1;k1=-1;
						System.out.println("  t:"+t);
					}
					else if(strXML.charAt(i)==':'&&k0==-1)
						k0=i+1;
					i++;
				}*/
				int a1,a2,a3,a4,a5,a6;
				a1=strXML.indexOf("receiver:");
				a2=strXML.indexOf("sender:");
				a3=strXML.indexOf("subject:");
				a4=strXML.indexOf("context:");
				a5=strXML.indexOf("date:");
				a6=strXML.indexOf("flag:");
				eva.setReceiver(strXML.substring(a1+9,a2));
				eva.setSender(strXML.substring(a2+7,a3));
				eva.setSubject(strXML.substring(a3+8,a4));
				eva.setContext(strXML.substring(a4+8,a5));
				eva.setData(strXML.substring(a5+5,a6));
				eva.setFlag(strXML.substring(a6+5,strXML.length()));
				System.out.println(eva.getContext());
				try {
					System.out.println("1:"+eva.getSender()+" 2:"+eva.getReceiver()+" 3:"+eva.getSubject()+" 4:"+eva.getContext()+" 5:"+eva.getData()+" 6:"+eva.getFlag());
					new mailDao().writemail(eva);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				if("end".equals(strXML)) {
					System.out.println("准备关闭socket");
					break;
				}
				if("".equals(strXML))
					continue;
				System.out.println("客户端发来：" + strXML.toString());	
				break;
			}
			socket.close();
			System.out.println("socket stop....");
		} catch(IOException ex) {
			
		} finally {
			
		}
	}
}
