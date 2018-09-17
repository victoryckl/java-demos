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

			//�鿴���ݿ�����Ԫ����
			//�����ݿ�������汾�������汾��������Щ���ݿ�ȵ�
			DatabaseMetaData dbmd = c.getMetaData();
			System.out.println("���ݿ��Ʒ���ƣ�\t"+dbmd.getDatabaseProductName());
			System.out.println("���ݿ��Ʒ�汾��\t"+dbmd.getDatabaseProductVersion());
			System.out.println("���ݿ�ͱ�ָ�����\t"+dbmd.getCatalogSeparator());
			System.out.println("�����汾��\t"+dbmd.getDriverVersion());
			System.out.println("���õ����ݿ��б�\t");
			ResultSet rs = dbmd.getCatalogs();
			while (rs.next()) {
				System.out.println("���ݿ����ƣ�\t"+rs.getString(1));
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
