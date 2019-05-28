package com.example.demo.util;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.net.InetAddress;
import java.net.UnknownHostException;

@Slf4j
public class IpUtil {

	private static final String UNKNOWN = "unknown";

	public static String getIpAddress(HttpServletRequest request) {
		String ipAddress = null;
		try {
			ipAddress = request.getHeader("x-forwarded-for");
			if (ipAddress == null || ipAddress.length() == 0 || UNKNOWN.equalsIgnoreCase(ipAddress)) {
				ipAddress = request.getHeader("Proxy-Client-IP");
			}
			if (ipAddress == null || ipAddress.length() == 0 || UNKNOWN.equalsIgnoreCase(ipAddress)) {
				ipAddress = request.getHeader("WL-Proxy-Client-IP");
			}
			if (ipAddress == null || ipAddress.length() == 0 || UNKNOWN.equalsIgnoreCase(ipAddress)) {
				ipAddress = request.getRemoteAddr();
				if ("127.0.0.1".equals(ipAddress) || "0:0:0:0:0:0:0:1".equals(ipAddress)) {
					// 根据网卡取本机配置的IP
					ipAddress = getServerIpAddress();
				}
			}
			// 对于通过多个代理的情况，第一个IP为客户端真实IP,多个IP按照','分割
			// "***.***.***.***".length()
			if (ipAddress != null && ipAddress.length() > 15) {
				// = 15
				if (ipAddress.indexOf(",") > 0) {
					ipAddress = ipAddress.substring(0, ipAddress.indexOf(","));
				}
			}
		} catch (Exception e) {
			ipAddress = "";
		}

		return ipAddress;
	}

	public static String getServerIpAddress() {
		InetAddress address;
		String serverIpAddress = null;
		try {
			// 获取的是本地的IP地址 //PC-20140317PXKX/192.168.0.121
			address = InetAddress.getLocalHost();
			// 获取的是本地的IP地址 //PC-20140317PXKX/192.168.0.121
			serverIpAddress = address.getHostAddress();
		} catch (UnknownHostException e) {
			serverIpAddress = "";
			log.error("获取本地IP时发生未知错误", e);
		}
		return serverIpAddress;
	}





	/**
	 * 获取客户端IP地址
	 * @param request
	 * @return
	 */
	public static String getIpAddress2(HttpServletRequest request) {
		String ip = request.getHeader("x-forwarded-for");
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("WL-Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getRemoteAddr();
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("http_client_ip");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("HTTP_X_FORWARDED_FOR");
		}
		if (ip != null && ip.indexOf(",") != -1) {
			ip = ip.substring(ip.lastIndexOf(",") + 1, ip.length()).trim();
		}
		if ("0:0:0:0:0:0:0:1".equals(ip)) {
			ip = "127.0.0.1";
		}
		if(StringUtils.isBlank(ip)){
			ip = "Unknown IP";
		}
		return ip;
	}

}
