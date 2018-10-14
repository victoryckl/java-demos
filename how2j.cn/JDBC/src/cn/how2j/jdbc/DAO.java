package cn.how2j.jdbc;

import java.util.List;

public interface DAO {
	void add(Hero hero);
	void update(Hero hero);
	void delete(int id);
	Hero get(int id);
	List<Hero> list();
	List<Hero> list(int start, int count);
}
