package com.test.member;

import java.util.Scanner;

import com.test.main.Main;
import com.test.member.model.MemberDTO;
import com.test.member.repository.MemberDAO;

public class MemberMain {
	
	public static String auth; //인증 티켓(id)
	
	public static void start() {
		
		boolean loop = true;
		Scanner scan = new Scanner(System.in);
		
		while (loop) {
		
			System.out.println("*** 회원 ***");
			
			if (MemberMain.auth == null) {
				System.out.println("1. 로그인");
			} else if (MemberMain.auth != null) {
				System.out.println("2. 로그아웃");
			}
			System.out.println("3. 뒤로가기");
			
			System.out.print("선택: ");
			
			String sel = scan.nextLine();
			
			if (sel.equals("1")) {
				login();
			} else if (sel.equals("2")) {
				logout();
			} else {
				loop = false;
			}
		
		}//while
		
	}//start

	private static void logout() {
		
		MemberMain.auth = null;
		
		Main.pause();
		
	}

	private static void login() {
		
		//DB 접속 > 인증 절차
		Scanner scan = new Scanner(System.in);
		
		System.out.println("=== 로그인 ===");
		
		System.out.print("아이디: ");
		String id = scan.nextLine();
		
		System.out.print("암호: ");
		String pw = scan.nextLine();
		
//		Connection
//		Statement
//		SQL
//		execute
//		ResultSet
		
		MemberDAO dao = new MemberDAO();
		
		MemberDTO dto = new MemberDTO();
		dto.setId(id);
		dto.setPw(pw);
		
		String result = dao.login(dto);
		
				
//		인증처리
		
		if (result != null) {
			//로그인 처리 > 인증 티켓 발급
			MemberMain.auth = result;
		} else {
			//로그인 실패
		}
		
		Main.pause();
		
		
	}

}


















