package cn.how2j.jdbc;

import java.sql.Connection;
import java.sql.Statement;

public class TestConnectionPool {
	public static void main(String[] args) {
		ConnectionPool cp = new ConnectionPool(3);
		for (int i=0; i<100; i++) {
			new WorkingThread("working "+i, cp).start();
		}
	}
}

class WorkingThread extends Thread {
	private ConnectionPool cp;
	
	public WorkingThread(String name, ConnectionPool cp) {
		super(name);
		this.cp = cp;
	}
	
	@Override
	public void run() {
		Connection c = cp.getConnection();
		System.out.println(this.getName()+" ������ӣ���ʼ����...");
		try {
			Statement s = c.createStatement();
			Thread.sleep(1000);//ģ���ʱ1��
			s.execute("select * from hero");
			s.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		cp.returnConnection(c);
	}
}
