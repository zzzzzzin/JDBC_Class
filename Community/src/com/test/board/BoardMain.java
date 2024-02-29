package com.test.board;

import java.util.ArrayList;
import java.util.Scanner;

import com.test.board.model.BoardDTO;
import com.test.board.repository.BoardDAO;
import com.test.main.Main;
import com.test.member.MemberMain;

public class BoardMain {

	public static void start() {

		boolean loop = true;
		Scanner scan = new Scanner(System.in);

		while (loop) {

			System.out.println("*** 게시판 ***");

			System.out.println("1. 목록보기");
			System.out.println("2. 글쓰기");
			System.out.println("3. 뒤로가기");

			System.out.print("선택: ");

			String sel = scan.nextLine();

			if (sel.equals("1")) {
				list();
			} else if (sel.equals("2")) {
				add();
			} else {
				loop = false;
			}

		} // while

	}// start

	private static void list() {
		
		BoardDAO dao = new BoardDAO();
		
		ArrayList<BoardDTO> list = dao.list();
		
		System.out.println("[번호]\t[제목]\t[아이디]\t[날짜]");
		
		for (BoardDTO dto : list) {
			
			System.out.printf("%s\t%s\t%s\t%s\n"
								, dto.getSeq()
								, dto.getSubject()
								, dto.getId()
								, dto.getRegdate());
			
		}
		
		Main.pause();		
		
	}

	private static void add() {
		
		Scanner scan = new Scanner(System.in);
		
		System.out.print("제목: ");
		String subject = scan.nextLine();
		
		System.out.print("내용: ");
		String content = scan.nextLine();
		
		BoardDAO dao = new BoardDAO(true);
		
		BoardDTO dto = new BoardDTO();
		
		dto.setSubject(subject);
		dto.setContent(content);
		dto.setId(MemberMain.auth); //로그인 아이디
		
		
		int result = dao.addPoint(MemberMain.auth);
		
		result *= dao.add(dto);
		
		
		
		if (result == 1) {
			System.out.println("글쓰기 성공");
			dao.commit();
		} else {
			System.out.println("글쓰기 실패");
			dao.rollback();
		}
		
		Main.pause();
		
	}

}














