package com.learn.mybatis.aspect;

import com.learn.mybatis.entity.CourseEntity;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class GeneralInterceptorAspect {

    @Pointcut("execution(* com.learn.mybatis.controller.*.*(..))")
    public void loggingPointCut(){

    }

//    @Before("loggingPointCut()")
//    public void before(JoinPoint joinPoint){
//
//        System.out.println("Betore method invookedd::" + joinPoint.getSignature());
//
//    }
//    @After("loggingPointCut()")
//    public void after(JoinPoint joinPoint){
//         System.out.println("after method invoked::" + joinPoint.getSignature());
//    }

    @AfterReturning(value = "execution(* com.learn.mybatis.controller.*.*(..))", returning = "course")
    public void afterReturing(JoinPoint joinPoint, CourseEntity course){
        System.out.println("after method invoked :: " + course.getCourseCode());
    }

    @AfterThrowing(value = "execution(* com.learn.mybatis.controller.*.*(..))", throwing = "exception")
    public void afterReturing(JoinPoint joinPoint, Exception exception){
        System.out.println("after throwing  invoked :: " + exception);
    }

}
