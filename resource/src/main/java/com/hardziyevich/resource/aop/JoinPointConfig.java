package com.hardziyevich.resource.aop;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

@Aspect
public class JoinPointConfig {

    @Pointcut("@annotation(org.springframework.web.bind.annotation.PostMapping)")
    public void postMappingPointcut() {
    }

    @Pointcut("@annotation(org.springframework.web.bind.annotation.ExceptionHandler)")
    public void errorHandlingControllerPointCut() {
    }
}
