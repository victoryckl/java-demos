package cn.how2j.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.ArrayList;
import java.util.List;

public class ConnectionPool {
	private List<Connection> cs = new ArrayList<Connection>();

	public ConnectionPool(int size) {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			for (int i=0; i<size; i++) {
				Connection c = DriverManager.getConnection(
						"jdbc:mysql://localhost:3306/how2java?characterEncoding=UTF-8",
						"root", "123456");
				cs.add(c);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public synchronized Connection getConnection() {
		while (cs.isEmpty()) {
			try {
				this.wait();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		Connection c = cs.remove(0);
		return c;
	}
	
	public synchronized void returnConnection(Connection c) {
		cs.add(c);
		this.notifyAll();
	}
}
