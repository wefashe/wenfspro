package com.example.demo.util.log;

import java.util.Arrays;

import javax.servlet.http.HttpServletRequest;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import lombok.extern.slf4j.Slf4j;

/**
 *
 */
@Slf4j
@Aspect
@Component
public class WebLogAspect {

  ThreadLocal<Long> startTime = new ThreadLocal<>();

  @Pointcut("execution(public * com.example.demo.controller..*.*(..))")
  public void webLog() {
  }

  @Before("webLog()")
  public void doBefore(JoinPoint joinPoint) {
    startTime.set(System.currentTimeMillis());
    // 接收到请求，记录请求内容
    ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
    HttpServletRequest request = attributes.getRequest();
    // 获取类名
    String className = joinPoint.getSignature().getDeclaringTypeName();
    // 获取方法名
    String  methodName = joinPoint.getSignature().getName();
    // 记录下请求内容
    log.info("-----------------------------------");
    log.info("REQUEST: ");
    log.info("URL : " + request.getRequestURL().toString());
    log.info("HTTP_METHOD : " + request.getMethod());
    log.info("IP : " + request.getRemoteAddr());
    log.info("CLASS_METHOD : " + className + "." + methodName);
    log.info("ARGS : " + Arrays.toString(joinPoint.getArgs()));

  }


  @AfterThrowing(throwing="e",pointcut = "webLog()")
  public void afterException(Exception e){
    log.info("TIME : " + (System.currentTimeMillis() - startTime.get())+"ms");
    log.info("EXCEPTION : " + e.toString());
  }


  @AfterReturning(returning = "result", pointcut = "webLog()")
  public void doAfterReturning(Object result) {
    log.info("TIME : " + (System.currentTimeMillis() - startTime.get())+"ms");
    log.info("RESPONSE : " +result);
  }

}
