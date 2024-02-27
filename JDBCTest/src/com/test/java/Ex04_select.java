package com.test.java;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Scanner;

public class Ex04_select {
	public static void main(String[] args) {
		
//		m1();
//		m2();
//		m3();
//		m4();
//		m5();	//내가 한거
//		m6();	//강사님 답
//		m7();	//select 오류 메세지
//		m8();	//join할 때의 오류 메세지
		m9();
	}

	private static void m9(){
		Connection conn = null;
		Statement stat = null;
		ResultSet rs = null;
		
		try {
			conn = DBUtil.open();
			stat = conn.createStatement();
			
			//요구사항] 영업부 > 직원수와 직원명단 출력
			//1. select count(*) as cnt from tblInsa where buseo = '영업부';
			//2. select * from tblInsa where buseo = '영업부';
			
			//*** ResultSet의 특징
			// - 커서를 가르키는 레코드의 정보 이외에는 알 수 없다.
			
			
			String sql = "";
			
			sql = "select * from tblInsa where buseo = '영업부'";
			rs = stat.executeQuery(sql);
			
			int count = 0;
			
			while(rs.next()) {
				System.out.println(rs.getString("name"));
				count++;
			}
			
			rs.close();
			
			stat.close();
			conn.close();
			
		} catch (Exception e) {
			System.out.println("Ex04_select.m9");
			e.printStackTrace();
		}
		
		
	}

	private static void m8(){
		
		Connection conn = null;
		Statement stat = null;
		ResultSet rs = null;
		
		try {
			
			conn = DBUtil.open();
			stat = conn.createStatement();
			
			String sql = "select\r\n"
					+ "m.name as mname, v.name as vname \r\n"
					+ "from tblMember m\r\n"
					+ "    inner join tblRent r\r\n"
					+ "        on m.seq = r.member\r\n"
					+ "            inner join tblVideo v\r\n"
					+ "                on v.seq = r.video";
			
			rs = stat.executeQuery(sql);
			
			//누가? 뭘?
			while(rs.next()) {
//				System.out.println("누가: " + rs.getString("m.name"));
//				System.out.println("무엇을: " + rs.getString("v.name"));
				
//				System.out.println("누가: " + rs.getString("1"));
//				System.out.println("무엇을: " + rs.getString("2"));

//				System.out.println("누가: " + rs.getString("name"));
//				System.out.println("무엇을: " + rs.getString("name"));

				System.out.println("누가: " + rs.getString("mname"));
				System.out.println("무엇을: " + rs.getString("vname"));
			}
			
			
			rs.close();
			
			
			stat.close();
			conn.close();
			
			
		} catch (Exception e) {
			System.out.println("Ex04_select.m8");
			e.printStackTrace();
		}
		
		
		
	}

	private static void m7(){
		
		//select > 오류 발생
		Connection conn = null;
		Statement stat = null;
		ResultSet rs = null;
		
		try {
			
			conn = DBUtil.open();
			stat = conn.createStatement();
			
			//ORA-00904: "BUSE": 부적합한 식별자
			//ORA-00942: 테이블 또는 뷰가 존재하지 않습니다.
			String sql = "select name, buseo, jikwi from tblInsa";
			
			rs = stat.executeQuery(sql);
			
			while(rs.next()) {
				//부적합한 열 이름
				System.out.println(rs.getString("name"));
				System.out.println(rs.getString("buseo"));
				System.out.println(rs.getString("jikwi"));
				System.out.println();
			}
			
			
			
			
			stat.close();
			conn.close();
			
		} catch (Exception e) {
			System.out.println("Ex04_select.m7");
			e.printStackTrace();
		}
		
	}

