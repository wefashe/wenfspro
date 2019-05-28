package com.example.demo.controller;

import org.springframework.util.StreamUtils;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.*;

@WebFilter(filterName = "myFilter", urlPatterns = "/*")
public class DoFilter implements Filter {
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;
        String taken = httpRequest.getHeader("token");
        ///
//        JWTResult result = JWTUtils.checkToken(token);
        Long userId = null;//= result.getUserId;
        HttpServletRequestWrapper requestWrapper = new HttpServletRequestWrapper(httpRequest) {
            @Override
            public String[] getParameterValues(String name) {
                if (name.equals("userId")) {
                    return new String[]{userId.toString()};
                }
                return super.getParameterValues(name);
            }

            @Override
            public Enumeration<String> getParameterNames() {
                Set<String> parameNames = new LinkedHashSet<>();
                parameNames.add("userId");
                Enumeration<String> names = super.getParameterNames();
                while (names.hasMoreElements()) {
                    parameNames.add(names.nextElement());
                }
                return Collections.enumeration(parameNames);
            }

            //json提交失败
            //只需要在HttpServletRequestWrapper中重新对提交的内容进行修改即可：
            @Override
            public ServletInputStream getInputStream() throws IOException {
                byte[] requestBody = new byte[0];
                try {
                    requestBody = StreamUtils.copyToByteArray(request.getInputStream());
                    Map map = null;//JsonUtils.toBean(Map.class, new String(requestBody));
                    map.put("userId", userId);
                    requestBody = null;//= JsonUtils.toJson(map).getBytes();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                final ByteArrayInputStream bais = new ByteArrayInputStream(requestBody);
                return new ServletInputStream() {
                    @Override
                    public boolean isFinished() {
                        return false;
                    }

                    @Override
                    public boolean isReady() {
                        return true;
                    }

                    @Override
                    public void setReadListener(ReadListener readListener) {

                    }

                    @Override
                    public int read() throws IOException {
                        return bais.read();
                    }
                };
                //到此为止，我们就可以直接将Token解析的用户ID直接注入到参数中了，不用去Header中获取，是不是很方便
            }
        };
        chain.doFilter(httpRequest, httpResponse);
    }
}
