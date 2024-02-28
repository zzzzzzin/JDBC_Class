package com.test.main;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBUtil {
	public static Connection open() {
		
		Connection conn = null;
		
		String url = "jdbc:oracle:thin:@localhost:1521:xe";
		String id = "hr";
		String pw = "java1234";
		
		try {
			
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
			conn = DriverManager.getConnection(url, id, pw);
			
			return conn;
		} catch (Exception e) {
			System.out.println("DBUtil.open");
			e.printStackTrace();
		}
		return null;
	}

	public static Connection open(String host, String id, String pw) {
		
		Connection conn = null;
		
		String url = "jdbc:oracle:thin:@" + host + ":1521:xe";
		
		try {
			
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
			conn = DriverManager.getConnection(url, id, pw);
			//conn.setAutoCommit(true);
			
			return conn;
		} catch (Exception e) {
			System.out.println("DBUtil.open");
			e.printStackTrace();
		}
		return null;
	}
	
}
