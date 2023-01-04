package com.learn.mybatis.aspect;

import com.learn.mybatis.entity.CourseEntity;
import com.learn.mybatis.entity.TeacherEntity;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class GeneralInterceptorAspect {

    // poing cut is the ezpression language
    // this point cut is for the method level execution
    // this is telling that return type to any and number of paramaeter is also the any number
    @Pointcut("execution(* com.learn.mybatis.controller.*.*(..))")
    // this is the point cut for method within the package
//    @Pointcut("within(com.learn.mybatis.mapper.*)")
    // only executed for StudentMapper class
//        @Pointcut("this(com.learn.mybatis.mapper.StudentMapper)")

    // point cut for the annotation
//@Pointcut("@annotation(com.learn.mybatis.annotation.CustomAnnotation)")

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

        @Around("loggingPointCut()")
    public Object aroudAspect(ProceedingJoinPoint joinPoint) throws Throwable{
        System.out.println("Before method invoked:: "+ joinPoint.getSignature());
         System.out.println("after method invoked::" + joinPoint.getSignature());
         // getting object k
         Object o = joinPoint.proceed();

            if(o  instanceof CourseEntity){
                System.out.println("After method invoke ");
            } else  if(o instanceof TeacherEntity){

            }

            return o;
    }

}
