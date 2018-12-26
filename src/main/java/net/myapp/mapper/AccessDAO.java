package net.myapp.mapper;

import java.util.List;
import java.util.Map;

import net.myapp.domain.BalanceVO;
import net.myapp.domain.MemberVO;

public interface AccessDAO {
	
	public MemberVO getMember(Map<String,String> map);
}
