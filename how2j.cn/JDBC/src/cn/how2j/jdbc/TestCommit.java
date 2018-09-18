package cn.how2j.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class TestCommit {

	public static void main(String[] args) {
		Connection c = null;
		Statement s = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			
			c = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/how2java?characterEncoding=UTF-8",
					"root", "123456");
			s = c.createStatement();
			
			// 有事务的前提下
            // 在事务中的多个操作，要么都成功，要么都失败
			c.setAutoCommit(false);
			// 加血的SQL
			String sql1 = "update hero set hp=hp+1 where id = 22";
			s.execute(sql1);
			// 减血的SQL
            // 不小心写错写成了 updata(而非update)
			String sql2 = "updata hero set hp=hp-1 where id = 22";
			s.execute(sql2);
			
			// 手动提交
			c.commit();			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (s != null) { s.close(); }
			} catch (Exception e) {
				e.printStackTrace();
			}
			try {
				if (c != null) { c.close(); }
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

}
