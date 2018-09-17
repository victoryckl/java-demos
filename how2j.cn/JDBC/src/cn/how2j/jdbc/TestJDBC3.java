package cn.how2j.jdbc;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class TestJDBC3 {

	public static void main(String[] args) {
		Connection c = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			
			c = DriverManager.getConnection(
					"jdbc:mysql://127.0.0.1:3306/how2java?characterEncoding=UTF-8",
					"root", "123456");

			//查看数据库层面的元数据
			//即数据库服务器版本，驱动版本，都有哪些数据库等等
			DatabaseMetaData dbmd = c.getMetaData();
			System.out.println("数据库产品名称：\t"+dbmd.getDatabaseProductName());
			System.out.println("数据库产品版本：\t"+dbmd.getDatabaseProductVersion());
			System.out.println("数据库和表分隔符：\t"+dbmd.getCatalogSeparator());
			System.out.println("驱动版本：\t"+dbmd.getDriverVersion());
			System.out.println("可用的数据库列表：\t");
			ResultSet rs = dbmd.getCatalogs();
			while (rs.next()) {
				System.out.println("数据库名称：\t"+rs.getString(1));
			}			
			rs.close();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (c != null) c.close();
			} catch (Exception e) {}
		}
	}

}
