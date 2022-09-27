package dev.spring.simpleCrud.log;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class ControllerLogger implements ApplicationLogger{

    @Pointcut("execution(* dev.spring.simpleCrud.controller.*.*(..))")
    public void controllerLoggerPointCut(){}


    @Override
    @Around("controllerLoggerPointCut()")
    public Object log(ProceedingJoinPoint joinPoint) throws Throwable {
        ObjectMapper mapper = new ObjectMapper();
        String method = getMethodName(joinPoint);
        String clazz  = getClassName(joinPoint);
        String args = mapper.writeValueAsString(getMethodArgs(joinPoint));

        logger.info("{}.{} has been hit with arguments: {}", clazz,method, args);
        Object o = joinPoint.proceed();
        logger.info("Response from {}.{}: {}",clazz,method,mapper.writeValueAsString(o));

        return o;
    }
}
