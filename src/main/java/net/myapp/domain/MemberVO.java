package net.myapp.domain;

import java.util.List;

import lombok.Data;

@Data
public class MemberVO {
	private String id;
	private String passwd;
	private String sex;
	private String access_token; 
	private List<String> fintech_use_nums;// 엑세스토큰 헤더에 추가 해줘야함 테이블에 생성


	
}
