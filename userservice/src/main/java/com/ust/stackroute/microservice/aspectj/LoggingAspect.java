package com.ust.stackroute.microservice.aspectj;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.ust.stackroute.microservice.exception.UserAlreadyExistsException;
import com.ust.stackroute.microservice.exception.UserNotFoundException;
import com.ust.stackroute.microservice.model.User;

/* Annotate this class with @Aspect and @Component */
@Aspect
@Component
public class LoggingAspect {
	/*
	 * Write loggers for each of the methods of User controller, any particular
	 * method will have all the four aspectJ annotation
	 * (@Before, @After, @AfterReturning, @AfterThrowing).
	 */
	
	private static final Logger logger = LoggerFactory
			.getLogger(LoggingAspect.class);
	@Before("execution (* com.ust.stackroute.microservice.controller.UserController.*(..))")
	public void logBeforeMethod(JoinPoint jp) {
		// logger.info("Execution of Before Advice "+jp.getTarget().getClass());
		logger.info(
				"Execution of Before Advice " + jp.getSignature().getName());

	}

	@After("execution (* com.ust.stackroute.microservice.controller.UserController.*(..))")
	public void logAfterMethod(JoinPoint jp) {
		// logger.info("Execution of After Advice "+jp.getTarget().getClass());
		logger.info("Execution of After Advice " + jp.getSignature().getName());
	}
	@AfterReturning(pointcut = "execution (* com.ust.stackroute.microservice.controller.*.*(..))", returning = "result")
	// @AfterReturning( pointcut = "execution(*
	// com.stackroute.keepnote.controller.*.*(..))",returning= "result")
	public void logAfterReturning(JoinPoint jp, Object result) {

		// logger.info("Execution of After Returning Advice
		// "+jp.getTarget().getClass());
		// logger.info("Execution of After Returning Advice
		// "+jp.getSignature().getName());
		logger.info("Execution of After Returning Advice Method Return Values "
				+ result);
	}
	// @AfterThrowing("execution (*
	// com.stackroute.keepnote.controller.*.*(..))")
	@AfterThrowing(pointcut = "execution (* com.ust.stackroute.microservice.controller.*.*(..))", throwing = "error")
	public void logAfterThrowing(JoinPoint jp, Throwable error) {

		// logger.info("Execution of After throwing Advice
		// "+jp.getTarget().getClass());
		// logger.info("Execution of After throwing Advice
		// "+jp.getSignature().getName());
		logger.info("Execution of After throwing Exception  " + error);
	}

}
