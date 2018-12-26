package net.myapp.aop;


import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

import lombok.extern.log4j.Log4j;

@Aspect
@Log4j
public class LoggerAop {

	@Pointcut("execution(public * net.myapp.service..*(...))")
	public void pointCut() {
		
	}
	@Around("pointCut()")
	public void target(ProceedingJoinPoint joinPoint) throws Throwable {
		log.info("========================================");
		log.info("호출된 method:"+ joinPoint.getSignature().toLongString());
		log.info("실제 넘어온 파라미터:" + joinPoint.getArgs().toString());
		log.info("========================================");
		joinPoint.proceed();
		log.info("메서드 호출 성공");
		
	}
	
	
}
