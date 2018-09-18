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
			
			// �������ǰ����
            // �������еĶ��������Ҫô���ɹ���Ҫô��ʧ��
			c.setAutoCommit(false);
			// ��Ѫ��SQL
			String sql1 = "update hero set hp=hp+1 where id = 22";
			s.execute(sql1);
			// ��Ѫ��SQL
            // ��С��д��д���� updata(����update)
			String sql2 = "updata hero set hp=hp-1 where id = 22";
			s.execute(sql2);
			
			// �ֶ��ύ
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
