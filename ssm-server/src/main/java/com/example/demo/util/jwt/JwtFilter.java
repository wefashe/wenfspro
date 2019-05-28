package com.example.demo.util.jwt;

import org.apache.shiro.web.filter.authc.BasicHttpAuthenticationFilter;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * shiro过滤器
 *
 * @author wenfs
 * @create 2018-09-30 14:36
 **/
public class JwtFilter extends BasicHttpAuthenticationFilter {

  /**
   * 登录标识
   */
  private static String LOGIN_SIGN = "Authorization";

  /**
   * 检测用户是否已登录
   * 检测header里面是否包含Authorization字段即可
   *
   * @param request
   * @param response
   * @return
   */
  @Override
  protected boolean isLoginAttempt(ServletRequest request, ServletResponse response) {

    HttpServletRequest req = (HttpServletRequest) request;

    String authorization = req.getHeader(LOGIN_SIGN);

    return authorization != null;

  }



  /**
   * 执行登录认证
   *
   * @param request
   * @param response
   * @param mappedValue
   * @return
   */
  @Override
  protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) {

    if (isLoginAttempt(request, response)) {
      try {
        executeLogin(request, response);
      } catch (Exception e) {
        return false;
//        throw new TSharkException("登录权限不足！", e);
      }
    }
    return true;

  }

  /**
   * 登录验证
   *
   * @param request
   * @param response
   * @return
   * @throws Exception
   */
  @Override
  protected boolean executeLogin(ServletRequest request, ServletResponse response) throws Exception {

    HttpServletRequest httpServletRequest = (HttpServletRequest) request;

    String token = httpServletRequest.getHeader(LOGIN_SIGN);

    JwtToken jwtToken = new JwtToken(token);
    // 提交给realm进行登入，如果错误他会抛出异常并被捕获
    getSubject(request, response).login(jwtToken);
    // 如果没有抛出异常则代表登入成功，返回true
    return true;
  }



  /**
   * 对跨域提供支持
   *
   * @param request
   * @param response
   * @return
   * @throws Exception
   */
  @Override
  protected boolean preHandle(ServletRequest request, ServletResponse response) throws Exception {
    HttpServletRequest httpServletRequest = (HttpServletRequest) request;
    HttpServletResponse httpServletResponse = (HttpServletResponse) response;
    httpServletResponse.setHeader("Access-control-Allow-Origin", httpServletRequest.getHeader("Origin"));
    httpServletResponse.setHeader("Access-Control-Allow-Methods", "GET,POST,OPTIONS,PUT,DELETE");
    httpServletResponse.setHeader("Access-Control-Allow-Headers", httpServletRequest.getHeader("Access-Control-Request-Headers"));
    // 跨域时会首先发送一个option请求，这里我们给option请求直接返回正常状态
    if (httpServletRequest.getMethod().equals(RequestMethod.OPTIONS.name())) {
      httpServletResponse.setStatus(HttpStatus.OK.value());
      return false;
    }
    return super.preHandle(request, response);
  }

}
