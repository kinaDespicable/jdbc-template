package dev.spring.simpleCrud.log;

import org.aspectj.lang.ProceedingJoinPoint;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public interface ApplicationLogger {

    Logger logger = LoggerFactory.getLogger(ApplicationLogger.class);

    Object log(ProceedingJoinPoint joinPoint) throws Throwable;

    default String getMethodName(ProceedingJoinPoint joinPoint){
        return joinPoint.getSignature().getName();
    }
    default String getClassName(ProceedingJoinPoint joinPoint){
        return joinPoint.getClass().toString();
    }

    default Object[] getMethodArgs(ProceedingJoinPoint joinPoint){
        return joinPoint.getArgs();
    }
}
