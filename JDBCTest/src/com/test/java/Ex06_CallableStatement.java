package com.test.java;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;

import oracle.jdbc.OracleTypes;

public class Ex06_CallableStatement {
	
	public static void main(String[] args) {
		
//		m1();	//인자값X, 반환값X	
//		m2();	//인자값O, 반환값X
//		m3();	//인자값X, 반환값O
//		m4();	//인자값O, 반환값O
		m5();	//커서를 반환하는 프로시저 호출
		
	}
	private static void m5(){
		Connection conn = null;
		CallableStatement stat = null;
		ResultSet rs = null;
		
		try {
			
			conn = DBUtil.open();
			
			String sql = "{ call procM5(?, ?) }"; 
			
			stat = conn.prepareCall(sql);
			
			stat.setString(1, "영업부");
			stat.registerOutParameter(2, OracleTypes.CURSOR);
			
			stat.executeQuery();          
      			//out > 오라클 커서 == 결과셋 == JDBC ResultSet
			rs = (ResultSet)stat.getObject(2);
			
			while(rs.next()) {
				System.out.println(rs.getString("name"));
			}
			
			stat.close();
			conn.close();
			
		} catch (Exception e) {
			System.out.println("Ex06_CallableStatement.temp");
			e.printStackTrace();
		}
		
	}
	private static void m4(){
		Connection conn = null;
		CallableStatement stat = null;
		ResultSet rs = null;
		
		try {
			
			conn = DBUtil.open();
			
			String sql = "{ call procM4(?, ?, ?, ?, ?) }";
			
			stat = conn.prepareCall(sql);
			
			//in
			stat.setString(1, "1001");
			
			//out
			stat.registerOutParameter(2, OracleTypes.VARCHAR);
			stat.registerOutParameter(3, OracleTypes.VARCHAR);
			stat.registerOutParameter(4, OracleTypes.VARCHAR);
			stat.registerOutParameter(5, OracleTypes.VARCHAR);
			
			stat.executeQuery();
			
			System.out.println(stat.getString(2));
			System.out.println(stat.getString(3));
			System.out.println(stat.getString(4));
			System.out.println(stat.getString(5));
			
			stat.close();
			conn.close();
			
		} catch (Exception e) {
			System.out.println("Ex06_CallableStatement.temp");
			e.printStackTrace();
		}
		
	}
	private static void m3(){
		Connection conn = null;
		CallableStatement stat = null;
		ResultSet rs = null;
		
		try {
			
			conn = DBUtil.open();
			
			String sql = "{ call procM3(?) }";
			
			stat = conn.prepareCall(sql);
			
			//in parameter
			//stat.setString(1, 값);
			
			//out parameter
			stat.registerOutParameter(1, OracleTypes.NUMBER);
			
			rs = stat.executeQuery();
			
			//PLSQL 문에서 인출을 수행할 수 없습니다.: next
			//System.out.println(rs.next());
			
			int count = stat.getInt(1);
			System.out.println(count);
			
			stat.close();
			conn.close();
			
		} catch (Exception e) {
			System.out.println("Ex06_CallableStatement.temp");
			e.printStackTrace();
		}
		
	}
	private static void m2(){
		Connection conn = null;
		CallableStatement stat = null;
		ResultSet rs = null;
		
		try {
			
			conn = DBUtil.open();
			
			String sql = "{ call procM2(?, ?, ?, ?, ?) }";
			
			stat = conn.prepareCall(sql);	
			
			stat.setString(1,  "후후후");
			stat.setString(2,  "22");
			stat.setString(3,  "m");
			stat.setString(4,  "010-3213-3212");
			stat.setString(5,  "서울시");
			
			int result = stat.executeUpdate();
			System.out.println(result);
			
			stat.close();
			conn.close();
			
		} catch (Exception e) {
			System.out.println("Ex06_CallableStatement.temp");
			e.printStackTrace();
		}
		
		
	}
	private static void m1(){
		Connection conn = null;
		CallableStatement stat = null;
		ResultSet rs = null;
		
		try {
			
			conn = DBUtil.open();
			
			String sql = "{ call procM1 }";
			
			stat = conn.prepareCall(sql);	//매개변수 처리 기능 보유
			
			int result = stat.executeUpdate();
			System.out.println(result);

			stat.close();
			conn.close();
			
		} catch (Exception e) {
			System.out.println("Ex06_CallableStatement.temp");
			e.printStackTrace();
		}
	}

	private static void temp() {
		Connection conn = null;
		CallableStatement stat = null;
		ResultSet rs = null;
		
		try {
			
			conn = DBUtil.open();
			

			conn.close();
			
		} catch (Exception e) {
			System.out.println("Ex06_CallableStatement.temp");
			e.printStackTrace();
		}
		
	}
}
