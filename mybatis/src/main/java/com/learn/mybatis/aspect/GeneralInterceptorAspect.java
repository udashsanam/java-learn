package com.learn.mybatis.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class GeneralInterceptorAspect {

    @Pointcut("execution(* com.learn.mybatis.controller.*.*(..))")
    public void loggingPointCut(){

    }

    @Before("loggingPointCut()")
    public void before(JoinPoint joinPoint){

        System.out.println("Betore method invookedd::" + joinPoint.getSignature());

    }
    @After("loggingPointCut()")
    public void after(JoinPoint joinPoint){
         System.out.println("after method invoked::" + joinPoint.getSignature());
    }

}
