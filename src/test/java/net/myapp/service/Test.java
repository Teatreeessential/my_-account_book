package net.myapp.service;


import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import lombok.Setter;
import lombok.extern.log4j.Log4j;
import net.myapp.domain.MemberVO;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"file:src/main/webapp/WEB-INF/spring/root-context.xml"})
@Log4j
public class Test {
	
	@Setter(onMethod_=@Autowired)
	private ManagerService service;
	
	
	@org.junit.Test
	public void test() {
		MemberVO vo = service.getMember("abc", "1111");
		log.info(vo.toString());
	}
}
