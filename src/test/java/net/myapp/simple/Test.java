package net.myapp.simple;


import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;

import lombok.Setter;
import lombok.extern.log4j.Log4j;
import net.myapp.controller.DataHandler;
import net.myapp.domain.BalanceVO;

@Log4j
public class Test {
	
	
	
	@org.junit.Test
	public void test() {
		Map<String,String> map = new HashMap<String, String>();
		
		DataHandler dh = new DataHandler();
		BalanceVO vo = new BalanceVO();
		vo.setAccount_type("1");
		vo.setBank_code_tran("002");
		System.out.println(vo.getAccount_type());
		System.out.println(vo.getBank_code_tran());
		map.put("20161001", "25000000");
		map.put("20180330", "2095000");

		
		System.out.println(dh.bankName(Integer.parseInt("002")));
	}
}
