package net.myapp.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.stereotype.Service;

import lombok.Setter;
import lombok.extern.log4j.Log4j;
import net.myapp.controller.TranDate;
import net.myapp.domain.BalanceVO;
import net.myapp.domain.MemberVO;
import net.myapp.domain.TransactionParameterVO;
import net.myapp.mapper.AccessDAO;

@Service
@Log4j
@EnableAspectJAutoProxy
public class ManagerServiceImpl implements ManagerService {

	@Autowired
	@Qualifier("accessDAO")
	private AccessDAO dao;

	@Override
	public MemberVO getMember(String id,String passwd) {
		Map<String, String> map = new HashMap<>();
		map.put("id", id);
		map.put("passwd", passwd);
		
		return dao.getMember(map);
	}
	
	

}
