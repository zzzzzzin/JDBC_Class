package com.test.member.model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

//DTO, Data Transfer Object
//- 계층간 데이터를 전달하는 용도

@Data	//Getter + Setter + ToString

public class MemberDTO {
	
	private String id;
	private String pw;
	
}
