package com.example.demo.controller;

import java.io.File;
import java.nio.charset.StandardCharsets;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.context.support.ApplicationObjectSupport;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.util.UriUtils;

import com.example.demo.util.IpUtil;

@Controller
public abstract class BaseController extends ApplicationObjectSupport {

	@InitBinder
	protected void initBinder(HttpServletRequest request, ServletRequestDataBinder binder) throws Exception {
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		CustomDateEditor dateEditor = new CustomDateEditor(format, true);
		binder.registerCustomEditor(Date.class, dateEditor);

	}

	/**
	 * 获取请求对象HttpServletRequest
	 * 
	 * @return
	 */
	protected HttpServletRequest getRequest() {
		RequestAttributes ra = RequestContextHolder.getRequestAttributes();
		return ((ServletRequestAttributes) ra).getRequest();
	}

	/**
	 * 获取响应对象HttpServletResponse
	 * 
	 * @return
	 */
	protected HttpServletResponse getResponse() {
		RequestAttributes ra = RequestContextHolder.getRequestAttributes();
		return ((ServletRequestAttributes) ra).getResponse();
	}

	/**
	 * 获取请求头中对应的内容
	 * 
	 * @param headerName
	 * @return
	 */
	protected String getHeader(String headerName) {
		return this.getRequest().getHeader(headerName);
	}

	/**
	 * 获取请求中对应参数的内容
	 * 
	 * @param paraName
	 * @return
	 */
	protected String getParameter(String paraName) {
		return this.getRequest().getParameter(paraName);
	}

	/**
	 * 获取请求中对应key值的Attribute内容
	 * 
	 * @param attributeName
	 * @return
	 */
	protected Object getRequestAttribute(String attributeName) {
		return this.getRequest().getAttribute(attributeName);
	}

	/**
	 * 往请求中设置Attribute的内容
	 * 
	 * @param attributeName
	 * @param object
	 */
	protected void setRequestAttribute(String attributeName, Object object) {
		this.getRequest().setAttribute(attributeName, object);
	}

	/**
	 * 获取请求会话对象HttpSession
	 * 
	 * @return
	 */
	protected HttpSession getSession() {
		return this.getRequest().getSession(true);
	}

	/**
	 * 获取会话中对应key值的Attribute内容
	 * 
	 * @param attributeName
	 * @return
	 */
	protected Object getSessionAttribute(String attributeName) {
		return this.getSession().getAttribute(attributeName);
	}

	/**
	 * 往会话中设置Attribute的内容
	 * 
	 * @param attributeName
	 * @param object
	 */
	protected void setSessionAttribute(String attributeName, Object object) {
		this.getSession().setAttribute(attributeName, object);
	}

	/**
	 * 获取cookie中对应key值的内容
	 * 
	 * @param cookieName
	 * @return
	 */
	protected String getCookie(String cookieName) {
		Cookie[] cookies = getRequest().getCookies();
		for (Cookie cookie : cookies) {
			if (cookieName.equals(cookie.getName())) {
				return cookie.getValue();
			}
		}
		return null;
	}

	/**
	 * 往cookie中设置内容
	 * 
	 * @param cookieName
	 * @param value
	 * @param age
	 */
	protected void setCookie(String cookieName, String value, int age) {
		Cookie cookie = new Cookie(cookieName, value);
		cookie.setMaxAge(age);
		// cookie.setHttpOnly(true);
		getResponse().addCookie(cookie);
	}

	/**
	 * 获取浏览器端的IP
	 * 
	 * @return
	 */
	protected String getIpAddress() {
		return IpUtil.getIpAddress(this.getRequest());
	}

	/**
	 * 获取服务器端的IP
	 * 
	 * @return
	 */
	protected String getServerIpAddress() {
		return IpUtil.getServerIpAddress();
	}

	/**
	 * 获取请求的url
	 * 
	 * @return
	 */
	protected String getUrl() {
		HttpServletRequest request = getRequest();
		String url = request.getRequestURI();
		String params = "";
		if (request.getQueryString() != null) {
			params = request.getQueryString().toString();
		}
		if (!"".equals(params)) {
			url = url + "?" + params;
		}
		return url;
	}

	/**
	 * 上传文件
	 * 
	 * @param file
	 * @throws Exception
	 */
	protected void upload(MultipartFile file) throws Exception {
		String fileDefaultRelativePath = "/upload";
		String filePath = getRequest().getServletContext().getRealPath(fileDefaultRelativePath);
		upload(file, filePath);

	}

	/**
	 * 下载文件
	 * 
	 * @param file
	 * @param filePath
	 * @throws Exception
	 */
	protected void upload(MultipartFile file, String filePath) throws Exception {
		if (!file.isEmpty()) {
			String fileName = file.getOriginalFilename();
			File filepath = new File(filePath, fileName);
			if (!filepath.getParentFile().exists()) {
				filepath.getParentFile().mkdirs();
			}
			file.transferTo(filepath);
		}
	}

	/**
	 * 下载文件
	 * 
	 * @param file
	 */
	protected ResponseEntity<Resource> download(File file) throws Exception {
		String fileName = file.getName();
		return download(file, fileName);
	}

	/**
	 * 下载文件
	 * 
	 * @param file
	 * @param fileName
	 */
	protected ResponseEntity<Resource> download(File file, String fileName) throws Exception {
		Resource resource = new FileSystemResource(file);

		HttpServletRequest request = getRequest();
		String header = request.getHeader("User-Agent");
		// 避免空指针
		header = header == null ? "" : header.toUpperCase();
		HttpStatus status;
		if (header.contains("MSIE") || header.contains("TRIDENT") || header.contains("EDGE")) {
			fileName = UriUtils.encode(fileName, StandardCharsets.UTF_8.name());
			status = HttpStatus.OK;
		} else {
			fileName = new String(fileName.getBytes(StandardCharsets.UTF_8), StandardCharsets.ISO_8859_1);
			status = HttpStatus.CREATED;
		}
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
		headers.setContentDispositionFormData("attachment", fileName);
		return new ResponseEntity<Resource>(resource, headers, status);
	}

	/**
	 * 允许跨域访问
	 */
	protected void allowCrossDomainAccess() {
		HttpServletResponse servletResponse = getResponse();
		servletResponse.setHeader("Access-Control-Allow-Origin", "*");
		servletResponse.setHeader("Access-Control-Allow-Methods", "POST,GET");
		servletResponse.setHeader("Access-Control-Allow-Headers:x-requested-with", "content-type");
	}

}