	private static void m6(){
		Scanner scan = new Scanner(System.in);
		Connection conn = null;
		Statement stat = null;
		ResultSet rs = null;
		
		try {
			
			conn = DBUtil.open();
			stat = conn.createStatement();
			
			//1. 직원번호
			String sql = "select num, name, buseo, jikwi from tblInsa order by num";
			
			rs = stat.executeQuery(sql);
			
			System.out.println("[번호]\t[이름]\t[부서]\t[직위]");
			
			while(rs.next()) {
				//rs > 가르키는 레코드 > 직원 1명씩
				System.out.printf("%s\t%s\t%s\t%s\n"
									,rs.getString("num")
									,rs.getString("name")
									,rs.getString("buseo")
									,rs.getString("jikwi"));
			}
			rs.close();
			System.out.println();
			
			//2.
			System.out.print("직원 번호: ");
			String num = scan.nextLine();
			
			//3.
			System.out.print("보너스 금액: ");
			String bonus = scan.nextLine();
			
			//4.
			sql = String.format("insert into tblBonus(seq, num, bonus) values (seqBonus.nextValm %s, %s)", num, bonus);
			
			int result = stat.executeUpdate(sql);
			
			//5. 직원번호, 이름, 부서, 직위, 보너스 금액
			System.out.println();
			sql = "select i.num, i.name, i.buseo, i.jikwi, b.bonus from tblBonus b inner join tblInsa i on b.num = i.num order by num";
			
			rs = stat.executeQuery(sql);
			
			System.out.println("[번호]\t[이름]\t[부서]\t[직위]\t[보너스]");
			
			while(rs.next()) {
				System.out.printf("%s\t%s\t%s\t%s\t%,10d원\n"
									, rs.getString("num")
									, rs.getString("name")
									, rs.getString("buseo")
									, rs.getString("jikwi")
									, rs.getInt("bonus"));
			}
			
			System.out.println("종료");
			
			stat.close();
			conn.close();
			
			
		} catch (Exception e) {
			System.out.println("Ex04_select.m6");
			e.printStackTrace();
		}
		
	}

	private static void m5(){
		
		//tblInsa + tblBonus
		//요구사항] 특정 직원에게 보너스를 지급하시오.
		//1. 모든 직원 명단을 출력(직원번호, 이름, 부서, 직위) > m4 참조	>> select tblInsa
		//2. 사용자 > 직원 선택(직원 번호 입력)	>> scan.nextLine
		//3. 사용자 > 보너스 금액 입력	>> scan.nextLine
		//4. 보너스 지급 > insert tblBonus...	>> insert tblBonus
		//5. 지급된 내역을 출력(tblBonus > 직원번호, 이름, 부서, 직위, 보너스 금액 > join)	>> select tblBonus
		Scanner scan = new Scanner(System.in);
		Connection conn = null;
		Statement stat = null;
		ResultSet rs = null;
		
		try {
			
			conn = DBUtil.open();
			stat = conn.createStatement();
			
			String sql = "select num, name, buseo, jikwi from tblInsa";
			rs = stat.executeQuery(sql);
		
			
			System.out.println("[번호]\t[이름]\t[부서]\t[직위]");
			while(rs.next()) {
				System.out.printf("%d\t%s\t%s\t%s\t\n"
						, rs.getInt("num")
						, rs.getString("name")
						, rs.getString("buseo")
						, rs.getString("jikwi"));
			}
			
			System.out.print("보너스를 지급할 번호: ");
			int sel = scan.nextInt();
			
			sql = String.format("select num from tblInsa where num = '%d'", sel);
			
			System.out.print("보너스 금액 입력: ");
			int bonus = scan.nextInt();
			
			sql = String.format("update tblBonus set bonus = bonus + %d" + "where num = %d", bonus, sel);
			stat = conn.createStatement();
			
			int result = stat.executeUpdate(sql);
			
			sql = "select i.num, i.name, i.buseo, i.jikwi, b.bonus from tblInsa i inner join tblBonus b "
					+ "on i.num = b.num";
			rs = stat.executeQuery(sql);
			System.out.println("[번호]\t[이름]\t[부서]\t[직위]\t[보너스]");
			
			while(rs.next()) {
				System.out.printf("%d\t%s\t%s\t%s\t%d\n"
						, rs.getInt("num")
						, rs.getString("name")
						, rs.getString("buseo")
						, rs.getString("jikwi")
						, rs.getInt("bonus"));
			}
			stat.close();
			conn.close();

		} catch (Exception e) {
			System.out.println("Ex04_select.m5");
			e.printStackTrace();
		}
		
	}

