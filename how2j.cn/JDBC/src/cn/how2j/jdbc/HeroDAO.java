package cn.how2j.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class HeroDAO implements DAO {
	
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
	
	private void close(Connection c, Statement s) {
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
	
	public int getTotal() {
		int total = 0;
		Connection c = null;
		Statement s = null;
		try {
			c = getConnection();
			s = c.createStatement();
			ResultSet rs = s.executeQuery("select count(*) form hero");
			if (rs.next()) {
				total = rs.getInt(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(c, s);
		}
		return total;
	}

	@Override
	public void add(Hero hero) {
		Connection c = null;
		PreparedStatement s = null;
		try {
			c = getConnection();
			s = c.prepareStatement("insert into hero values(null,?,?,?)");
			s.setString(1, hero.name);
			s.setFloat(2, hero.hp);
			s.setInt(3, hero.damage);
			s.execute();
			
			ResultSet rs = s.getGeneratedKeys();
			if (rs.next()) {
				int id = rs.getInt(1);
				hero.id = id;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(c, s);
		}
	}

	@Override
	public void update(Hero hero) {
		Connection c = null;
		PreparedStatement s = null;
		try {
			c = getConnection();
			s = c.prepareStatement("update hero set name=?, hp=?, damage=? where id=?");
			s.setString(1, hero.name);
			s.setFloat(2, hero.hp);
			s.setInt(3, hero.damage);
			s.setInt(4, hero.id);
			s.execute();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(c, s);
		}
	}

	@Override
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
			close(c, s);
		}
	}

	@Override
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
				hero.id = id;
				hero.name = rs.getString(2);
				hero.hp = rs.getFloat(3);
				hero.damage = rs.getInt(4);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(c, s);
		}
		return hero;
	}

	@Override
	public List<Hero> list() {
		return list(0, Short.MAX_VALUE);
	}

	@Override
	public List<Hero> list(int start, int count) {
		List<Hero> heros = new ArrayList<Hero>();
		Connection c = null;
		PreparedStatement s = null;
		try {
			c = getConnection();
			s = c.prepareStatement("select * from hero order by id desc limit ?,?");
			s.setInt(1, start);
			s.setInt(2, count);
			ResultSet rs = s.executeQuery();
			while(rs.next()) {
				Hero h = new Hero();
				h.id = rs.getInt(1);
				h.name = rs.getString(2);
				h.hp = rs.getFloat(3);
				h.damage = rs.getInt(4);
				heros.add(h);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(c, s);
		}
		return heros;
	}

}
