package cn.how2j.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class TestJDBC {

	public static void main(String[] args) {
		Connection c = null;
		Statement s = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			System.out.println("数据库驱动加载成功！");
			
            // 建立与数据库的Connection连接
            // 这里需要提供：
            // 数据库所处于的ip:127.0.0.1 (本机)
            // 数据库的端口号： 3306 （mysql专用端口号）
            // 数据库名称 how2java
            // 编码方式 UTF-8
            // 账号 root
            // 密码 123456
			c = DriverManager.getConnection(
					"jdbc:mysql://127.0.0.1:3306/how2java?characterEncoding=UTF-8",
					"root","123456");
			System.out.println("连接成功，获取连接对象： " + c);
			
			// 注意：使用的是 java.sql.Statement
            // 不要不小心使用到： com.mysql.jdbc.Statement;
			s = c.createStatement();
			System.out.println("获取 Statement对象： " + s);
			
			// 准备sql语句
            // 注意： 字符串要用单引号'
			String sql = "insert into hero values(null,'提莫',313,50)";
			if (s.execute(sql)) {
				System.out.println("执行插入语句成功");
			}
			
			sql = "delete from hero where id = 5";
			s.execute(sql);
			System.out.println("执行删除语句成功");
			
			sql = "update hero set name='name 5' where id = 3";
			s.execute(sql);
			System.out.println("执行更新语句成功");
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			// 数据库的连接时有限资源，相关操作结束后，养成关闭数据库的好习惯
            // 先关闭Statement
            if (s != null) {
                try {
                    s.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            // 后关闭Connection
            if (c != null) {
                try {
                    c.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
		}
	}
}
