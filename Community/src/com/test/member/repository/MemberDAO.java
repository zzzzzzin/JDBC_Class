package com.test.member.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import com.test.main.DBUtil;
import com.test.member.model.MemberDTO;


//DAO, Data Access Object
//- 데이터 업무 전문 클래스(DB, File.. 등 모든 데이터 업무)
public class MemberDAO {

	private Connection conn;
	private Statement stat;
	private PreparedStatement pstat;
	private ResultSet rs;
	
	public MemberDAO() {
		conn = DBUtil.open("localhost", "jdbc", "java1234");
	}
	
	public String login(MemberDTO dto) {
		
		try {
			
			String sql = "select * from tblUser where id = ? and pw = ?";
			
			pstat = conn.prepareStatement(sql);
			
			pstat.setString(1, dto.getId());
			pstat.setString(2, dto.getPw());
			
			rs = pstat.executeQuery();
			
			if (rs.next()) {
				//로그인 성공
				return dto.getId();
			}
			
			
		} catch (Exception e) {
			System.out.println("MemberDAO.login");
			e.printStackTrace();
		}
		
		return null;
		
	}
	
}









