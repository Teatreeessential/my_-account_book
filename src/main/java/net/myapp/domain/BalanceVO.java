package net.myapp.domain;

import lombok.Data;

@Data
public class BalanceVO {
	private String balance_amt;
	private String available_amt;
	private String account_type;
	private String product_name;
		
}
