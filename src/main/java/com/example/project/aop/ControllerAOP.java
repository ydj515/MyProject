package com.example.project.aop;

import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class ControllerAOP {

	private static final Logger logger = LoggerFactory.getLogger(ControllerAOP.class);

	@Pointcut("execution(* com.example.project.controller.*.*(..))")
	public void controllerLog() {
	}

	@Before("controllerLog()")
	public void beforeControllerLog() {
		logger.info("=== 시작 ===");
	}

	@After("controllerLog()")
	public void afterControllerLog() {
		logger.info("=== 종료 ===");
	}

	@AfterReturning("controllerLog()")
	public void afterReturningSuccess() {
		logger.info("=== 성공 ===");
	}

}
