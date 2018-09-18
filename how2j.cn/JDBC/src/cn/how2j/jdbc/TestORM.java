package cn.how2j.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class TestORM {

	public static void main(String[] args) {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void add(Hero h) {
		Connection c = null;
		Statement s = null;
		try {
			c = getConnection();
			s = c.createStatement();
			String sql = "insert into hero values(null,'"+h.name+"',"+h.hp+","+h.damage+")";
			s.execute(sql);			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(c, s);
		}
	}
	
	public static void delete(Hero h) {
		Connection c = null;
		Statement s = null;
		try {
			c = getConnection();
			s = c.createStatement();
			String sql = "delete from hero where id="+h.id;
			s.execute(sql);			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(c, s);
		}
	}
	
	public static void update(Hero h) {
		Connection c = null;
		Statement s = null;
		try {
			c = getConnection();
			s = c.createStatement();
			String sql = "update hero set name='"+h.name+"',hp="+h.hp+",damage="+h.damage+" where id="+h.id;
			s.execute(sql);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(c, s);
		}
	}
	
	public static List<Hero> list() {
		Connection c = null;
		Statement s = null;
		List<Hero> heros = new ArrayList<Hero>();
		try {
			c = getConnection();
			s = c.createStatement();
			String sql = "select * from hero";
			ResultSet rs = s.executeQuery(sql);
			while (rs.next()) {
				Hero h = new Hero();
				h.id = rs.getInt("id");
				h.name = rs.getString("name");
				h.hp = rs.getFloat("hp");
				h.damage = rs.getInt("damage");
				heros.add(h);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(c, s);
		}
		return heros;
	}
	
	private static Connection getConnection() throws SQLException {
		return DriverManager.getConnection(
				"jdbc:mysql://localhost:3306/how2java?characterEncoding=UTF-8",
				"root", "123456");
	}
	
	private static void close(Connection c, Statement s) {
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
