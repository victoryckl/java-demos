package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import bean.Hero;

public class HeroDAO {
	public HeroDAO() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	public Connection getConnection() throws SQLException {
		return DriverManager.getConnection(
				"jdbc:mysql://localhost:3306/how2java?characterEncoding=UTF-8",
				"root", "123456");
	}
	
	public int getTotal() {
		int total = 0;
		Connection c = null;
		Statement s = null;
		try {
			c = getConnection();
			s = c.createStatement();
			ResultSet rs = s.executeQuery("select count(*) from hero");
			if (rs.next()) {
				total = rs.getInt(1);
			}
			System.out.println("total="+total);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (s != null) { try { s.close(); } catch (SQLException e) {} }
			if (c != null) { try { c.close(); } catch (SQLException e) {} }
		}
		return total;
	}
	
	public void add(Hero hero) {
		Connection c = null;
		PreparedStatement s = null;
		try {
			c = getConnection();
			s = c.prepareStatement("insert into hero values(null,?,?,?)", Statement.RETURN_GENERATED_KEYS);
			s.setString(1, hero.getName());
			s.setFloat(2, hero.getHp());
			s.setInt(3, hero.getDamage());
			s.execute();
			ResultSet rs = s.getGeneratedKeys();
			if (rs.next()) {
				int id = rs.getInt(1);
				hero.setId(id);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (s != null) { try { s.close(); } catch (SQLException e) {} }
			if (c != null) { try { c.close(); } catch (SQLException e) {} }
		}
	}
	
	public void update(Hero hero) {
		Connection c = null;
		PreparedStatement s = null;
		try {
			c = getConnection();
			s = c.prepareStatement("update hero set name=?, hp=?, damage=? where id=?");
			s.setString(1, hero.getName());
			s.setFloat(2, hero.getHp());
			s.setInt(3, hero.getDamage());
			s.setInt(4, hero.getId());
			s.execute();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (s != null) { try { s.close(); } catch (SQLException e) {} }
			if (c != null) { try { c.close(); } catch (SQLException e) {} }
		}
	}
	
	public void delete(int id) {
		Connection c = null;
		Statement s = null;
		try {
			c = getConnection();
			s = c.createStatement();
			s.execute("delete from hero where id="+id);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (s != null) { try { s.close(); } catch (SQLException e) {} }
			if (c != null) { try { c.close(); } catch (SQLException e) {} }
		}
	}
	
	public Hero get(int id) {
		Hero hero = null;
		Connection c = null;
		Statement s = null;
		try {
			c = getConnection();
			s = c.createStatement();
			ResultSet rs = s.executeQuery("select * from hero where id="+id);
			if (rs.next()) {
				hero = new Hero();
				hero.setId(rs.getInt(1));
				hero.setName(rs.getString(2));
				hero.setHp(rs.getFloat(3));
				hero.setDamage(rs.getInt(4));
			}			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (s != null) { try { s.close(); } catch (SQLException e) {} }
			if (c != null) { try { c.close(); } catch (SQLException e) {} }
		}
		return hero;
	}
	
	public List<Hero> list() {
		return list(0, Short.MAX_VALUE);
	}
	
	public List<Hero> list(int start, int count) {
		List<Hero> list = new ArrayList<Hero>();
		Connection c = null;
		PreparedStatement s = null;
		try {
			c = getConnection();
			s = c.prepareStatement("select * from hero order by id desc limit ?,?", Statement.RETURN_GENERATED_KEYS);
			s.setInt(1, start);
			s.setInt(2, count);
			ResultSet rs = s.executeQuery();
			while (rs.next()) {
				Hero hero = new Hero();
				hero.setId(rs.getInt(1));
				hero.setName(rs.getString(2));
				hero.setHp(rs.getFloat(3));
				hero.setDamage(rs.getInt(4));
				list.add(hero);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (s != null) { try { s.close(); } catch (SQLException e) {} }
			if (c != null) { try { c.close(); } catch (SQLException e) {} }
		}
		return list;
	}
}
