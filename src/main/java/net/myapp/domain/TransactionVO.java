package net.myapp.domain;

import lombok.Data;

@Data
public class TransactionVO {
	//은행계좌를 클릭하면 이런식으로 거래내역을 가져올 수 있음.
	
	    private String tran_time;//거래시간
	    private String tran_date;//거래일자
	    private String inout_type;//입출금구분
	    private String tran_type; //거래구분
	    private String tran_amt; //거래금액
	    private String after_balance_amt; //거래후 잔액
	    private String branch_name; //거래점명
		
	    
	    
	    
		
	    
         
		
}
