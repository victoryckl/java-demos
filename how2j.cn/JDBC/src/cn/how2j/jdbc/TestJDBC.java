package cn.how2j.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class TestJDBC {

	public static void main(String[] args) {
		Connection c = null;
		Statement s = null;
		PreparedStatement ps = null;
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
			s.execute(sql);
			System.out.println("执行插入语句成功");
			
			sql = "delete from hero where id = 5";
			s.execute(sql);
			System.out.println("执行删除语句成功");
			
			sql = "update hero set name='name 5' where id = 3";
			s.execute(sql);
			System.out.println("执行更新语句成功");
			
			s.executeUpdate("delete from hero where name like '英雄%'");
			
			for (int i=0; i<20; i++) {
				sql = "insert into hero values(null,'英雄"+(i+1)+"',"+(313+1)+","+(50+i)+")";
				s.execute(sql);
			}
			System.out.println("插入成功");
			
			StringBuilder sb = new StringBuilder();
			ResultSet rs = s.executeQuery("select * from hero where name like 'hero%'");
			while (rs.next()) {
				int id = rs.getInt("id");
				String name = rs.getString(2);
				float hp = rs.getFloat("hp");
				int damage = rs.getInt(4);
				sb.append(name).append(',');
			}
			System.out.println(sb.toString());
			
			if (s.execute("select count(*) from hero")) {//返回值为true，表示执行的是查询语句
				rs = s.getResultSet();
				if (rs.next()) {
					System.out.println("[execute] 总记录数 "+rs.getInt(1));
				}
			}
			
			rs = s.executeQuery("select count(*) from hero");
			if (rs.next()) {
				System.out.println("[executeQuery] 总记录数 "+rs.getInt(1));
			}
			
			ps = c.prepareStatement("insert into hero values(null,?,?,?)", Statement.RETURN_GENERATED_KEYS);
			ps.setString(1, "呵呵");
			ps.setFloat(2, 666.6f);
			ps.setInt(3, 66);
			ps.execute();
			rs = ps.getGeneratedKeys();
			if (rs.next()) {
				System.out.println("[getGeneratedKeys] 插入后自动产生的id= "+rs.getInt(1));
			}
			
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
            
            if (ps != null) { try { ps.close(); } catch (Exception e) { e.printStackTrace(); }}
            
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
