package com.test.java;

import java.sql.Connection;

public class Ex02 {
	public static void main(String[] args) {
		
		//접속시 발생하는 에러
		//1. 서버 주고 X
		//- IO오류: The Network Adapter could not establish the connection
		//2. 아이디X / 암호X
		//- ORA-01017: 사용자명/비밀번호가 부적합, 로그온할 수 없습니다.
		//3. 서버 중지
		//- Listener refused the connection with the following error
		//4. 연결 문자 오타
		//- 사용자명/비밀번호가 부적합, 로그온할 수 없습니다.
		//5. 포트번호 X
		//- IO오류: The Network Adapter could not establish the connection
		//6. SID X
		//- Listener refused the connection with the following error:
		//7. 드라이버 오타
		//- oracle.jdbcdriver.OracleDriver
		//8. ojdbc8.jar 문제
		//- oracle.jdbc.driver.OracleDriver
		
		try {
			//Connection conn = DBUtil.open();
			Connection conn = DBUtil.open("localhost" , "hr2", "java1234");
			
			System.out.println(conn.isClosed());
			
			conn.close();	//faulse

		} catch (Exception e) {
			System.out.println("Ex02.main");
			e.printStackTrace();
		}
	}
}
