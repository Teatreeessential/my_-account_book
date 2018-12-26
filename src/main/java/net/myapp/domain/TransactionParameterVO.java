package net.myapp.domain;

import lombok.Data;

@Data
public class TransactionParameterVO {
	// 기존의 핀테크 이용번호와 토큰 그리고 이 파라미터 값을 추가하면 은행마다 각각의 거래내역이 생성된다.

	private String inquiry_type; // 조회구분코드 A:ALL I:입금 O:출금
	private String from_date; // 조회 시작일자
	private String to_date; // 조회 끝나는 일자
	private String sort_order;// 정렬순서 D 디센딩 A 어센딩
	private String page_index; // 페이지번호
	private String tran_dtime; // 요청일시


}
