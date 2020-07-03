package com.example.project.aop;

import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class InsertDBAOP {

	private static final Logger logger = LoggerFactory.getLogger(InsertDBAOP.class);

	@Pointcut("execution(* com.example.project.crawler.InsertData.insert(..))")
	public void insertDBLog() {
	}

	@Before("insertDBLog()")
	public void beforeInsertDBLog() {
		logger.info("=== DB insert 시작 ===");
	}

	@After("insertDBLog()")
	public void afterInsertDBLog() {
		logger.info("=== DB insert 종료 ===");
	}

	@AfterReturning("insertDBLog()")
	public void afterInsertDBSuccess() {
		logger.info("=== DB Insert 성공적 완료 ===");
	}

	@AfterThrowing("insertDBLog()")
	public void afterInsertDBFail() {
		logger.info("=== DB Insert 실패 ===");
	}
}