	private static void m4(){
		//부서 입력 > 직원 명단 출력
		
		Scanner scan = new Scanner(System.in);
		//System.out.print("부서명: ");
		//String buseo = scan.nextLine();
		
		Connection conn = null;
		Statement stat = null;
		ResultSet rs = null;
		
		try {
			
			conn = DBUtil.open();
			stat = conn.createStatement();
			
			String sql = "select distinct buseo from tblInsa order by buseo asc";
			
			rs = stat.executeQuery(sql);
			
			System.out.println("보고싶은 부서를 선택하세요.");
			ArrayList<String> blist = new ArrayList<String>();
			
			int n = 1;
			while(rs.next()) {
				System.out.printf("%d. %s\n", n, rs.getString("buseo"));
				n++;
				blist.add(rs.getString("buseo"));
			}
			System.out.print("선택(번호): ");
			int sel = scan.nextInt();
			
			String buseo = blist.get(sel - 1);
			
			
			sql = String.format("select * from tblInsa where buseo = '%s'", buseo);
			
			rs = stat.executeQuery(sql);
			System.out.println("[이름]\t[직위]\t[지역]\t[급여]");
			
			while (rs.next()) {
				//레코드 1줄 = 직원 1명
				System.out.printf("%s\t%s\t%s\t%,10d원\n"
									, rs.getString("name")
									, rs.getString("jikwi")
									, rs.getString("city")
									, rs.getInt("basicpay"));
			}
			
			stat.close();
			conn.close();
			
		} catch (Exception e) {
			System.out.println("Ex04_select.m4");
			e.printStackTrace();
		}
		
		
	}

	private static void m3(){
		//다중값 반환
		//- 다중 레코드
		//- N행 1열
		Connection conn = null;
		Statement stat = null;
		ResultSet rs = null;
		
		try {
			
			conn = DBUtil.open();
			stat = conn.createStatement();
					
			String sql = "select name from tblInsa order by name asc";
			
			//60명
			rs = stat.executeQuery(sql);	//BOF
			
			//ResultSet.next가 호출되지 않았음
			//System.out.println(rs.getString("name"));
			rs.next();
			System.out.println(rs.getString("name"));	//고순정
			System.out.println(rs.getString("name"));	//고순정
			
			rs.next();
			System.out.println(rs.getString("name"));	//권영미
			
			while (rs.next()) {
				System.out.println(rs.getString("name"));	//tblInsa의 모든 name(고순정, 권영미 제외)
			}
			
			stat.close();
			conn.close();
			
		} catch (Exception e) {
			System.out.println("Ex04_select.m3");
			e.printStackTrace();
		}
		
	}

	private static void m2(){
		
		//다중값 반환
		//- 1행 N열
		Connection conn = null;
		Statement stat = null;
		ResultSet rs = null;
		
		try {
			
			conn = DBUtil.open();
			stat = conn.createStatement();
			
			String sql = "select * from tblAddress whre seq = 5";
			
			rs = stat.executeQuery(sql);
			
			if (rs.next()) {
				
				String name = rs.getString("name");
				String age = rs.getString("age");
				String address = rs.getString("address");
				
				
			} else {
				System.out.println("5번 데이터가 없습니다.");
			}
			
			stat.close();
			conn.close();

		} catch (Exception e) {
			System.out.println("Ex04_select.m2");
			e.printStackTrace();
		}
		
	}

	private static void m1(){
		//단일값 반환
		//- 1행 1열
		Connection conn = null;
		Statement stat = null;
		ResultSet rs = null;
		
		try {
			
			conn = DBUtil.open();
			stat = conn.createStatement();
			
			String sql = "select count(*) as cnt from tblAddress";
			
			rs = stat.executeQuery(sql);
			
			//ResultSet == 커서(Cursor) > 판독기 == 스트림, Iterator, 향상된 for문
			rs.next();	//커서 1줄 전진
			
			//현재 커서가 가르키고 있는 레코드의 원하는 컬럼을 가져오기
			//- rs.getXXX()
			
			//int count = rs.getInt(1);	//컬럼 순서
			//int count = rs.getInt("cnt");	//컬럼명
			String count = rs.getString("cnt"); 
			
			System.out.println(count);
			
			stat.close();
			conn.close();
			
		} catch (Exception e) {
			System.out.println("Ex04_select.m1");
			e.printStackTrace();
		}
		
	}
}
