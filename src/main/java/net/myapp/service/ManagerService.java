package net.myapp.service;

import java.util.List;

import org.springframework.stereotype.Service;

import net.myapp.domain.BalanceVO;
import net.myapp.domain.MemberVO;
import net.myapp.domain.TransactionParameterVO;

@Service
public interface ManagerService {
	public MemberVO getMember(String id,String passwd);
}
