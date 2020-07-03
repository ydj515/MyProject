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
public class CrawlingAOP {

	private static final Logger logger = LoggerFactory.getLogger(CrawlingAOP.class);

	@Pointcut("execution(* com.example.project.crawler.Crawler.run(..))")
	public void crawlingLog() {
	}

	@Before("crawlingLog()")
	public void beforeControllerLog() {
		logger.info("=== 크롤링 시작 ===");
	}

	@After("crawlingLog()")
	public void afterControllerLog() {
		logger.info("=== 크롤링 종료 ===");
	}

	@AfterReturning("crawlingLog()")
	public void afterReturningSuccess() {
		logger.info("=== 크롤링 성공적 종료 ===");
	}

	@AfterThrowing("crawlingLog()")
	public void afterReturningFail() {
		logger.info("=== DB Insert 실패 ===");
	}

}
