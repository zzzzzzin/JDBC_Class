package com.test.member.model;

//DTO, Data Transfer Object
//- 계층간 데이터를 전달하는 용도
public class MemberDTO {
	
	private String id;
	private String pw;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPw() {
		return pw;
	}
	public void setPw(String pw) {
		this.pw = pw;
	}
	
}
