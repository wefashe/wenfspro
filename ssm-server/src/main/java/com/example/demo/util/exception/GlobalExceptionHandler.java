package com.example.demo.util.exception;

import com.baomidou.kaptcha.exception.*;
import com.example.demo.entity.SysUser;
import com.example.demo.util.shiro.TokenManager;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.ShiroException;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.DisabledAccountException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.web.util.WebUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.demo.util.enums.MyExceptionEnums;
import com.example.demo.util.result.Result;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.servlet.ServletException;
import javax.validation.ConstraintViolationException;
import javax.validation.ValidationException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 */
@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {


    @ExceptionHandler(MyException.class)
    @ResponseBody
    public Result myExcepitonHandler(MyException e) {
        return new Result(e);
    }

    /**
     * shiro异常处理
     *
     * @param shiroException
     * @return
     */
    @ExceptionHandler(ShiroException.class)
    @ResponseBody
    public Result shiroExcepitonHandler(ShiroException shiroException) {
        Result result;
        if (shiroException instanceof UnknownAccountException) {
            result = new Result(MyExceptionEnums.USER_NOT_EXISTS);
        } else if (shiroException instanceof DisabledAccountException) {
            result = new Result(MyExceptionEnums.USER_STOP_USING);
        } else if (shiroException instanceof IncorrectCredentialsException) {
            result = new Result(MyExceptionEnums.PASSWORD_ERROR);
        } else {
            SysUser user = TokenManager.getToken();
            result = new Result(shiroException);
            log.error("用户【" + user.getUserName() + "】登陆异常", shiroException);
        }
        return result;
    }


    /**
     * kaptcha异常处理
     *
     * @param kaptchaException
     * @return
     */
    @ExceptionHandler(KaptchaException.class)
    @ResponseBody
    public Result kaptchaExcepitonHandler(KaptchaException kaptchaException) {
        Result result;
        if (kaptchaException instanceof KaptchaNotFoundException) {
            result = new Result(MyExceptionEnums.KAPTCHA_NOTFOUND);
        } else if (kaptchaException instanceof KaptchaRenderException) {
            result = new Result(MyExceptionEnums.KAPTCHA_RENDERFAIL);
        } else if (kaptchaException instanceof KaptchaIncorrectException) {
            result = new Result(MyExceptionEnums.KAPTCHA_INCORRECT);
        } else if (kaptchaException instanceof KaptchaTimeoutException) {
            result = new Result(MyExceptionEnums.KAPTCHA_TIMEOUT);
        } else {
            result = new Result(kaptchaException);
            log.error("验证码异常", kaptchaException);
        }
        return result;
    }

    /**
     * servlet异常处理
     *
     * @param ServletException
     * @return
     */
    @ExceptionHandler(ServletException.class)
    @ResponseBody
    public Result servletExceptionHandler(ServletException ServletException) {
        Result result;
        if (ServletException instanceof NoHandlerFoundException) {
            result = new Result(MyExceptionEnums.REQUEST_METHOD_NO_FOUND);
        } else {
            result = new Result(ServletException);
            log.error("servlet异常", ServletException);
        }
        return result;
    }

    /**
     * runtime异常处理
     *
     * @param runtimeException
     * @return
     */
    @ExceptionHandler(RuntimeException.class)
    @ResponseBody
    public Result runtimeExcepitonHandler(RuntimeException runtimeException) {
        log.error("运行时异常", runtimeException);
        return new Result(runtimeException);
    }

}
