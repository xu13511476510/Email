package test;

import java.io.*;
import java.net.*;

public class Server {
	public static void main(String args[]) {
		ServerSocket server;
		try {
			server = new ServerSocket(1234); //�����������˶���
			Socket sk = server.accept(); //��ÿͻ��˶���
			
			BufferedReader br = new BufferedReader(new InputStreamReader(sk.getInputStream()));
			System.out.println(br.readLine());
			br.close();
			server.close();
		} catch (IOException e) {
			System.out.println(e);
		}
	}
}
