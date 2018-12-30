package net.myapp.domain;

import lombok.Data;
import lombok.Setter;
import net.myapp.controller.DataHandler;

@Data
public class BalanceVO {
	private String fintech_use_num;
	private String bank_code_tran; //은행코드 -> 이미지 파일 위치
	private String bank_name;
	private String balance_amt; //계좌잔액
	private String available_amt; //출금가능금액
	private String account_type; //계좌종료 1:수시입출금 2:예적금 6:수익증권
	private String product_name; //계좌명
	

}
