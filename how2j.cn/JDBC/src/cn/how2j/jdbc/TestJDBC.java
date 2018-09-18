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
			System.out.println("���ݿ��������سɹ���");
			
            // ���������ݿ��Connection����
            // ������Ҫ�ṩ��
            // ���ݿ������ڵ�ip:127.0.0.1 (����)
            // ���ݿ�Ķ˿ںţ� 3306 ��mysqlר�ö˿ںţ�
            // ���ݿ����� how2java
            // ���뷽ʽ UTF-8
            // �˺� root
            // ���� 123456
			c = DriverManager.getConnection(
					"jdbc:mysql://127.0.0.1:3306/how2java?characterEncoding=UTF-8",
					"root","123456");
			System.out.println("���ӳɹ�����ȡ���Ӷ��� " + c);
			
			// ע�⣺ʹ�õ��� java.sql.Statement
            // ��Ҫ��С��ʹ�õ��� com.mysql.jdbc.Statement;
			s = c.createStatement();
			System.out.println("��ȡ Statement���� " + s);
			
			// ׼��sql���
            // ע�⣺ �ַ���Ҫ�õ�����'
			String sql = "insert into hero values(null,'��Ī',313,50)";
			s.execute(sql);
			System.out.println("ִ�в������ɹ�");
			
			sql = "delete from hero where id = 5";
			s.execute(sql);
			System.out.println("ִ��ɾ�����ɹ�");
			
			sql = "update hero set name='name 5' where id = 3";
			s.execute(sql);
			System.out.println("ִ�и������ɹ�");
			
			s.executeUpdate("delete from hero where name like 'Ӣ��%'");
			
			for (int i=0; i<20; i++) {
				sql = "insert into hero values(null,'Ӣ��"+(i+1)+"',"+(313+1)+","+(50+i)+")";
				s.execute(sql);
			}
			System.out.println("����ɹ�");
			
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
			
			if (s.execute("select count(*) from hero")) {//����ֵΪtrue����ʾִ�е��ǲ�ѯ���
				rs = s.getResultSet();
				if (rs.next()) {
					System.out.println("[execute] �ܼ�¼�� "+rs.getInt(1));
				}
			}
			
			rs = s.executeQuery("select count(*) from hero");
			if (rs.next()) {
				System.out.println("[executeQuery] �ܼ�¼�� "+rs.getInt(1));
			}
			
			ps = c.prepareStatement("insert into hero values(null,?,?,?)", Statement.RETURN_GENERATED_KEYS);
			ps.setString(1, "�Ǻ�");
			ps.setFloat(2, 666.6f);
			ps.setInt(3, 66);
			ps.execute();
			rs = ps.getGeneratedKeys();
			if (rs.next()) {
				System.out.println("[getGeneratedKeys] ������Զ�������id= "+rs.getInt(1));
			}
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			// ���ݿ������ʱ������Դ����ز������������ɹر����ݿ�ĺ�ϰ��
            // �ȹر�Statement
            if (s != null) {
                try {
                    s.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            
            if (ps != null) { try { ps.close(); } catch (Exception e) { e.printStackTrace(); }}
            
            // ��ر�Connection
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
