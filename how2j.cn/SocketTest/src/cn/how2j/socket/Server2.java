package cn.how2j.socket;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server2 {

	public static void main(String[] args) {
		try {
            ServerSocket ss = new ServerSocket(8888);
 
            System.out.println("�����ڶ˿ں�:8888");
            Socket s = ss.accept();
 
            //����������Ϣ�߳�
            new SendThread(s).start();
            //����������Ϣ�߳�
            new RecieveThread(s).start();
 
        } catch (IOException e) {
            e.printStackTrace();
        }
	}

}
