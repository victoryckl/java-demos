package cn.how2j.socket;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class Client {
	public static void main(String[] args) {
		try {
			Socket s = new Socket("127.0.0.1", 8888);
			DataOutputStream dos = new DataOutputStream(s.getOutputStream());
			
			Scanner sc = new Scanner(System.in);
			dos.writeUTF(sc.next());
			
			dos.close();
			
			s.close();
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
