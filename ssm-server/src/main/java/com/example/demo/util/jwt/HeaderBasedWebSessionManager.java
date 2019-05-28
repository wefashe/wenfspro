package com.example.demo.util.jwt;

import org.apache.shiro.web.servlet.ShiroHttpServletRequest;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.apache.shiro.web.session.mgt.WebSessionManager;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.Serializable;

public class HeaderBasedWebSessionManager extends DefaultWebSessionManager implements WebSessionManager {

  @Override
  protected Serializable getSessionId(ServletRequest request, ServletResponse response) {
    Serializable id = super.getSessionId(request, response);
    if (id != null) {
      return id;
    }
    id = getSessionIdFromHeader((HttpServletRequest) request);
    if (id != null) {
      request.setAttribute(ShiroHttpServletRequest.REFERENCED_SESSION_ID, id);
      request.setAttribute(ShiroHttpServletRequest.REFERENCED_SESSION_ID_IS_VALID, Boolean.TRUE);
    }
    return id;
  }

  private Serializable getSessionIdFromHeader(HttpServletRequest request) {
    // JSESSIONID
//    return request.getHeader(getSessionIdName());
    return null;
  }

}
