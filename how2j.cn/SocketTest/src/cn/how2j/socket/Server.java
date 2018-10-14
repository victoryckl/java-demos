package cn.how2j.socket;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
	public static void main(String[] args) {
		try {
			ServerSocket ss = new ServerSocket(8888);
			System.out.println("¼àÌý¶Ë¿Ú8888");
			Socket s = ss.accept();
			
			DataInputStream dis = new DataInputStream(s.getInputStream());
			String msg = dis.readUTF();
			System.out.println(msg);
			dis.close();
			ss.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
