package com.test.java;

import java.sql.Connection;
import java.sql.Statement;
import java.util.Scanner;

public class Ex03_Statement {
	public static void main(String[] args) {
		/*
			Connection
			- 연결/종료
			
			Statement
			- 모든 SQL 실행하는 역할
			
			Statement 종류
			1. Statement
				- 기본
				
			2. PreparedStatement
				- Statenemt 개량 > 매개 변수 처리 특화
				
			3. CallableStatement
				- Statenemt 개량 > 프로시저 호출 전용
				
		
		*/
//		m1();
//		m2();
//		m3();
//		m4();
//		m5();
		m6();
	}

	private static void m6(){
		
		//UI + SQL + 데이터 사용자 입력 + 실행
		
		//*** 자바의 자료형과 오라클의 자료형은 아무 상관 없음
		Scanner scan = new Scanner(System.in);
		
		System.out.print("이름: ");	//varchar2
		String name = scan.nextLine();
		
		System.out.print("나이: ");	//number
		String age = scan.nextLine();

		System.out.print("성별(m,f): ");	
		String gender = scan.nextLine();
		
		System.out.print("전화번호: ");	
		String tel = scan.nextLine();
		
		System.out.print("주소: ");	
		String address = scan.nextLine();
		address = address.replace("'", "''");
		
		Connection conn = null;
		Statement stat = null;
		
		try {
			//1. 
			conn = DBUtil.open();
			
			if (!conn.isClosed()) {
				
				//2.
				//- 자바는 SQL을 모른다. > SQL을 문자열로 취급(아무 의미 X)
				//String sql = "insert into tblAddress (seq, name ,age, gender, tel, address, regdate) values (seqAddress.nextVal, '"+name+"', "+ age +", '"+gender+"', '"+tel+"', '"+address+"', default)";
				
				String sql = String.format("insert into tblAddress (seq, name ,age, gender, tel, address, regdate) values (seqAddress.nextVal, '%s', %s, '%s', '%s', '%s', default)", name, age, gender, tel, address);
				
				stat= conn.createStatement();
				
				int result = stat.executeUpdate(sql);
				
				if (result == 1) {
					System.out.println("등록 성공");
				} else {
					System.out.println("등록 실패");
				}
				
				stat.close();
				conn.close();
				
			}else {
				System.out.println("DB 접속 실패");
			}
			
		} catch (Exception e) {
			System.out.println("Ex03_Statement.m1");
			e.printStackTrace();
		}
	}

	private static void m5(){
		Connection conn = null;
		Statement stat = null;
		
		try {
			//1. 
			conn = DBUtil.open();
			
			if (!conn.isClosed()) {
				
				String sql = "create table tblAddress(\r\n"
						+ "    seq number primary key,\r\n"
						+ "    name varchar2(30) not null,\r\n"
						+ "    age number(3) not null check(age between 0 and 150),\r\n"
						+ "    gender char(1) not null check(gender in ('m','f')),\r\n"
						+ "    tel varchar2(15) not null,\r\n"
						+ "    address varchar2(300) not null,\r\n"
						+ "    regdate date default sysdate not null\r\n"
						+ ")";
				
				stat= conn.createStatement();
				
				int result = stat.executeUpdate(sql);
				
				if (result == 1) {
					System.out.println("등록 성공");
				} else {
					System.out.println("등록 실패");
				}
				
				stat.close();
				conn.close();
				
			}else {
				System.out.println("DB 접속 실패");
			}
			
		} catch (Exception e) {
			System.out.println("Ex03_Statement.m1");
			e.printStackTrace();
		}
	}

	private static void m4(){
		Connection conn = null;
		Statement stat = null;
		
		try {
			//1. 
			conn = DBUtil.open();
			
			if (!conn.isClosed()) {
				
				String sql = "delete from tblAddress where seq = 2;";
				
				stat= conn.createStatement();
				
				int result = stat.executeUpdate(sql);
				
				if (result == 1) {
					System.out.println("등록 성공");
				} else {
					System.out.println("등록 실패");
				}
				
				stat.close();
				conn.close();
				
			}else {
				System.out.println("DB 접속 실패");
			}
			
		} catch (Exception e) {
			System.out.println("Ex03_Statement.m1");
			e.printStackTrace();
		}
	}

	private static void m3(){
		
		Connection conn = null;
		Statement stat = null;
		
		try {
			//1. 
			conn = DBUtil.open();
			
			if (!conn.isClosed()) {
				
				//2.
				//- 자바는 SQL을 모른다. > SQL을 문자열로 취급(아무 의미 X)
				String sql = "update tblAddress set age = age + 1" + "where seq = 2;";
				
				stat = conn.createStatement();
				
				int result = stat.executeUpdate(sql);
				
				if (result == 1) {
					System.out.println("등록 성공");
				} else {
					System.out.println("등록 실패");
				}
				
				stat.close();
				conn.close();
				
			}else {
				System.out.println("DB 접속 실패");
			}
			
		} catch (Exception e) {
			System.out.println("Ex03_Statement.m1");
			e.printStackTrace();
		}
		
	}

	private static void m2(){
		
		//JDBC
		//1. 기본 > SQL 실행 시 자동으로 커밋이 동반된다. > Auto Commit
		//2. 설정 > 수동 트랜잭션 제어
		
		Connection conn = null;
		Statement stat = null;
		
		try {
			//1. 
			conn = DBUtil.open();
			
			if (!conn.isClosed()) {
				
				//2.
				//- 자바는 SQL을 모른다. > SQL을 문자열로 취급(아무 의미 X)
				String sql = "insert into tblAddress (seq, name ,age, gender, tel, address, regdate) values (seqAddress.nextVal, '하하하', 20, 'm', '010-1234-5678', '서울시 강남구 역삼동 한독빌딩', default)";
				
				stat= conn.createStatement();
				
				//SQL Developer > Ctrl + Enter 실행
				int result = stat.executeUpdate(sql);
				
				
				if (result == 1) {
					System.out.println("등록 성공");
				} else {
					System.out.println("등록 실패");
				}
				
				conn.commit();
				conn.rollback();
				
				//자원 정리
				stat.close();
				conn.close();
				
			}else {
				System.out.println("DB 접속 실패");
			}
			
		} catch (Exception e) {
			System.out.println("Ex03_Statement.m1");
			e.printStackTrace();
		}
		
	}

	private static void m1(){
		
		Connection conn = null;
		Statement stat = null;
		
		try {
			//1. 
			conn = DBUtil.open();
			
			if (!conn.isClosed()) {
				
				//2.
				//- 자바는 SQL을 모른다. > SQL을 문자열로 취급(아무 의미 X)
				String sql = "insert into tblAddress (seq, name ,age, gender, tel, address, regdate) values (seqAddress.nextVal, '아무개', 20, 'm', '010-1234-5678', '서울시 강남구 역삼동 한독빌딩', default)";
				
				stat= conn.createStatement();
				
				//반환값이 없는 쿼리
				//- int stat.executeUpdate(sql)
				
				//반환값이 있는 쿼리
				//- ResultSet.executeUpdate(sql)
				
				//SQL Developer > Ctrl + Enter 실행
				int result = stat.executeUpdate(sql);
				
				
				if (result == 1) {
					System.out.println("등록 성공");
				} else {
					System.out.println("등록 실패");
				}
				
				//자원 정리
				stat.close();
				conn.close();
				
			}else {
				System.out.println("DB 접속 실패");
			}
			
		} catch (Exception e) {
			System.out.println("Ex03_Statement.m1");
			e.printStackTrace();
		}
		
	}
}
















