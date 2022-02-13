package com.hardziyevich.resource.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;
import java.util.Map;

@Aspect
@Configuration
public class LoggerAspect {

    private final Logger logger = LoggerFactory.getLogger(LoggerAspect.class);

    @Around("com.hardziyevich.resource.aop.JoinPointConfig.postMappingPointcut()")
    public Object loggerForController(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        Arrays.stream(proceedingJoinPoint.getArgs()).forEach(a -> logger.info("Request to controller is {}", a.toString()));
        Object request = proceedingJoinPoint.proceed();
        logger.info("Return object from controller is {}", request.toString());
        return request;
    }

    @AfterReturning(value = "com.hardziyevich.resource.aop.JoinPointConfig.errorHandlingControllerPointCut()",
            returning = "errors")
    public void loggerForControllerAdvice(Map<String,String> errors) {
        errors.forEach((k,v) -> logger.info("Return invalid result: field name {} , error {}",k,v));
    }
}
