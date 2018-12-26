package net.myapp.simple;


import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;

import lombok.Setter;
import lombok.extern.log4j.Log4j;
import net.myapp.controller.DataHandler;

@Log4j
public class Test {
	

	
	@org.junit.Test
	public void test() {
		Map<String,String> map = new HashMap<String, String>();
		
		DataHandler dh = new DataHandler();
		
		map.put("20161001", "25000000");
		map.put("20180330", "2095000");

		
		System.out.println(dh.sum(map));
		
		dh.sortByValue(map).forEach((n1,n2) -> {
			System.out.println("key:"+n1);
			System.out.println("value:"+n2);
			
		});;
	}
}
